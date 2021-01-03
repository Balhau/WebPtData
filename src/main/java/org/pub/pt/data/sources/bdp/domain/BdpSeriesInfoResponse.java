package org.pub.pt.data.sources.bdp.domain;

import java.util.List;

public class BdpSeriesInfoResponse {
    private final BdpLinks links;
    private final List<BdpSeriesInfo> data;

    public BdpSeriesInfoResponse(BdpLinks links, List<BdpSeriesInfo> data) {
        this.links = links;
        this.data = data;
    }

    public BdpLinks getLinks() {
        return links;
    }

    public List<BdpSeriesInfo> getData() {
        return data;
    }
}
