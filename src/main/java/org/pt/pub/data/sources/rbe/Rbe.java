package org.pt.pub.data.sources.rbe;

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
import org.pt.pub.global.configs.HtmlTag;
import org.pt.pub.global.utils.DomUtils;


/**
 * Rede de Bibliotecas Escolares (RBE).
 * This class will be used to retrieve information from http://rbe.mec.pt/
 * @author balhau 
 *
 */
public class Rbe {
	private ScriptEngine se;
	
	public Rbe(){
		se=new ScriptEngineManager().getEngineByName("JavaScript");
	}
	
	public void evaluateJavascript(String urlData) throws Exception{
		Connection cn=Jsoup.connect(urlData);
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
		
		
	}
}
