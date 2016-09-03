package org.pub.pt.data;

import org.pub.pt.data.sources.rbe.Rbe;

public class rbe {
	public static void main(String args[]) throws Exception{
		Rbe r=new Rbe();
		System.out.println(
				r.getIndicator(342,1200).toXML()
		);
		
		System.out.println(r.getIndicators());
	}
}
