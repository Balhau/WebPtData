package org.pub.global.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Centralized pool. This was done to avoid scattering thread pools around the code base and spend
 * cpu when the beans are instantiated
 */
public class ScraperPool {
    public static final int THREAD_POOL_SIZE = 20;

    private final ExecutorService pool;
    private static Object singletonLock=new Object();
    private static ScraperPool INSTANCE;

    private ScraperPool(final int poolSize){
        this.pool=Executors.newFixedThreadPool(poolSize);
    }

    public static ExecutorService getPool() {
        if(INSTANCE==null){
            synchronized (singletonLock){
                if(INSTANCE==null){
                    INSTANCE=new ScraperPool(THREAD_POOL_SIZE);
                }
            }
        }
        return INSTANCE.pool;
    }
}
