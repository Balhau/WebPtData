package org.pt.pub.data.sources.base;

import groovy.ui.Console;

import java.net.URLEncoder;

/**
 * Class that holds several static public attributes that represents the default values for the query
 * web endopoint of Base service, as well as static utility methods to help the implementation
 * of queries to the endpoint
 * @author balhau
 *
 */
public class BaseQueryUtils {
	
	public static final String HOST="http://www.base.gov.pt";
	private static final String DEFAULT_TYPE="contratos";
	private static final int DEFAULT_TYPE2=0;
	private static final int DEFAULT_TIPO_CONTRACTO=0;
	private static final int DEFAULT_PAIS=0;
	private static final int DEFAULT_DISTRITO=0;
	private static final int DEFAULT_CONCELHO=0;
	
	
	public static final String QUERY_PATTERN=HOST+"/Base/pt/ResultadosPesquisa?"
			+ "range=%s-%s"
			+ "&type=%s&query=%s";
	
	public static final String QUERY_PATTERN_CONTRACT=HOST+"/Base/pt/Pesquisa/Contrato?a=";
	
	public static final String SUB_QUERY_PATTERN="texto=%s&tipo=%s&tipocontrato=%s"
			+ "&cvp=%s&aqinfo=%s&adjudicante=%s&adjudicataria=%s"
			+ "&desdeprecocontrato_false=%s&desdeprecocontrato=%s&ateprecocontrato_false=%s"
			+ "&ateprecocontrato=%s&desdedatacontrato=%s&atedatacontrato=%s&desdedatapublicacao=%s"
			+ "&atedatapublicacao=%s&desdeprazoexecucao=%s&ateprazoexecucao=%s&desdedatafecho=%s"
			+ "&atedatafecho=%s&desdeprecoefectivo_false=%s&desdeprecoefectivo=%s&ateprecoefectivo_false=%s"
			+ "&ateprecoefectivo=%s&pais=%s&distrito=%s&concelho=%s";
	
	public static String queryFull (
			int pageStart,int pageEnd,String type,String text,int type2,
			int tipoContracto,String cvp,String aqinfo,String adjudicante,
			String adjudicaria,String desdePrecoContractoFalse,String desdePrecoContracto,
			String atePrecoContractoFalse,String atePrecoContracto,String desdeDataContracto,
			String ateDataContracto,String desdeDataPublicacao,String ateDataPublicacao,
			String desdePrazoExecucao, String atePrazoExecucao,String desdeDataFecho,String ateDataFecho,
			String desdePrecoEfectivoFalse,String desdePrecoEfectivo,String atePrecoEfectivoFalse,
			String atePrecoEfectivo,int pais,int distrito,int concelho
	) throws Exception {
		
		String query=String.format(SUB_QUERY_PATTERN, 
				text,type2,tipoContracto,cvp,aqinfo,adjudicante,adjudicaria,desdePrecoContractoFalse,
				desdePrecoContracto,atePrecoContractoFalse,atePrecoContracto,desdeDataContracto,
				ateDataContracto,desdeDataPublicacao,ateDataPublicacao,desdePrazoExecucao,atePrazoExecucao,
				desdeDataFecho,ateDataFecho,desdePrecoEfectivoFalse,desdePrecoEfectivo,atePrecoEfectivoFalse,
				atePrecoEfectivo,pais,distrito,concelho
		);
		return String.format(QUERY_PATTERN, 
				pageStart,pageEnd,type,URLEncoder.encode(query, "UTF-8")
		);
	}
	
	/**
	 * This method will return the default query with page specified by parameter
	 * @param startPage {@link Integer} start item
	 * @param endPage {@link Integer}
	 * @return
	 */
	public static String defaultQueryWithPagination(int firstItem, int lastItem) throws Exception{
		return queryFull(firstItem, lastItem, DEFAULT_TYPE, "", 
				DEFAULT_TYPE2, DEFAULT_TIPO_CONTRACTO, 
				"", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", "", "", "", "", 
				DEFAULT_PAIS, DEFAULT_DISTRITO, DEFAULT_CONCELHO);
	}
	
	public static String defaultQuery() throws Exception{
		return defaultQueryWithPagination(0, 25);
	}
}
