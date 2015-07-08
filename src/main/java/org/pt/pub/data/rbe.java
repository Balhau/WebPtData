package org.pt.pub.data;

import org.pt.pub.data.sources.rbe.Rbe;

public class rbe {
	public static void main(String args[]) throws Exception{
		Rbe r=new Rbe();
		r.evaluateJavascript("http://rbe.mec.pt/np4/indicadores?cats=342&s=1132");
		
	}
}
