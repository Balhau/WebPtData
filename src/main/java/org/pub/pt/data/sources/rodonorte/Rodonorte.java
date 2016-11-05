package org.pub.pt.data.sources.rodonorte;


import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.sun.corba.se.impl.protocol.RequestCanceledException;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.pub.global.utils.DomUtils;
import org.pub.global.utils.ReflectionUtils;
import org.pub.pt.data.sources.rodonorte.domain.Destination;
import org.pub.pt.data.sources.rodonorte.domain.Ride;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.print.Doc;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * This will return the scheduling of bus of a northern portuguese company
 */
public class Rodonorte {
    private static final String RODONORTE_URL="http://www.rodonorte.pt/";

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

    private void hackIntoRestrictedHeaders() throws Exception{

        Field[] fields = HttpURLConnection.class.getDeclaredFields();
        List<Field> f=Arrays.stream(fields)
                .filter(fi -> fi.getName() == "restrictedHeaders" || fi.getName() == "restrictedHeaderSet")
                .collect(toList());

        ReflectionUtils.setStaticValue(f.get(0),new HashSet<>());
        ReflectionUtils.setStaticValue(f.get(1),new String[]{});
    }

    public List<Destination> getDestinations(String origin) throws Exception {
        hackIntoRestrictedHeaders();
        Connection con  = DomUtils.get(RODONORTE_URL+"pt");
        Connection.Response r = con.execute();
        con = DomUtils.get(RODONORTE_URL+"plugins/ximyticket/xiMyticket.ajax.php");
        con.ignoreContentType(true);
        con.header("Origin","http://www.rodonorte.pt");
        con.header("X-Requested-With","XMLHttpRequest");
        con.header("Referer","http://www.rodonorte.pt/pt/");
        con.data("method","GetStopsRest","isRest","true");
        Document doc = con.post();
        Map jsonData = new Gson().fromJson(doc.body().text(),Map.class);
        return ((ArrayList<LinkedTreeMap<String,String>>) jsonData.get("data"))
                .stream()
                .map(this::parseDestination)
                .collect(toList());
    }

    private Destination parseDestination(LinkedTreeMap treeMap){
        Destination newDestination=new Destination((Double)treeMap.get("Id"),(String)treeMap.get("Name"));
        return newDestination;
    }
}
