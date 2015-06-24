package org.pt.pub.global.utils;

import java.util.Collection;
import java.util.Iterator;

public class StringUtils {
	/**
	 * Utility method to join collections of strings
	 * @param strings
	 * @param separator
	 * @return
	 */
	public static String join(Collection<String> strings,String separator){
		StringBuilder sb=new StringBuilder();
		Iterator<String> it=strings.iterator(); 
		for(int i=0;i<strings.size();i++){
			if(i!=0) sb.append(separator);
			sb.append(it.next());
		}
		return sb.toString();
	}
}
