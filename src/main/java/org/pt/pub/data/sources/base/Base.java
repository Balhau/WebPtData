package org.pt.pub.data.sources.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.types.resources.BaseResourceCollectionContainer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.base.domain.BaseEntry;
import org.pt.pub.data.sources.base.domain.BaseQueryResponse;
import org.pt.pub.data.sources.domain.Message;
import org.pt.pub.data.sources.domain.MessageService;
import org.pt.pub.data.utilities.Utils;
import org.pt.pub.global.configs.GlobalConfigs;
import org.pt.pub.global.configs.HtmlTag;
import org.pt.pub.global.domain.TableData;
import org.pt.pub.global.utils.DomUtils;


/**
 * This is a class that will scrap information from the <a href="http://www.base.gov.pt/" target="_blank">Base</a> that 
 * holds information about public contracts of portuguese government
 * @author balhau
 *
 */
public class Base implements MessageService{
	
	public Base(){
		
	}

	/**
	 * Method that returns a list of entries between a start and end offset
	 * @param startOffset {@link Integer} first item to be retrieved
	 * @param endOffset {@link Integer} last item to be retrieved
	 * @return {@link BaseQueryResponse} Response object for the query
	 * @exception Exception in case error is found while parsing data
	 */
	public BaseQueryResponse getAllResults(int startOffset,int endOffset) throws Exception{
		BaseQueryResponse response = getResultsByQuery(startOffset, endOffset, BaseQueryUtils.defaultQueryWithPagination(startOffset, endOffset));
		response.setItems(response.getItems().subList(1, response.getItems().size()));
		return response;
	}
	
	public BaseQueryResponse getByAdjudicante(int startOffset,int endOffset,String adjudicante) throws Exception{
		return getResultsByQuery(startOffset, endOffset, 
					BaseQueryUtils.getByAdjudicante(startOffset, endOffset, adjudicante)
			);
	}
	
	public BaseQueryResponse getByAjudicatario(int startOffset,int endOffset,String adjudicatario) throws Exception{
		return getResultsByQuery(startOffset, endOffset, 
				BaseQueryUtils.getByAdjudicario(startOffset, endOffset, 
						adjudicatario)
				);
	}
	
	private BaseQueryResponse getResultsByQuery(int startOffset,int endOffset,String query) throws Exception{
		List<BaseEntry> entries=new ArrayList<BaseEntry>();
		Connection con=Jsoup.connect(query);
		con.timeout(GlobalConfigs.CONNECTION_TIMEOUT);
		Document doc=con.get();
		int totalElements=Integer.parseInt(doc.getElementsByTag("span").get(2).text());
		Elements results=doc.getElementById("resultadosContractos").getElementsByTag(HtmlTag.TR);
		for(Element row : results){
			entries.add(baseEntryFromRow(row));
		}
		return new BaseQueryResponse(entries, totalElements, startOffset, endOffset);
	}
	
	public List<TableData> getEntryInformationByContractoId(int idEntry) throws Exception{
		List<TableData> data=new ArrayList<TableData>();
		Connection con=Jsoup.connect(BaseQueryUtils.QUERY_PATTERN_CONTRACT+idEntry);
		Document doc=con.get();
		
		data.add(DomUtils.tableElementToTableData(doc.getElementsByTag(HtmlTag.TABLE).get(0)));
		data.add(DomUtils.tableElementToTableData(doc.getElementsByTag(HtmlTag.TABLE).get(1)));
		
		return data;
	}
	
	private BaseEntry baseEntryFromRow(Element row){
		BaseEntry be=new BaseEntry();
		
		Elements cells=row.getElementsByTag(HtmlTag.TD);
		if(cells.size()==0){
			return be;
		}
		
		be.setDescription(cells.get(0).text());
		be.setPrice(Float.parseFloat(
				cells.get(1).text().replace(".", "").replace("€","").replace(",", ".").trim())
		);
		be.setPublication(cells.get(2).text());
		be.setAdjudicante(cells.get(3).text());
		be.setAdjudicatario(cells.get(4).text());
		String url=cells.get(5).getElementsByTag(HtmlTag.ANCHOR).get(0).attr("href");
		be.setUrl(url);
		be.setId(Integer.parseInt(url.split("a=")[1]));
		return be;
	}

	@Override
	public Message getMessage() throws Exception {
		BaseQueryResponse resp=getAllResults(1,2);
		int initOffset=(int)Math.floor(Math.random()*resp.getNumOfResults());
		int lastOffset=initOffset+20;
		BaseQueryResponse query2=getAllResults(initOffset,lastOffset);
		BaseEntry entry=Utils.pickRandom(query2.getItems());
		return new Message(
				"Contrato público:\n"+
				"Descrição: "+entry.getDescription()+"\n"+
				"Adjudicante: "+entry.getAdjudicante()+"\n"+
				"Adjudicatário: "+entry.getAdjudicatario()+"\n"+
				"Preço: "+entry.getPrice()+"\n"+
				"Data: "+entry.getPublication()+"\n","Base Contratos Públicos"
		);
	}
	
}
