package org.pub.pt.data.sources.ipma.domain.api;

import java.util.List;

public class Warnings {
    private final String owner;
    private final String country;
    private final List<Warning> data;
    private final String dataAtualiza;

    public Warnings(String owner, String country, List<Warning> data, String dataAtualiza) {
        this.owner = owner;
        this.country = country;
        this.data = data;
        this.dataAtualiza = dataAtualiza;
    }

    public String getOwner() {
        return owner;
    }

    public String getCountry() {
        return country;
    }

    public List<Warning> getData() {
        return data;
    }

    public String getDataAtualiza() {
        return dataAtualiza;
    }
}
