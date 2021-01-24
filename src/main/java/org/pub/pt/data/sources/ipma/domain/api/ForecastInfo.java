package org.pub.pt.data.sources.ipma.domain.api;

public class ForecastInfo {
    private final String tMin;
    private final int idFfxVento;
    private final String dataUpdate;
    private final String tMax;
    private final String iUv;
    private final String intervaloHora;
    private final int idTipoTempo;
    private final int idIntensidadePrecipita;
    private final int globalIdLocal;
    private final String probabilidadePrecipita;
    private final int idPeriodo;
    private final String dataPrev;
    private final String ddVento;

    public ForecastInfo(String tMin, int idFfxVento, String dataUpdate, String tMax, String iUv, String intervaloHora, int idTipoTempo, int idIntensidadePrecipita, int globalIdLocal, String probabilidadePrecipita, int idPeriodo, String dataPrev, String ddVento) {
        this.tMin = tMin;
        this.idFfxVento = idFfxVento;
        this.dataUpdate = dataUpdate;
        this.tMax = tMax;
        this.iUv = iUv;
        this.intervaloHora = intervaloHora;
        this.idTipoTempo = idTipoTempo;
        this.idIntensidadePrecipita = idIntensidadePrecipita;
        this.globalIdLocal = globalIdLocal;
        this.probabilidadePrecipita = probabilidadePrecipita;
        this.idPeriodo = idPeriodo;
        this.dataPrev = dataPrev;
        this.ddVento = ddVento;
    }

    public String gettMin() {
        return tMin;
    }

    public int getIdFfxVento() {
        return idFfxVento;
    }

    public String getDataUpdate() {
        return dataUpdate;
    }

    public String gettMax() {
        return tMax;
    }

    public String getiUv() {
        return iUv;
    }

    public String getIntervaloHora() {
        return intervaloHora;
    }

    public int getIdTipoTempo() {
        return idTipoTempo;
    }

    public int getIdIntensidadePrecipita() {
        return idIntensidadePrecipita;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public String getProbabilidadePrecipita() {
        return probabilidadePrecipita;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public String getDataPrev() {
        return dataPrev;
    }

    public String getDdVento() {
        return ddVento;
    }
}
