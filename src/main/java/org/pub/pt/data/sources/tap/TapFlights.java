package org.pub.pt.data.sources.tap;

import org.pub.pt.data.sources.tap.domain.TapCountry;
import org.pub.pt.data.sources.tap.domain.TapLanguage;

/**
 * API for the Transportes Aéreos Portugueses or TAP until recently a public institution that operates
 * in the flight markets. With this API you can search for flights, get the time and prices for each one
 *
 * @author balhau
 */
public class TapFlights {
    private static final String TAP_HOST = "https://www.flytap.com";
    private static final String TAP_GLOBAL_INFO_PATTERN = "/api/general/masterdata?sc_mark=%s&sc_lang=%s";


    /**
     * Build url for global info based on country and language inputs
     *
     * @param country  Example PT
     * @param language Example pt-PT
     * @return String
     */
    private String globalInfoURL(final TapCountry country, final TapLanguage language) {
        return String.format(TAP_GLOBAL_INFO_PATTERN, country.getCountryCode(), language.getCodeLanguage());
    }
}
