package cn.itdeer.utils;

/**
 * Description : 信息打印输出工具类
 * PackageName : cn.itdeer.utils
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/15:54
 */
public class LogPrint {
    /**
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param startDate  开始日期
     * @param endDate    结束日期
     * @param threadName 线程名字
     * @param dataNumber 总数据条数
     * @param tableName  表名称
     */
    public static void outPrint(long startTime, long endTime, String startDate, String endDate, String threadName, long dataNumber, String tableName) {
        double totle_time = (endTime - startTime) / 1000;

        System.out.printf("\nThreadNum \t" + "TableName \t" + "TotleTime(s) \t" + "StartDate \t" + "EndDate \t" + "TotleMessageNums \t" + "Speed \t" + "\n" + "%s \t" + "%s \t" + "%s \t" + "%s \t" + "%s \t" + "%s \t" + "%s \t", threadName, tableName, totle_time, startDate, endDate, dataNumber, dataNumber / totle_time);
        System.out.println();
    }
}
