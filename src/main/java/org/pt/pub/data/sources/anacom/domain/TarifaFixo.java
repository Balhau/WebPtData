package org.pt.pub.data.sources.anacom.domain;

/**
 * Created by vitorfernandes on 10/24/15.
 */
public class TarifaFixo extends TarifaBase{

    private final String tipoTarifario;
    private final String custoPrimeiroMinutoRedesFixas;
    private final String custoPrimeiroMinutoRedesMovel;

    public TarifaFixo(
            String prestador,
            String tarifario,
            String customensal,
            String tipotarifario,
            String custoPrimeiroMinutoRedesFixas,
            String custoPrimeiroMinutoRedesMovel
            ){
        super(prestador,tarifario,customensal);
        this.tipoTarifario=tipotarifario;this.custoPrimeiroMinutoRedesFixas=custoPrimeiroMinutoRedesFixas;
        this.custoPrimeiroMinutoRedesMovel=custoPrimeiroMinutoRedesMovel;
    }

    public String getTipoTarifario() {
        return tipoTarifario;
    }

    public String getCustoPrimeiroMinutoRedesFixas() {
        return custoPrimeiroMinutoRedesFixas;
    }

    public String getCustoPrimeiroMinutoRedesMovel() {
        return custoPrimeiroMinutoRedesMovel;
    }
}
