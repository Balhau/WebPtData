package org.pt.pub.data.sources.quotes.brainyquote.domain;

import org.pt.pub.data.sources.domain.AbstractData;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Created by vitorfernandes on 10/24/15.
 */
public class Topic extends Entry{

    public Topic(String name,String url){
        super(name,url);
    }

}
