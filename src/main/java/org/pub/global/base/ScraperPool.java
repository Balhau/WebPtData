package org.pub.global.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Centralized pool. This was done to avoid scattering thread pools around the code base and spend cpu when the beans are instantiated
 * Created by vitorfernandes on 9/4/16.
 */
public class ScraperPool {
    public static final int THREAD_POOL_SIZE=20;

    private static final ExecutorService pool;

    static{
        pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public static ExecutorService getPool(){
        return pool;
    }
}
