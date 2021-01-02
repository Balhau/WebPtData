package org.pub.global.domain;

import org.pub.pt.data.sources.domain.AbstractData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tableRow")
public class TableRow extends AbstractData {
    List<String> column;

    public TableRow() {
        this.column = new ArrayList<String>();
    }

    public TableRow(List<String> data) {
        this.column = data;
    }

    public List<String> getData() {
        return column;
    }

    public void setData(List<String> data) {
        this.column = data;
    }


}