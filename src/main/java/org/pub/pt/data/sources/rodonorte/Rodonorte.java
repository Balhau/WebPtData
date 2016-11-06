package org.pub.pt.data.sources.rodonorte;


import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.rodonorte.domain.Destination;
import org.pub.pt.data.sources.rodonorte.domain.Ride;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * This will return the scheduling of bus of a northern portuguese company
 */
public class Rodonorte {
    private static final String RODONORTE_URL="http://www.rodonorte.pt/";
    private static final String HORARIOS_PATH="pt/horarios/";
    private static final String DESTINATION_PATH="plugins/ximyticket/xiMyticket.ajax.php";
    private static final String DESTINATION_METHOD_NAME="GetStopsRest";
    private static final String DESTINATION_REST_FLAG="true";
    private static final String DESTINATION_REST_PARAM="isRest";
    private static final String RPC_METHOD_PARAM="method";
    private static final String ORIGIN_PARAM = "origem";
    private static final String DESTINY_PARAM= "destino";


    public List<String> getOriginList() throws Exception{
        Connection con = DomUtils.get(RODONORTE_URL+"pt");
        Document page = con.get();
        return page.getElementsByTag("select")
                .get(2)
                .getElementsByTag("option")
                .stream()
                .skip(1)
                .map( o -> o.text())
                .collect(toList());
    }

    public List<Destination> getDestinations(String origin) throws Exception {
        Connection con = DomUtils.get(RODONORTE_URL+DESTINATION_PATH);
        con.ignoreContentType(true);
        con.data(
                RPC_METHOD_PARAM,DESTINATION_METHOD_NAME,
                DESTINATION_REST_PARAM,DESTINATION_REST_FLAG
        );
        Document doc = con.post();
        Map jsonData = new Gson().fromJson(doc.body().text(),Map.class);
        return ((ArrayList<LinkedTreeMap<String,String>>) jsonData.get("data"))
                .stream()
                .map(this::parseDestination)
                .collect(toList());
    }

    public List<Ride> getRides(String origin, Destination destination) throws Exception {
        Connection con = DomUtils.get(RODONORTE_URL+HORARIOS_PATH);
        con.data(
                ORIGIN_PARAM,origin,
                DESTINY_PARAM,destination.getName()
        );
        Document doc = con.post();
        return doc.getElementsByTag("table")
                .stream()
                .skip(1)
                .limit(doc.getElementsByTag("table").size()-2)
                .map(e -> parseRidesFromTableRow(e,origin,destination))
                .collect(Collectors.toList());
    }

    private Destination parseDestination(LinkedTreeMap treeMap){
        Destination newDestination=new Destination((Double)treeMap.get("Id"),(String)treeMap.get("Name"));
        return newDestination;
    }

    private Ride parseRidesFromTableRow(Element row,String origin,Destination destination) {
        Elements cells = row.getElementsByTag("tr").get(0).getElementsByTag("td");
        return new Ride(
                origin,
                destination.getName(),
                cells.get(1).text(),
                cells.get(3).text(),
                Float.parseFloat(cells.get(5).text().split("€")[1].replace(",","."))
                );
    }
}
