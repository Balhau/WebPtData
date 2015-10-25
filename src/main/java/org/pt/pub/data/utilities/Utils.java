package org.pt.pub.data.utilities;

import java.util.List;

/**
 * Created by vitorfernandes on 10/25/15.
 */
public class Utils {
    public static <T> T pickRandom(List<T> list){
        return list.get((int) Math.floor(Math.random()*list.size()));
    }
}
