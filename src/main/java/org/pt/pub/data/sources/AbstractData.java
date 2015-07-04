package org.pt.pub.data.sources;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.pt.pub.data.exceptions.EncodingException;

import com.google.gson.Gson;

/**
 * The datatypes to be marshalled should extend this class. This has the common methods to be applied for 
 * all the datatypes.
 * @author balhau
 *
 */
public class AbstractData implements IData{

	
	/**
	 * The purpose of this method is able all subclass to encode his data into a JSON format
	 * 
	 * @return {@link String} JSON encoding of the object
	 */
	public String toJSON() throws EncodingException {
		Gson gson=new Gson();
		String jsonData=gson.toJson(this);
		return jsonData;
	}
	
	@Override
	public String toString(){
		try{
			return this.toXML();
		}catch(Exception ex){
			return ex.getMessage();
		}
	}
	

	/**
	 * This is a method that able all the subclass to encode his data into XML file format
	 * @return {@link String} String with the data encoded as XML file format
	 */
	public String toXML() throws EncodingException{
		try{
			JAXBContext ctx= JAXBContext.newInstance(this.getClass());
			Marshaller m=ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw=new StringWriter();
			m.marshal(this, sw);
			sw.close();
			return sw.toString();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new EncodingException(e.getMessage());
		}
	}
}
