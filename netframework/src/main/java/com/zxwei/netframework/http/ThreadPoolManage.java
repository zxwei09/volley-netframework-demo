package com.zxwei.netframework.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zxwei on 2018/11/23.
 */

public class ThreadPoolManage {

    private LinkedBlockingQueue<Runnable>  queue = new LinkedBlockingQueue<Runnable>();

    private static ThreadPoolManage instance = new ThreadPoolManage();

    //把队列中的任务放到线程池
    private ThreadPoolExecutor threadPoolExecutor;

    //添加任务
    public void execute(Runnable runnable) {
           if(runnable != null) {
               try {
                   queue.put(runnable);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
    }

    private ThreadPoolManage() {
        threadPoolExecutor = new ThreadPoolExecutor(4, 20, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), rejectedExecutionHandler);
        //开启传送带，让程序运行起来
        threadPoolExecutor.execute(runnable);
    }


    public static ThreadPoolManage getInstance() {
        return instance;
    };


    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                 if(runnable != null) {//超时的线程重新添加到队列中
                     try {
                         queue.put(runnable);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
        }
    };


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while(true) {
                Runnable runnable = null;
                try {
                    runnable = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(runnable != null) {
                    threadPoolExecutor.execute(runnable);
                }
            }
        }
    };




}
