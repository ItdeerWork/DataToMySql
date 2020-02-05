package cn.itdeer.common.fields;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

import static cn.itdeer.utils.Constants.*;

/**
 * Description : Date类型字段
 * PackageName : cn.itdeer.common.fields
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/11:46
 */
@Slf4j
public class DateField implements FieldInterface {

    /**
     * 开始时间点
     */
    private String startPoint = DATE_FIELD_DEFAULT_START_POINT;

    /**
     * 时间格式
     */
    private String format = DATE_FIELD_DEFAULT_FORMAT;

    /**
     * 生成时间数据的时间间隔
     */
    private int interval = DATE_FIELD_DEFAULT_INTERVAL;

    /**
     * 时间格式化对象
     */
    private SimpleDateFormat sdf;

    /**
     * 起始时间和当前时间的差距间隔
     */
    private long leadTime = 0l;

    /**
     * 无参构造函数（getValue方法的startPoint和format使用默认值）
     */
    public DateField() {
        init();
    }

    /**
     * 一个参数构造函数
     *
     * @param format 时间格式
     */
    public DateField(String format) {
        this.format = format;
        init();
    }

    /**
     * 两个参数构造函数
     *
     * @param startPoint
     * @param format
     */
    public DateField(String startPoint, String format) {
        this.startPoint = startPoint;
        this.format = format;
        init();
    }

    /**
     * 三个参数构造函数
     *
     * @param startPoint
     * @param format
     */
    public DateField(String startPoint, String format, int interval) {
        this.startPoint = startPoint;
        this.format = format;
        this.interval = interval;
        init();
    }

    /**
     * 初始化参数值
     */
    private void init() {
        sdf = new SimpleDateFormat(format);
        try {
            if (!DATE_FIELD_DEFAULT_START_POINT.equals(startPoint)) {
                long lead = new Date().getTime() - sdf.parse(startPoint).getTime();
                leadTime = lead > 0 ? lead : 0l;
            }
        } catch (Exception e) {
            log.error("When creating a time type field, instantiate the time field to handle the instance, and an error occurred while initializing the start time ......");
            log.error("The starting time format should be consistent with the time format, otherwise it cannot be processed ......");
        }
    }

    /**
     * 实现返回一个指定时间的String类型时间字符串
     *
     * @return String类型的时间值
     */
    @Override
    public Object getValue() {
        leadTime = leadTime - interval;
        return sdf.format(new Date().getTime() - leadTime);
    }
}
