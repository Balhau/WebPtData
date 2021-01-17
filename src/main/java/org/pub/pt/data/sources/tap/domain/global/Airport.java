package org.pub.pt.data.sources.tap.domain.global;

import java.util.List;

public class Airport {
    private final String IATACode;
    private final String Tag;
    private final String Market;
    private final String City;
    private final String DestinationId;
    private final List<String> Connections;
    private final List<String> ConnectionsVictoria;
    private final List<String> ConnectionsTAP;
    private final List<String> ConnectionsStopover;
    private final boolean MilesAndCash;
    private final boolean YoungAdults;
    private final boolean OnlyDestination;
    private final boolean IsAllAirportsOfCityOpen;

    public Airport(String iataCode, String tag, String market, String city, String destinationId, List<String> connections, List<String> connectionsVictoria, List<String> connectionsTap, List<String> connectionsStopover, boolean milesAndCash, boolean youngAdults, boolean onlyDestination, boolean isAllAirportsOfCityOpen) {
        this.IATACode = iataCode;
        this.Tag = tag;
        this.Market = market;
        this.City = city;
        this.DestinationId = destinationId;
        this.Connections = connections;
        this.ConnectionsVictoria = connectionsVictoria;
        this.ConnectionsTAP = connectionsTap;
        this.ConnectionsStopover = connectionsStopover;
        this.MilesAndCash = milesAndCash;
        this.YoungAdults = youngAdults;
        this.OnlyDestination = onlyDestination;
        this.IsAllAirportsOfCityOpen = isAllAirportsOfCityOpen;
    }

    public String getIATACode() {
        return IATACode;
    }

    public String getTag() {
        return Tag;
    }

    public String getMarket() {
        return Market;
    }

    public String getCity() {
        return City;
    }

    public String getDestinationId() {
        return DestinationId;
    }

    public List<String> getConnections() {
        return Connections;
    }

    public List<String> getConnectionsVictoria() {
        return ConnectionsVictoria;
    }

    public List<String> getConnectionsTAP() {
        return ConnectionsTAP;
    }

    public List<String> getConnectionsStopover() {
        return ConnectionsStopover;
    }

    public boolean isMilesAndCash() {
        return MilesAndCash;
    }

    public boolean isYoungAdults() {
        return YoungAdults;
    }

    public boolean isOnlyDestination() {
        return OnlyDestination;
    }

    public boolean isAllAirportsOfCityOpen() {
        return IsAllAirportsOfCityOpen;
    }
}
