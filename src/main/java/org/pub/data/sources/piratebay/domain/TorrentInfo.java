package org.pub.data.sources.piratebay.domain;

/**
 * Domain object for torrent files information
 * Created by balhau on 9/2/16.
 */
public class TorrentInfo {

    private final String id;
    private final String name;
    private final String info_hash;
    private final String leechers;
    private final String seeders;
    private final String num_files;
    private final String size;
    private final String username;
    private final String added;
    private final String status;
    private final String category;
    private final String imdb;

    public TorrentInfo(
            String id, String name, String info_hash, String leechers,
            String seeders, String num_files, String size, String username,
            String added, String status, String category, String imdb
    ) {
        this.id = id;
        this.name = name;
        this.info_hash = info_hash;
        this.leechers = leechers;
        this.seeders = seeders;
        this.num_files = num_files;
        this.size = size;
        this.username = username;
        this.added = added;
        this.status = status;
        this.category = category;
        this.imdb = imdb;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo_hash() {
        return info_hash;
    }

    public String getLeechers() {
        return leechers;
    }

    public String getSeeders() {
        return seeders;
    }

    public String getNum_files() {
        return num_files;
    }

    public String getSize() {
        return size;
    }

    public String getUsername() {
        return username;
    }

    public String getAdded() {
        return added;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getImdb() {
        return imdb;
    }
}
