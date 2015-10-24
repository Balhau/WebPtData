package org.pt.pub.data.sources.anacom.domain;

import org.pt.pub.data.sources.AbstractData;

/**
 * This represents the description of a ISP contract.
 * Created by vitorfernandes on 10/24/15.
 */
public class Tarifa extends AbstractData{
    private final String prestador;
    private final String tarifario;
    private final String tecnologia;
    private final String fidelizacao;
    private final String velocidade;
    private final String limites;
    private final String custoMensal;

    public Tarifa(
            String prestador,
            String tarifario,
            String tecnologia,
            String fidelizacao,
            String velocidade,
            String limites,
            String custoMensal
    ){
        this.prestador=prestador;this.tarifario=tarifario;
        this.tecnologia=tecnologia;this.fidelizacao=fidelizacao;
        this.velocidade=velocidade;this.limites=limites;
        this.custoMensal=custoMensal;
    }

    public String getPrestador() {
        return prestador;
    }

    public String getTarifario() {
        return tarifario;
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

    public String getCustoMensal() {
        return custoMensal;
    }
}
