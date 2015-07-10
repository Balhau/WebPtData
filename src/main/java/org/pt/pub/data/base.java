package org.pt.pub.data;

import org.pt.pub.data.sources.base.Base;
import org.pt.pub.data.sources.base.BaseQueryUtils;


public class base {
	public static void main(String[] args) throws Exception{
		Base base=new Base();
		System.out.println(BaseQueryUtils.defaultQuery());
	}
}
