package org.pt.pub.data.sources;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.pt.pub.data.exceptions.EncodingException;

/**
 * The datatypes to be marshalled should extend this class. This has the common methods to be applied for 
 * all the datatypes.
 * @author balhau
 *
 */
public class AbstractData implements IData{

	public String toJSON() throws EncodingException {
		// TODO Auto-generated method stub
		return null;
	}
	

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
			throw new EncodingException(e.getMessage());
		}
	}
}
