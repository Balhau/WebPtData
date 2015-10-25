package org.pt.pub.data.sources.anacom;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.anacom.domain.TarifaFixo;
import org.pt.pub.data.sources.anacom.domain.TarifaInternet;
import org.pt.pub.data.sources.anacom.domain.TarifaMovel;
import org.pt.pub.data.sources.anacom.domain.TarifaTelevisao;
import org.pt.pub.global.configs.GlobalConfigs;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will retrieve relevant information from Anacom. <a href="http://www.anacom.pt">Anacom</a> is a portuguese
 * entity that controls the telecomunication market
 * Created by vitorfernandes on 10/24/15.
 */
public class Anacom {

    public interface LineProcessor<T>{
        T processLine(Element line);
    }

    public static String ANACOM_BASE="http://www.anacom.pt";
    public static String TARIFARIOS_INTERNET=ANACOM_BASE+"/tarifarios/InternetResultadosConsultaTodos.do";
    public static String TARIFARIOS_TELEVISAO=ANACOM_BASE+"/tarifarios/TelevisaoResultadosConsultaTodos.do";
    public static String TARIFARIOS_MOVEL=ANACOM_BASE+"/tarifarios/MovelResultadosConsultaTodos.do";
    public static String TARIFARIOS_FIXO=ANACOM_BASE+"/tarifarios/FixoResultadosConsultaTodos.do";

    /**
     * Return all the internet services available from the Portuguese Market
     * @return
     * @throws Exception
     */
    public List<TarifaInternet> getTarifariosInternet() throws Exception{
        return processTable(TARIFARIOS_INTERNET, (Element line) -> getTarifaInternet(line));
    }

    public List<TarifaTelevisao> getTarifariosTelevisao() throws Exception{
        return processTable(TARIFARIOS_TELEVISAO,(Element line)-> getTarifaTelevisao(line));
    }

    public List<TarifaMovel> getTarifariosMovel() throws Exception{
        return processTable(TARIFARIOS_MOVEL,(Element line) -> getTarifaMovel(line));
    }

    public List<TarifaFixo> getTarifariosFixo() throws Exception{
        return processTable(TARIFARIOS_FIXO,(Element line) -> getTarifaFixo(line));
    }


    private <T> List<T> processTable(String url,LineProcessor<T> processor) throws Exception{
        List<T> tarifas=new ArrayList<>();
        Connection con= Jsoup.connect(url).userAgent(GlobalConfigs.USER_AGENT).timeout(GlobalConfigs.CONNECTION_TIMEOUT);
        Document doc=con.get();
        Elements lines=doc.getElementsByTag("tbody").get(0).getElementsByTag("tr");

        for(Element line : lines){
            tarifas.add(processor.processLine(line));
        }
        return tarifas;
    }

    private TarifaTelevisao getTarifaTelevisao(Element element){
        Elements cells=element.getElementsByTag("td");
        return new TarifaTelevisao(
                cells.get(1).text(),
                cells.get(2).text(),
                cells.get(6).text(),
                cells.get(3).text(),
                cells.get(4).text(),
                cells.get(5).text()
        );
    }

    private TarifaMovel getTarifaMovel(Element element){
        Elements cells=element.getElementsByTag("td");

        return new TarifaMovel(
                cells.get(1).text(),
                cells.get(2).text(),
                cells.get(7).text(),
                cells.get(3).text(),
                cells.get(4).text(),
                cells.get(5).text(),
                cells.get(6).text()
        );
    }

    private TarifaFixo getTarifaFixo(Element element){
        Elements cells=element.getElementsByTag("td");

        return new TarifaFixo(
                cells.get(1).text(),
                cells.get(2).text(),
                cells.get(6).text(),
                cells.get(3).text(),
                cells.get(4).text(),
                cells.get(5).text()
        );
    }

    private TarifaInternet getTarifaInternet(Element element){
        Elements cells=element.getElementsByTag("td");
        return new TarifaInternet(
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
