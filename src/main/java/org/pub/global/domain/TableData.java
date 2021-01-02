package org.pub.global.domain;

import org.pub.pt.data.sources.domain.AbstractData;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tableData")
public class TableData extends AbstractData {
    private List<TableRow> rows;

    public TableData() {
        this.rows = new ArrayList<TableRow>();
    }

    public TableData(List<TableRow> rows) {
        this.rows = rows;
    }

    public List<TableRow> getRows() {
        return rows;
    }

    public void setRows(List<TableRow> rows) {
        this.rows = rows;
    }


}
