package org.pt.pub.data.sources.anacom.domain;

import org.pt.pub.data.sources.AbstractData;

/**
 * Created by vitorfernandes on 10/24/15.
 */
public abstract class TarifaBase extends AbstractData{
    protected final String prestador;
    protected final String tarifario;
    protected final String customensal;

    public TarifaBase(String prestador, String tarifario,String customensal){
        this.customensal=customensal;this.tarifario=tarifario;
        this.prestador=prestador;
    }

    public String getPrestador() {
        return prestador;
    }

    public String getTarifario() {
        return tarifario;
    }

    public String getCustomensal() {
        return customensal;
    }
}
