package org.pt.pub.data.sources.amusing.chucknorris;

import org.pt.pub.data.sources.AbstractDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Here we will implement the scrapper for the chucknorris jokes
 * Created by vitorfernandes on 10/11/15.
 */
public class ChuckNorris extends AbstractDataSource{

    private final String CHUCK_URL_BASE="http://www.chucknorrisfacts.com";
    private final String SEARCH_PATH=CHUCK_URL_BASE+"search/node/%s%s";
    private final String PAGINATED_PATH=CHUCK_URL_BASE+"all-chuck-norris-facts%s";

    public ChuckNorris(){

    }

    /**
     * Get the chuck norris facts in a paginated way
     * @param number
     */
    public List<String> getFacts(int number){
        List<String> facts=new ArrayList<>();
        return facts;
    }

    public List<String> getFacts(String searchKey,int number){
        List<String> facts=new ArrayList<>();
        return facts;
    }

    /**
     * This will return the url for the paginated path given the number of the page
     * @param numberPage {link @Integer} number of the path
     * @return
     */
    private String getPaginatedPath(int numberPage){
        return String.format(PAGINATED_PATH,
                (numberPage==0) ? "" : "?page="+numberPage
        );
    }
}
