package com.ydbbin.advanced.multiThread.waitAndNotify.timeOutPoolConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 启动多个线程进行获取，每个线程进行多次获取线程
 * 通过线程工具CountDownLatch进行汇总失败、成功信息
 */
public class DBPoolsTest {
    static DBPool pool = new DBPool(10);
    //多个线程
    static int countThread = 50;
    static AtomicInteger goSum = new AtomicInteger();
    static AtomicInteger notGoSum = new AtomicInteger();
    //每个线程发送次数
    static int csPerThread = 20;
    static CountDownLatch countDownLatch = new CountDownLatch(50);

    public static void main(String[] args) throws Exception {


        for (int i = 0; i <countThread; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {


                    Connection conn = null;
                    for (int i = 0; i <csPerThread; i++) {
                        try {
                            conn = pool.getConnection(1000);
                            if (conn != null) {
                                conn.createStatement();
                                conn.commit();
                                goSum.incrementAndGet();
                            } else {
                                notGoSum.incrementAndGet();
                                System.out.println("线程：" + Thread.currentThread().getId() + "等待超时!");
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            if (conn != null) {
                                pool.releaseConnection(conn);
                            }
                        }

                    }
                    countDownLatch.countDown();
                }
            }).start();

        }
        //闭锁
        countDownLatch.await();
        System.out.println("总共尝试了: " + (countThread * csPerThread));
        System.out.println("拿到连接的次数：  " + goSum);
        System.out.println("没能连接的次数： " + notGoSum);

    }

}
