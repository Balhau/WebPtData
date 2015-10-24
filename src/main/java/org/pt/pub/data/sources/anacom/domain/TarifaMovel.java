package org.pt.pub.data.sources.anacom.domain;

/**
 * Created by vitorfernandes on 10/24/15.
 */
public class TarifaMovel extends TarifaBase{
    private final String tipoTarifario;
    private final String custoPrimeiroMinuto;
    private final String custoSms;
    private final String custoInternetMovel;

    public TarifaMovel(
            String prestador,
            String tarifario,
            String customensal,
            String tipotarifario,
            String custoPrimeiroMinuto,
            String custoSms,
            String custoInternetMovel
    ){
        super(prestador,tarifario,customensal);
        this.tipoTarifario=tipotarifario;this.custoInternetMovel=custoInternetMovel;
        this.custoPrimeiroMinuto=custoPrimeiroMinuto;this.custoSms=custoSms;
    }

    public String getTipoTarifario() {
        return tipoTarifario;
    }

    public String getCustoPrimeiroMinuto() {
        return custoPrimeiroMinuto;
    }

    public String getCustoSms() {
        return custoSms;
    }

    public String getCustoInternetMovel() {
        return custoInternetMovel;
    }
}
