package org.pub.data.sources.yts.domain;

/**
 * Created by vitorfernandes on 9/4/16.
 */
public class TorrentLink {
    private final String url;
    private final String name;

    public TorrentLink(String url,String name){
        this.name=name;this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
