package org.pt.pub.data.sources.tap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.tap.domain.FlightDetail;
import org.pt.pub.global.configs.GlobalConfigs;

import java.util.ArrayList;
import java.util.List;

/**
 * API for the Transportes Aéreos Portugueses or TAP until recently a public institution that operates
 * in the flight markets. With this API you can search for flights, get the time and prices for each one
 * @author balhau
 *
 */
public class TAP {

    public static String TAP_SEARCH_PATH="http://book.flytap.com/r3air/TAPPT/PoweredAvailabilityBP.aspx?\n" +
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
        return null;
    }

}
