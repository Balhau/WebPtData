package org.pub.pt.data.sources.tap.domain.global;

import java.util.List;

public class TapGlobalInfo {
    private final List<Airport> Airports;
    private final List<Country> Countries;
    private final List<String> MarketList;
    private final List<State> States;

    public TapGlobalInfo(List<Airport> airports, List<Country> countries, List<String> marketList, List<State> states) {
        this.Airports = airports;
        this.Countries = countries;
        this.MarketList = marketList;
        this.States = states;
    }

    public List<Airport> getAirports() {
        return Airports;
    }

    public List<Country> getCountries() {
        return Countries;
    }

    public List<String> getMarketList() {
        return MarketList;
    }

    public List<State> getStates() {
        return States;
    }
}
