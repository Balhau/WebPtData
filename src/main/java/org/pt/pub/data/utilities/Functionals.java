package org.pt.pub.data.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitorfernandes on 10/17/15.
 */
public class Functionals {

    /**
     * Static method that builds a map functional call
     * @param data
     * @param mapper
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T,U> List<T> map(List<U> data, Transform<T,U> mapper){
        List<T> nlist=new ArrayList<>(data.size());
        for(U e : data){
            nlist.add(mapper.transform(e));
        }
        return nlist;
    }

    public static <T> void call(List<T> data,Callback<T> callback){
        for(T e : data){
            callback.call(e);
        }
    }
}
