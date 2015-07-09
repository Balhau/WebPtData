package org.pt.pub.data.sources.base;


/**
 * This is a class that will scrap information from the <a href="http://www.base.gov.pt/" target="_blank">Base</a> that 
 * holds information about public contracts of portuguese government
 * @author balhau
 *
 */
public class Base{
	
	public static final String HOST="http://www.base.gov.pt/";
	public static final String QUERY_PATTERN=HOST+"/Base/pt/ResultadosPesquisa?"
			+ "type=contratos&query=texto=&tipo=0&tipocontrato=0"
			+ "&cvp=&aqinfo=&adjudicante=&adjudicataria="
			+ "&desdeprecocontrato_false=&desdeprecocontrato=&ateprecocontrato_false="
			+ "&ateprecocontrato=&desdedatacontrato=&atedatacontrato=&desdedatapublicacao="
			+ "&atedatapublicacao=&desdeprazoexecucao=&ateprazoexecucao=&desdedatafecho="
			+ "&atedatafecho=&desdeprecoefectivo_false=&desdeprecoefectivo=&ateprecoefectivo_false="
			+ "&ateprecoefectivo=&pais=0&distrito=0&concelho=0";
	
	public Base(){
		
	}
	
	
}
