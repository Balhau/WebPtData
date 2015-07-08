package org.pt.pub.data.sources.rbe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.rbe.domain.RbeIndicator;
import org.pt.pub.data.sources.rbe.domain.RbeIndicatorData;
import org.pt.pub.global.configs.HtmlTag;
import org.pt.pub.global.utils.DomUtils;


/**
 * Rede de Bibliotecas Escolares (RBE).
 * This class will be used to retrieve information from http://rbe.mec.pt/
 * @author balhau 
 *
 */
public class Rbe {
	
	private static final String HOST="http://rbe.mec.pt";
	private static final String INDICATOR_PATTERN=HOST+"/np4/indicadores?cats=%s&s=%s";
	private static final String INDICATOR_HOME=HOST+"/np4/indicadores/";
	
	private ScriptEngine se;
	
	public Rbe(){
		se=new ScriptEngineManager().getEngineByName("JavaScript");
	}
	
	private String urlFromCategorieSerie(int cat,int serie){
		return String.format(INDICATOR_PATTERN, cat,serie);
	}
	
	public List<RbeIndicator> getIndicators() throws Exception{
		Connection cn=Jsoup.connect(INDICATOR_HOME);
		List<RbeIndicator> l=new ArrayList<RbeIndicator>();
		Document doc=cn.get();
		Elements lines=doc.getElementsByClass("newsInList");
		for(Element line : lines){
			l.add(indicatorFromElement(line));
		}
		return l;
	}
	
	private RbeIndicator indicatorFromElement(Element el){
		RbeIndicator rb=new RbeIndicator();
		Element a=el.getElementsByTag(HtmlTag.ANCHOR).get(0);
		Elements spans=el.getElementsByTag(HtmlTag.SPAN);
		
		rb.setUrl(a.attr("href"));
		rb.setTitle(a.text());
		rb.setData(spans.get(0).text());
		rb.setDescription(spans.get(1).text());
		return rb;
	}
	
	public RbeIndicatorData getIndicator(int cat,int serie) throws Exception{
		Connection cn=Jsoup.connect(urlFromCategorieSerie(cat,serie));
		Document doc=cn.get();
		Elements els=doc.getElementsByTag(HtmlTag.SCRIPT);
		Element scdata=els.get(els.size()-1);
		String scriptCode=scdata.html().split("var styleParameters")[0];
		se.eval(scriptCode);
		Bindings bds=se.getBindings(ScriptContext.ENGINE_SCOPE);
		Iterator<String> ks=bds.keySet().iterator();
		
		String[] categories=DomUtils.stringArrayFromScriptObjectMirror((ScriptObjectMirror)bds.get(ks.next()));
		List<String[]> series=DomUtils.stringTableFromScriptObjectMirror((ScriptObjectMirror)bds.get(ks.next()));
		String[] seriesNames=DomUtils.stringArrayFromScriptObjectMirror((ScriptObjectMirror)bds.get(ks.next()));
		
		return new RbeIndicatorData(categories,series,seriesNames);
	}
}
