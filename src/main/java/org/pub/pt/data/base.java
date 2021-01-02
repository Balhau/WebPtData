package org.pub.pt.data;

import org.pub.pt.data.sources.base.BaseGov;


public class base {
	public static void main(String[] args) throws Exception{
		BaseGov base=new BaseGov();
		int idEntry=1498876;
		System.out.println(base.getAllResults(0, 25));
		System.out.println(base.getEntryInformationByContractoId(idEntry));
	}
}
