package org.pub.pt.data.sources.tap.domain.global;

import java.util.List;

public class TapGlobalInfo {
    private final List<Airport> Airports;
    private final List<Country> Countries;
    private final List<String> MarketList;
    private final List<State> States;
    private final List<Airport> MarketAirports;

    public TapGlobalInfo(
            final List<Airport> airports,
            final List<Country> countries,
            final List<String> marketList,
            final List<State> states,
            final List<Airport> marketAirports
    ) {
        this.Airports = airports;
        this.Countries = countries;
        this.MarketList = marketList;
        this.States = states;
        this.MarketAirports = marketAirports;
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
