package org.pub.pt.data.sources.rbe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.*;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.pt.data.sources.rbe.domain.RbeIndicator;
import org.pub.pt.data.sources.rbe.domain.RbeIndicatorData;
import org.pub.global.configs.HtmlTag;
import org.pub.global.utils.DomUtils;


/**
 * Rede de Bibliotecas Escolares (RBE).<br><br>
 * This class will be used to retrieve information from 
 * <a href="http://rbe.mec.pt/" target="_blank">Rede de Bibliotecas Escolares</a><br>
 * In <a href="http://rbe.mec.pt/np4/indicadores" target="_blank">Indicadores</a> you find a list of statistical
 * information about the public school libraries.<br>
 * 
 * This class implements the methods for you retrieve the list of indicators as well the contents of each
 * indicator.<br>
 * 
 * As an example you can use the following code to fetch data available here<br><br> 
 * 
 * <a href="http://rbe.mec.pt/np4/indicadores?cats=361&s=1241" target="_blank">Indicators data</a><br><br>
 * 
 * <b>Tutorial:</b><br><br>
 * <pre>
 * 	
 * {@link Rbe} rbe=new {@link #Rbe() Rbe()};
 * {@link List} lIndicators=rbe.{@link #getIndicators() getIndicators()};
 * {@link RbeIndicator} indicator=lIndicators.{@link List#get(int) get(0)};
 * {@link Integer} cat=indicator.{@link RbeIndicator#getCategorie() getCategorie()};
 * {@link Integer} ser=indicator.{@link RbeIndicator#getSerie() getSerie()});
 * {@link RbeIndicatorData} data=rbe.{@link #getIndicator(int, int) getIndicator(cat,ser)};
 * </pre>
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
	
	/**
	 * This private method builds the URL for the indicator with the category and serie values provided as 
	 * parameters:<br><br>
	 * For example<br><br>
	 * <code>
	 * String a=this.urlFromCategorieSerie(123,321)<br>
	 * <b>Return:</b> a="http://rbe.mec.pt/np4/indicadores?cats=123&s=321"
	 * </code>
	 * @param cat {@link Integer} Category id
	 * @param serie {@link Integer} serie id
	 * @return {@link String} Url of the indicator
	 */
	private String urlFromCategorieSerie(int cat,int serie){
		return String.format(INDICATOR_PATTERN, cat,serie);
	}
	
	/**
	 * Method that returns a {@link List} of {@link RbeIndicator} with each entry representing a statistic
	 * that can be queried with {@link #getIndicator(int, int) getIndicator}
	 * @return {@link List} of {@link RbeIndicator} elements
	 * @throws Exception if error is found while parsing data
	 */
	public List<RbeIndicator> getIndicators() throws Exception{
		Connection cn=DomUtils.getHTML(INDICATOR_HOME);
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
		
		String url=a.attr("href");
		rb.setUrl(url);
		rb.setTitle(a.text());
		rb.setData(spans.get(0).text());
		rb.setDescription(spans.get(1).text());
		rb.setCategorie(Integer.parseInt(url.split("cats=")[1].split("&")[0]));
		rb.setSerie(Integer.parseInt(url.split("&s=")[1]));
		return rb;
	}
	
	/**
	 * Method that returns an {@link RbeIndicatorData} with the data presented in the
	 * indicator represented by a category and series id.
	 * @param cat {@link Integer} Category id
	 * @param serie {@link Integer} Series id
	 * @return {@link RbeIndicatorData} Data for the series
	 * @throws Exception in case of error parsing the data
	 */
	public RbeIndicatorData getIndicator(int cat,int serie) throws Exception{
		Connection cn=DomUtils.getHTML(urlFromCategorieSerie(cat,serie));
		Document doc=cn.get();
		Elements els=doc.getElementsByTag(HtmlTag.SCRIPT);
		Element scdata=els.get(els.size()-1);
		String scriptCode=scdata.html().split("var styleParameters")[0];
		se.eval(scriptCode);
		Bindings bds=se.getBindings(ScriptContext.ENGINE_SCOPE);
		Iterator<String> ks=bds.keySet().iterator();
		
		@SuppressWarnings("restriction")
		String[] categories=DomUtils.stringArrayFromScriptObjectMirror((ScriptObjectMirror)bds.get(ks.next()));
		@SuppressWarnings("restriction")
		List<String[]> series=DomUtils.stringTableFromScriptObjectMirror((ScriptObjectMirror)bds.get(ks.next()));
		@SuppressWarnings("restriction")
		String[] seriesNames=DomUtils.stringArrayFromScriptObjectMirror((ScriptObjectMirror)bds.get(ks.next()));
		
		return new RbeIndicatorData(categories,series,seriesNames);
	}
}
