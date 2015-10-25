package org.pt.pub.data.sources.tap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.tap.domain.Destination;
import org.pt.pub.data.sources.tap.domain.FlightDetail;
import org.pt.pub.global.configs.GlobalConfigs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            "&searchType=fixed\n" +
            "&";

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
        Connection con = Jsoup.connect(String.format(TAP_SEARCH_DESTINATIONS,departureCode))
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

    public List<FlightDetail> getFlights(String origin,
                                         String destination,
                                         String departDate,
                                         String returnDate,
                                         String adults) throws Exception{
        List<FlightDetail> flightDetails=new ArrayList<>();
        String url=buildSearchPath(origin, destination, departDate, returnDate, adults);
        Connection con = Jsoup.connect(url).userAgent(GlobalConfigs.USER_AGENT);

        Document doc=con.get();

        Element table=doc.getElementById("tableBodyContent");

        Elements rows=table.getAllElements();
        int i=0;
        for(Element row : rows){
            if(i!=0){ //Skip first row

            }
            i+=1;
        }

        return flightDetails;
    }

    private FlightDetail parseFligthDetail(Element row){
        row.getAllElements();
        return null;
    }

}
