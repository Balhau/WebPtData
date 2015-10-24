package org.pt.pub.data.sources.anacom.domain;

/**
 * Domain object for the television services available in the Portuguese market
 * Created by vitorfernandes on 10/24/15.
 */
public class TarifaTelevisao extends TarifaBase{
    private final String tecnologia;
    private final String numeroCanais;
    private final String fidelizacao;

    public TarifaTelevisao(
            String prestador,
            String tarifario,
            String custoMensal,
            String tecnologia,
            String numeroCanais,
            String fidelizacao
    ){
        super(prestador,tarifario,custoMensal);
        this.tecnologia=tecnologia;
        this.numeroCanais=numeroCanais;this.fidelizacao=fidelizacao;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public String getNumeroCanais() {
        return numeroCanais;
    }

    public String getFidelizacao() {
        return fidelizacao;
    }
}
