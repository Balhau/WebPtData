package org.pub.pt.data.sources.bdp.domain;

public class BdpLinks {
    private final String next;
    private final String previous;

    public BdpLinks(String next, String previous) {
        this.next = next;
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }
}
