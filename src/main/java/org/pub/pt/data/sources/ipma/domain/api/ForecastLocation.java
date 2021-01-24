package org.pub.pt.data.sources.ipma.domain.api;

public class ForecastLocation {
    private final int idRegiao;
    private final int idDistrito;
    private final String idAreaAviso;
    private final int globalIdLocal;
    private final int idConcelho;
    private final String latitude;
    private final String longitude;
    private final String local;

    public ForecastLocation(int idRegiao, int idDistrito, String idAreaAviso, int globalIdLocal, int idConcelho, String latitude, String longitude, String local) {
        this.idRegiao = idRegiao;
        this.idDistrito = idDistrito;
        this.idAreaAviso = idAreaAviso;
        this.globalIdLocal = globalIdLocal;
        this.idConcelho = idConcelho;
        this.latitude = latitude;
        this.longitude = longitude;
        this.local = local;
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public String getIdAreaAviso() {
        return idAreaAviso;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public int getIdConcelho() {
        return idConcelho;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLocal() {
        return local;
    }
}
