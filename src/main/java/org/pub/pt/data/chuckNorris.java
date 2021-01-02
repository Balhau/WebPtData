package org.pub.pt.data;

import org.pub.pt.data.sources.quotes.chucknorris.ChuckNorris;
import org.pub.pt.data.utilities.Functionals;

/**
 * Created by balhau on 10/17/15.
 */
public class chuckNorris {
    public static void main(String[] args) throws Exception{
        ChuckNorris chuckNorris=new ChuckNorris();
        Functionals.call(chuckNorris.getFacts(2), (a) -> {
            System.out.println(a);
        });
    }
}
