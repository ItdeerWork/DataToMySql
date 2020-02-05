package cn.itdeer.core.type;

import cn.itdeer.common.config.Message;
import cn.itdeer.common.fields.FieldInterface;
import cn.itdeer.common.init.InitMessage;
import cn.itdeer.utils.ConnectionPool;
import cn.itdeer.utils.LogPrint;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static cn.itdeer.utils.Constants.FORMAT;

/**
 * Description : 指定点位类型配置发送数据核心
 * PackageName : cn.itdeer.core.type
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/14:54
 */
@Slf4j
public class PointTypeData extends Thread {

    /**
     * 应用配置信息
     */
    private Message message;
    private int timeFrequency;

    /**
     * 基本信息
     */
    private long dataNumber;
    private String table;

    private Integer batchSize;
    private Connection conn;
    private Statement statement;
    private String sql;

    /**
     * 数据中转集合
     */
    private Map<String, Object> living;
    private Map<Integer, List<Map<String, Object>>> map;

    /**
     * 构造函数
     *
     * @param message    配置信息实体
     * @param threadName 线程名称
     */
    public PointTypeData(Message message, String threadName) {
        super(threadName);
        this.message = message;

        table = message.getTable();
        batchSize = message.getBatchSize();
        dataNumber = message.getDataNumber();
        timeFrequency = message.getTimeFrequency();

        living = new HashMap<>();
    }


    /**
     * 覆盖线程Run方法
     */
    @Override
    public void run() {

        try {
            if (conn == null) {
                conn = ConnectionPool.INSTANCE.getConnection();
            }
            statement = conn.createStatement();
        } catch (SQLException e) {
            log.error("To obtain the abnormal information of the database connection as follows [{}]", e.getStackTrace());
        }

        /**
         * 开始时间
         */
        long startTime = System.currentTimeMillis();
        String startDate = FORMAT.format(new Date());
        long totleNumber = dataNumber;
        log.info("Start time of sending data [{}]", startDate);


        /**
         * 初始化数据值获取实例
         */
        map = new InitMessage().initPointsInstance(message.getPoints(), living);
        if (map.size() == 0)
            return;
        log.info("Send value instance initialization based on the point template generation");

        /**
         * 准备插入SQL的前半部分
         */
        StringBuffer sqlPrefix = new StringBuffer();
        sqlPrefix.append("INSERT INTO ").append(table).append(" (").append(message.getFieldMapping()).append(") VALUES (");
        sql = sqlPrefix.toString();


        /**
         * 发数
         */
        Boolean ifFinsh = sendData();
        if (ifFinsh) {
            log.info("Random generation of data is completed");

            /**
             * 结束时间
             */
            long endTime = System.currentTimeMillis();
            String endDate = FORMAT.format(new Date());
            log.info("End time of sending data [{}]", endDate);

            /**
             * 输出发数信息
             */
            LogPrint.outPrint(startTime, endTime, startDate, endDate, Thread.currentThread().getName(), totleNumber, table);
        }
    }

    /**
     * 发送数据
     *
     * @return 发送完成状态
     */
    private Boolean sendData() {
        int number = 0;
        String insertSql = sql;

        while (dataNumber > 0) {
            try {
                for (Integer num : map.keySet()) {
                    List<Map<String, Object>> ll = map.get(num);
                    for (Map<String, Object> mm : ll) {
                        for (String key : mm.keySet()) {
                            if (living.containsKey(mm.get(key))) {
                                insertSql = insertSql + "'" + ((FieldInterface) living.get(mm.get(key))).getValue() + "',";
                            } else {
                                insertSql = insertSql + "'" + mm.get(key) + "',";
                            }
                        }
                    }
                    insertSql = insertSql.substring(0, insertSql.lastIndexOf(",")) + ")";
                    statement.addBatch(insertSql);
                    insertSql = sql;
                    number++;

                    if (number >= batchSize) {
                        statement.executeBatch();
                        statement.clearBatch();
                        number = 0;
                    }
                }

                if (timeFrequency > 0) {
                    Thread.sleep(timeFrequency);
                }
                dataNumber--;
            } catch (Exception e) {
                log.error("An exception occurred in the process of generating data transmission. The exception information is as follows [{}]", e.getStackTrace());
            }
        }
        return true;
    }
}
