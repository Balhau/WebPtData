package org.pub.pt.data.sources.tap;

import com.google.gson.Gson;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.tap.domain.global.TapCountry;
import org.pub.pt.data.sources.tap.domain.global.TapGlobalInfo;
import org.pub.pt.data.sources.tap.domain.global.TapLanguage;

/**
 * API for the Transportes Aéreos Portugueses or TAP until recently a public institution that operates
 * in the flight markets. With this API you can search for flights, get the time and prices for each one
 *
 * @author balhau
 */
public class TapFlights {
    private static final String TAP_HOST = "https://www.flytap.com";
    private static final String TAP_GLOBAL_INFO_PATTERN = "/api/general/masterdata?sc_mark=%s&sc_lang=%s";


    public TapGlobalInfo getGlobalInfo() {
        return getGlobalInfo(TapCountry.PT, TapLanguage.PT_PT);
    }

    public TapGlobalInfo getGlobalInfo(final TapCountry country, final TapLanguage language) {
        String globalInfoURL = globalInfoURL(country, language);
        String jsonData = DomUtils.getRawString(globalInfoURL);
        TapGlobalInfo globalInfo = new Gson().fromJson(jsonData,TapGlobalInfo.class);
        return globalInfo;
    }

    /**
     * Build url for global info based on country and language inputs
     *
     * @param country  {@link TapCountry}
     * @param language {@link TapLanguage}
     * @return String
     */
    private String globalInfoURL(final TapCountry country, final TapLanguage language) {
        return String.format(TAP_HOST + TAP_GLOBAL_INFO_PATTERN, country.getCountryCode(), language.getCodeLanguage());
    }
}
