package com.deodio.core.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.deodio.core.utils.AppUtils;

public class ThreadPool {

    private static int corePoolSize;
    private static int maximumPoolSize;
    private static int keepAliveTime;
    private static int capacity;
    private static ThreadPoolExecutor threadPool;

    static {
        corePoolSize = Integer.parseInt(AppUtils.THREAD_POOL_CORE_SIZE);
        maximumPoolSize = Integer.parseInt(AppUtils.THREAD_POOL_MAX_SIZE);
        keepAliveTime = Integer.parseInt(AppUtils.THREAD_POOL_KEEP_ALIVE_TIME);
        capacity = Integer.parseInt(AppUtils.THREAD_POOL_CAPACITY);
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue(
                        capacity));
    }

    public static void run(Runnable command) {
        threadPool.execute(command);
    }
}
