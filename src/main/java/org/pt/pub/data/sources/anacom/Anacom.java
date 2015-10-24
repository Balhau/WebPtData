package org.pt.pub.data.sources.anacom;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.anacom.domain.Tarifa;
import org.pt.pub.global.configs.GlobalConfigs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitorfernandes on 10/24/15.
 */
public class Anacom {
    public static String ANACOM_BASE="http://www.anacom.pt";
    public static String TARIFARIOS=ANACOM_BASE+"/tarifarios/InternetResultadosConsultaTodos.do";

    public List<Tarifa> getTarifarios() throws Exception{
        List<Tarifa> tarifas=new ArrayList<>();
        Connection con= Jsoup.connect(TARIFARIOS).userAgent(GlobalConfigs.USER_AGENT);
        Document doc=con.get();
        Elements lines=doc.getElementsByTag("tbody").get(0).getElementsByTag("tr");

        for(Element line : lines){
            tarifas.add(getTarifa(line));
        }

        return tarifas;
    }

    private Tarifa getTarifa(Element element){
        Elements cells=element.getElementsByTag("td");
        return new Tarifa(
                cells.get(1).text(),
                cells.get(2).text(),
                cells.get(3).text(),
                cells.get(4).text(),
                cells.get(5).text(),
                cells.get(6).text(),
                cells.get(7).text()
        );
    }
}
