package org.pub.global.utils;

import java.util.function.Predicate;

/**
 * Created by vitorfernandes on 12/24/16.
 */
public class PredicateDictionary {
    public static Predicate<Object> NOT_NULL = new Predicate<Object>() {
        @Override
        public boolean test(Object o) {
            return o != null;
        }
    };
}
