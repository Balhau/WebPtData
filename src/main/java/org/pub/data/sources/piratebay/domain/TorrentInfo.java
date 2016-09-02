package org.pub.data.sources.piratebay.domain;

/**
 * Domain object for torrent files information
 * Created by vitorfernandes on 9/2/16.
 */
public class TorrentInfo {

    private final String name;
    private final String magnetLink;
    private final String torrentLink;
    private final String date;
    private final int seeders;
    private final int leechers;

    public TorrentInfo(
        String name,String magnetLink,String torrentLink,
        String date,int seeders,int leechers
    ){
        this.name=name;this.magnetLink=magnetLink;
        this.torrentLink=torrentLink;this.date=date;
        this.seeders=seeders;this.leechers=leechers;
    }

    public String getName() {
        return name;
    }

    public String getMagnetLink() {
        return magnetLink;
    }

    public String getTorrentLink() {
        return torrentLink;
    }

    public String getDate() {
        return date;
    }

    public int getSeeders() {
        return seeders;
    }

    public int getLeechers() {
        return leechers;
    }
}
