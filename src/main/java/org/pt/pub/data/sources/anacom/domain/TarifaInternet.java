package org.pt.pub.data.sources.anacom.domain;

import org.pt.pub.data.sources.AbstractData;

/**
 * This represents the description of a ISP contract.
 * Created by vitorfernandes on 10/24/15.
 */
public class TarifaInternet extends TarifaBase{
    private final String tecnologia;
    private final String fidelizacao;
    private final String velocidade;
    private final String limites;

    public TarifaInternet(
            String prestador,
            String tarifario,
            String tecnologia,
            String fidelizacao,
            String velocidade,
            String limites,
            String custoMensal
    ){
        super(prestador,tarifario,custoMensal);
        this.tecnologia=tecnologia;this.fidelizacao=fidelizacao;
        this.velocidade=velocidade;this.limites=limites;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public String getFidelizacao() {
        return fidelizacao;
    }

    public String getVelocidade() {
        return velocidade;
    }

    public String getLimites() {
        return limites;
    }
}
