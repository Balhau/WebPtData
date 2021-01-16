package org.pub.pt.data.sources.postalcode;

import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.postalcode.domain.PostalCode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Get Postal code information
 */
public class PostalCodes {

    private static final String POSTAL_CODES_HOST = "https://www.codigo-postal.pt";
    private static final String POSTAL_CODES_LOCAL_PATTERN = "/?rua=%s&local=%s";
    private static final String POSTAL_CODES_CODE_PATTERN = "/?cp4=%s&cp3=%s";

    public List<PostalCode> getPostalCodesByLocal(final String street, final String locality) throws Exception {
        final String postalCodesURL = POSTAL_CODES_HOST + String.format(POSTAL_CODES_LOCAL_PATTERN, street == null ? "" : street, locality == null ? "" : locality);
        Connection con = DomUtils.getHTML(postalCodesURL);
        return parsePipeline(con);
    }

    public List<PostalCode> getPostalCodesByCode(final String major, final String minor) throws Exception {
        final String postalCodesURL = POSTAL_CODES_HOST + String.format(POSTAL_CODES_CODE_PATTERN, major == null ? "" : major, minor == null ? "" : minor);
        Connection con = DomUtils.getHTML(postalCodesURL);
        return parsePipeline(con);
    }

    private List<PostalCode> parsePipeline(Connection connection) throws Exception {
        return connection.get()
                .getElementsByClass("places")
                .get(0)
                .children()
                .stream()
                .map(this::parsePlace)
                .filter(p -> p != null)
                .collect(Collectors.toList());
    }

    private PostalCode parsePlace(Element placeElement) {
        try {
            Elements spans = placeElement.getElementsByTag("span");
            final String gps = spans.get(0).text().split("GPS: ")[1];
            final String desc = spans.get(1).getElementsByClass("local").text();
            String[] codes = spans.get(1).getElementsByClass("cp").text().split("-");
            int major = Integer.parseInt(codes[0]);
            int minor = Integer.parseInt(codes[1]);
            return new PostalCode(gps, desc, major, minor);
        } catch (Exception ex) {
            return null;
        }
    }
}
