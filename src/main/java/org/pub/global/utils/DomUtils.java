package org.pub.global.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.global.configs.GlobalConfigs;
import org.pub.global.configs.HtmlTag;
import org.pub.global.domain.TableData;
import org.pub.global.domain.TableRow;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Utility class with a bunch of DOM utility methods
 *
 * @author balhau
 */
public class DomUtils {

    private static final String RESTRICTED_HEADERS_ALLOW = "sun.net.http.allowRestrictedHeaders";

    static {
        System.setProperty(RESTRICTED_HEADERS_ALLOW, "true");
    }

    public static SSLContext getSSLIgnoreCertificatesContext() throws RuntimeException {
        SSLContext sc;
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs,
                                               String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs,
                                               String authType) {
                }
            }};

            // Install the all-trusting trust manager
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (Exception ex) {
            throw new RuntimeException("Error while initializing SSLContext");
        }

        return sc;
    }

    /**
     * Convert a table dom element into a {@link TableData} domain object
     *
     * @param table {@link Element}
     * @return {@link TableData}
     */
    public static TableData tableElementToTableData(Element table) {
        TableData tb = new TableData();
        Elements rows = table.getElementsByTag(HtmlTag.TR);
        for (Element row : rows) {
            Elements aux = row.getElementsByTag(HtmlTag.TD);
            Elements cols = aux.size() == 0 ? row.getElementsByTag(HtmlTag.TH) : aux;
            TableRow trow = new TableRow();
            for (Element col : cols) {
                trow.getData().add(col.text());
            }
            tb.getRows().add(trow);
        }
        return tb;
    }


    public static String getRawString(String path) throws RuntimeException {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpGet request = new HttpGet(path);
                // add request headers
                request.addHeader(HttpHeaders.USER_AGENT, GlobalConfigs.USER_AGENT);
                request.addHeader(HttpHeaders.ACCEPT, "*/*");

                CloseableHttpResponse response = httpClient.execute(request);

                try {
                    HttpEntity entity = response.getEntity();
                    String data = null;
                    if (entity != null) {
                        data = EntityUtils.toString(entity);
                    }
                    return data;

                } finally {
                    response.close();
                }
            } finally {
                httpClient.close();
            }

        } catch (Exception ex) {
            throw new RuntimeException("Error getting page", ex);
        }
    }

    /**
     * Default way to establish a http connection
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static Connection getHTML(String url) throws Exception {
        return Jsoup.connect(url)
                .userAgent(GlobalConfigs.USER_AGENT)
                .header("accept-content", "application/json")
                .sslSocketFactory(getSSLIgnoreCertificatesContext().getSocketFactory())
                .timeout(GlobalConfigs.CONNECTION_TIMEOUT);
    }

    /**
     * Method that extracts a List of {@link String[]} elements from a {@link ScriptObjectMirror}
     *
     * @param scm {@link ScriptObjectMirror} Object from nashorn framework with javascript contents
     * @return {@link List} Of {@link String[]}
     */
    @SuppressWarnings("restriction")
    public static List<String[]> stringTableFromScriptObjectMirror(ScriptObjectMirror scm) {
        Collection<Object> vals = scm.values();
        List<String[]> ls = new ArrayList<String[]>();
        for (Object strList : vals) {
            ls.add(stringArrayFromScriptObjectMirror(((ScriptObjectMirror) strList)));
        }
        return ls;
    }

    /**
     * Method that extracts a {@link String[]} from a {@link ScriptObjectMirror} element
     *
     * @param scm {@link ScriptObjectMirror} that represents an array of {@link String}
     * @return {@link String[]}
     */
    @SuppressWarnings("restriction")
    public static String[] stringArrayFromScriptObjectMirror(ScriptObjectMirror scm) {
        Collection<Object> vals = scm.values();
        String[] l = new String[vals.size()];
        int i = 0;
        for (Object str : vals) {
            l[i] = "" + str;
            i++;
        }
        return l;
    }
}
