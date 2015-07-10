package org.pt.pub.data;

import org.pt.pub.data.sources.base.Base;
import org.pt.pub.data.sources.base.BaseQueryUtils;


public class base {
	public static void main(String[] args) throws Exception{
		Base base=new Base();
		int idEntry=1498876;
		System.out.println(base.getAllResults(0, 25));
		System.out.println(base.getEntryInformationByContractoId(idEntry));
	}
}
