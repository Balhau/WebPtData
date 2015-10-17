package org.pt.pub.data;

import org.pt.pub.data.sources.amusing.chucknorris.ChuckNorris;

import static org.pt.pub.data.utilities.Functionals.call;

/**
 * Created by vitorfernandes on 10/17/15.
 */
public class chuckNorris {
    public static void main(String[] args) {
        ChuckNorris chuckNorris=new ChuckNorris();
        call(chuckNorris.getFacts(0), (a) -> {
            System.out.println(a);
        });
    }
}
