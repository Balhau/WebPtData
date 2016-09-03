package org.pub.pt.data.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class with utility methods
 * Created by vitorfernandes on 10/25/15.
 */
public class Utils {
    /**
     * Utility method that will return a random element from the list
     * @param list A generic list
     * @param <T> Type of the entries
     * @return a generic entry
     */
    public static <T> T pickRandom(List<T> list){
        return list.get((int) Math.floor(Math.random()*list.size()));
    }

    /**
     * Utility class that help us to build date strings that can be used in TAP data provider
     * @param date Date object
     * @return String representation
     */
    public static String formatDateForTAP(Date date){
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }
}
