package org.pt.pub.data;

import org.pt.pub.data.sources.quotes.chucknorris.ChuckNorris;

import static org.pt.pub.data.utilities.Functionals.call;

/**
 * Created by vitorfernandes on 10/17/15.
 */
public class chuckNorris {
    public static void main(String[] args) throws Exception{
        ChuckNorris chuckNorris=new ChuckNorris();
        call(chuckNorris.getFacts(1), (a) -> {
            System.out.println(a);
        });
    }
}
