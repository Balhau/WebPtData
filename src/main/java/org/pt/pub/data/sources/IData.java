package org.pt.pub.data.sources;

import org.pt.pub.data.exceptions.EncodingException;

/**
 * This interface for the data retrieved by the services. All they should encode the data into
 * json and xml.
 * @author balhau
 *
 */
public interface IData {
	/**
	 * This method encodes the data into JSON format
	 * @return {@link String} the encoded data into JSON format
	 * @throws EncodingException Encoding problems 
	 */
	public String toJSON() throws EncodingException;
	/**
	 * This method encodes the data into XML format
	 * @return {@link String} the encoded data into XML format
	 * @throws EncodingException Encoding problems
	 */
	public String toXML() throws EncodingException;
}
