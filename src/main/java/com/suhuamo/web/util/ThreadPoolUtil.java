package com.suhuamo.web.util;

import java.util.concurrent.*;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/03/11
 * 线程池工具-单例模式
 */
public class ThreadPoolUtil {
    /**
     * 初始线程数-即最小线程数,默认使用CPU的数量
     * System.out.println(Runtime.getRuntime().availableProcessors()); //输出本机CPU的数量,是一个数字
     */
    public static final int CORE_POOL_SIZE = cpuNum();
    /**
     * 最大线程数-一般线程池大小设置为 2N+1 (N为CPU数量)
     */
    public static final int MAX_POOL_SIZE = desiredThreadNum();
    /**
     * 空闲时间-多久回收已创建但未使用了的线程
     */
    public static final int THREAD_WAIT_TIME = 60 * 30;
    /**
     * 线程队列长度-即允许多少个线程启用+等待
     */
    public static final int THREAD_QUEUE_SIZE = 30;

    /**
     *  获取一个线程使用，使用完成后记得关闭！！！
     * @param
     * @return ExecutorService
     */
    public static ExecutorService getThread() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, // 核心线程
                MAX_POOL_SIZE, // 最大线程
                THREAD_WAIT_TIME, // 空闲等待时间
                TimeUnit.SECONDS, // 等待时间单位
                new ArrayBlockingQueue<>(THREAD_QUEUE_SIZE), // 工作队列
                Executors.defaultThreadFactory(), // 默认的拒绝策略
                new ThreadPoolExecutor.DiscardOldestPolicy()); // 线程工厂-创建线程
    }

    public static ThreadPoolExecutor getThread123() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, // 核心线程
                MAX_POOL_SIZE, // 最大线程
                THREAD_WAIT_TIME, // 空闲等待时间
                TimeUnit.SECONDS, // 等待时间单位
                new ArrayBlockingQueue<>(THREAD_QUEUE_SIZE), // 工作队列
                Executors.defaultThreadFactory(), // 默认的拒绝策略
                new ThreadPoolExecutor.DiscardOldestPolicy()); // 线程工厂-创建线程
    }

    /**
     * 理想的线程数，使用 2倍cpu核心数 + 1
     */
    private static int desiredThreadNum() {
        return Runtime.getRuntime().availableProcessors() * 2 + 1;
    }

    /**
     * cpu的核心数
     */
    private static int cpuNum() {
        return Runtime.getRuntime().availableProcessors();
    }

    private ThreadPoolUtil() {
    }
}
