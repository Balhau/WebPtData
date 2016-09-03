package org.pt.pub.data.sources.tap;

import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.tap.domain.Destination;
import org.pt.pub.data.sources.tap.domain.FlightDetail;
import org.pt.pub.data.sources.tap.domain.FlightSegment;
import org.pub.global.configs.GlobalConfigs;
import org.pub.global.utils.DomUtils;

import java.util.*;

/**
 * API for the Transportes Aéreos Portugueses or TAP until recently a public institution that operates
 * in the flight markets. With this API you can search for flights, get the time and prices for each one
 * @author balhau
 *
 */
public class TAP {

    public static final String TAP_BASE="http://www.flytap.com/";
    public static final String TAP_BOOK_BASE="http://book.flytap.com";
    public static final String TAP_SEARCH_LOCATION=TAP_BASE+"/getAptMatch.php?language=enus&term=%s";
    public static final String TAP_SEARCH_DESTINATIONS=TAP_BASE+"/loadOriginDestination.php?language=en&getDestinations=%s";

    public static final String TAP_SEARCH_PATH=TAP_BOOK_BASE+"/r3air/TAPPT/PoweredAvailabilityBP.aspx?\n" +
            "flightType=return\n" +
            "&_a=FTPTPT\n" +
            "&adt=%s\n" +
            "&_l=pt\n" +
            "&origin=%s\n" +
            "&destination=%s\n" +
            "&depDate=%s\n" +
            "&retDate=%s\n" +
            "&depTime=0\n" +
            "&retTime=0\n" +
            "&maxConn=-1\n" +
            "&market=PT\n" +
            "&cabinClass=Y\n" +
            "&currency=\n" +
            "&searchType=fixed\n";

    private String buildSearchPath(
            String origin,
            String destination,
            String departDate,
            String returnDate,
            String adults
            ){
        return String.format(TAP_SEARCH_PATH,
                adults,origin,destination,departDate,returnDate
        ).replace("\n","");
    }

    public List<Destination> searchDeparture(String keyword) throws Exception{
        Connection con=Jsoup
                .connect(String.format(TAP_SEARCH_LOCATION, keyword))
                .userAgent(GlobalConfigs.USER_AGENT)
                .timeout(GlobalConfigs.CONNECTION_TIMEOUT);

        Document doc=con.get();
        Map[] departures=new Gson().fromJson(doc.text(),Map[].class);
        return mapsToDeparture(departures);
    }

    public List<Destination> findPossibleDestinations(String departureCode) throws Exception{
        Connection con = DomUtils.get(String.format(TAP_SEARCH_DESTINATIONS,departureCode))
                .userAgent(GlobalConfigs.USER_AGENT)
                .timeout(GlobalConfigs.CONNECTION_TIMEOUT);

        Document doc=con.get();
        Map departures=new Gson().fromJson(doc.text(),Map.class);
        return mapToDeparture(departures);
    }

    private List<Destination> mapToDeparture(Map map){
        List<Destination> destinations = new ArrayList<>();
        String value;
        Set<String> keys = map.keySet();
        for(String key : keys){
            value=(String)map.get(key);
            destinations.add(new Destination(key,value));
        }
        return destinations;
    }

    private List<Destination> mapsToDeparture(Map[] maps){
        List<Destination> destinations=new ArrayList<>();
        Destination aux;
        for(Map m : maps){
            aux=new Destination((String)m.get("id"),(String)m.get("label"));
            destinations.add(aux);
        }
        return destinations;
    }

    public Map<String,List<FlightDetail>> getFlights(String origin,
                                         String destination,
                                         String departDate,
                                         String returnDate,
                                         String adults) throws Exception{
        Map<String,List<FlightDetail>> flightWithReturn=new HashMap<>();
        String url=buildSearchPath(origin, destination, departDate, returnDate, adults);
        Connection con = DomUtils.get(url);

        Document doc=con.get();

        Elements tables=doc.getElementsByClass("ffTable");
        List<FlightDetail> departures=parseTableFlightDetail(tables.get(0));
        List<FlightDetail> returns=parseTableFlightDetail(tables.get(1));

        flightWithReturn.put("departures",departures);
        flightWithReturn.put("returns",returns);

        return flightWithReturn;
    }

    private List<FlightDetail> parseTableFlightDetail(Element table){
        Elements rows=table.getElementsByTag("tr");
        List<FlightDetail> flights=new ArrayList<>();
        int i=0;
        for(Element row : rows){
            if(i!=0){} //Skip first row
            else{
                flights.add(parseFligthDetail(row));
            }
            i+=1;
        }
        return flights;
    }

    private FlightDetail parseFligthDetail(Element row){
        List<FlightSegment> segments=new ArrayList<>();
        Element fligthDetails=row.getElementsByClass("flightInfo").get(0);
        Elements segmentRows=fligthDetails.getElementsByTag("tr");
        if(segmentRows.size()==2){//one segment
            segments.add(parseSegment(segmentRows.get(0)));
        }else if(segmentRows.size()==3) {//two segments
            segments.add(parseSegment(segmentRows.get(0)));
            segments.add(parseSegment(segmentRows.get(2)));
        }else{//need to be studied more than two segments
            return null;
        }

        Elements eprices=row.getElementsByClass("farePrice");
        Elements labels;
        String[] prices=new String[eprices.size()];
        int i=0;
        for(Element price : eprices){
            labels=price.getElementsByTag("label");
            prices[i]=labels.size()!=0?labels.get(0).text():"NA";
            i++;
        }
        return new FlightDetail(segments,prices[0],prices[1],prices[2],prices[3],prices[4]);
    }

    private FlightSegment parseSegment(Element row){
        Elements cells=row.getAllElements();
        return new FlightSegment(
                cells.get(0).getAllElements().get(0).text(),
                cells.get(1).getAllElements().get(0).attr("class").split("availCarrier")[1],
                cells.get(2).getAllElements().get(0).text(),
                cells.get(3).getAllElements().get(0).text()
        );
    }

}
