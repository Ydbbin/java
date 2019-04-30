package com.ydbbin.advanced.multiThread.waitAndNotify.timeOutPoolConnection;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 用linkedlist模拟数据库连接池
 * 提供获取连接、回收连接方法
 */
public class DBPool {

    private static LinkedList<Connection> pools = new LinkedList<Connection>();

    public DBPool(int conSize) {
        if (conSize > 0) {
            for (int i = 0; i <= conSize; i++) {
                pools.add(Myconnection.fetchConnection());
            }
        }
    }

    /**
     * 获取数据库连接
     * 提供超时处理
     * 如果拿不到，返回null
     *
     * @param timeOutMillis：超时时间
     * @return
     */
    public Connection getConnection(long timeOutMillis) throws InterruptedException {
        synchronized (pools) {
            if (timeOutMillis < 0) {
                //建议使用while，wait返回时候执行完成代码块，会重新判断
                while (pools.isEmpty()) {
                    pools.wait();
                }
                return pools.removeFirst();
            } else {
                long timeOutSj = System.currentTimeMillis() + timeOutMillis;
                long remain = timeOutMillis;
                if (pools.isEmpty() && remain > 0) {
                    pools.wait(remain);
                    remain = timeOutSj - System.currentTimeMillis();
                }
                //如果是超时跳出来的再次判断
                Connection conn = null;
                if (!pools.isEmpty()) {
                    conn = pools.removeFirst();
                }
                return conn;

            }
        }
    }

    /**
     * 回收连接
     * 需要唤醒正在获取连接的线程
     */
    public void releaseConnection(Connection conn){
            synchronized(pools){
                pools.addLast(conn);
                pools.notifyAll();
            }
    }
}
