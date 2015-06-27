package org.pt.pub.global.utils;

import java.util.Collection;
import java.util.Iterator;

public class StringUtils {
	/**
	 * Utility method to join collections of strings
	 * @param strings {@link Collection} A collection of strings
	 * @param separator {@link String} The separator used in join operation
	 * @return {@link String} The result of the join over the collection of strings
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
