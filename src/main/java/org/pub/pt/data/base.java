package org.pub.pt.data;

import org.pub.pt.data.sources.base.Base;


public class base {
	public static void main(String[] args) throws Exception{
		Base base=new Base();
		int idEntry=1498876;
		System.out.println(base.getAllResults(0, 25));
		System.out.println(base.getEntryInformationByContractoId(idEntry));
	}
}
