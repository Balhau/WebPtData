package org.pub.global.base;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vitorfernandes on 9/4/16.
 */
public abstract class ThreadedScrapper {
    public static final int THREAD_POOL_SIZE=20;

    protected ExecutorService pool;

    public ThreadedScrapper(){
        pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }
}
