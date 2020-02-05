package cn.itdeer.utils;

import java.text.SimpleDateFormat;

/**
 * Description : 常量工具类
 * PackageName : cn.itdeer.utils
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/15:54
 */
public class Constants {

    /**
     * 配置文件名称
     */
    public static final String CONFIG_FILE_NAME = "runtime.json";
    public static final String CONFIG_FILE_DIRECTORY = "config";


    /**
     * 点位配置类型
     */
    public static final String FIELDS = "fields";
    public static final String POINTS = "points";
    public static final String FILES = "files";


    public static final Integer BOOLEAN_FIELD_DEFAULT_FLAG = 0; // 1 表示true  -1 表示false 0 表示随机true或false

    public static final String DATE_FIELD_DEFAULT_START_POINT = "now";                  // 表示开始时间
    public static final String DATE_FIELD_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";      // 表示时间格式
    public static final Integer DATE_FIELD_DEFAULT_INTERVAL = 0;                       // 表示时间格式,单位为毫秒值

    public static final String DOUBLE_FIELD_DEFAULT_FORMAT = "#0.0000";                // 表示保留的小数点位数
    public static final Double DOUBLE_FIELD_DEFAULT_MIN = 0.0;                          // 表示double最小取值范围
    public static final Double DOUBLE_FIELD_DEFAULT_MAX = 10000.0;                      // 表示double最大取值范围

    public static final String FLOAT_FIELD_DEFAULT_FORMAT = "#0.00";                   // 表示保留的小数点位数
    public static final Float FLOAT_FIELD_DEFAULT_MIN = 0.0f;                           // 表示float最小取值范围
    public static final Float FLOAT_FIELD_DEFAULT_MAX = 10000.0f;                       // 表示float最大取值范围

    public static final Integer INT_FIELD_DEFAULT_MIN = 0;                             // 表示int最小取值范围
    public static final Integer INT_FIELD_DEFAULT_MAX = 10000;                         // 表示int最大取值范围

    public static final Integer STRING_FIELD_DEFAULT_LENGTH = 4;                       //字符串的长度为4个字符组成
    public static final Integer STRING_FIELD_DEFAULT_WRITE = 7;                        // 1 表示大写  2 表示小写 3 表示数字 4 表示大写小写混合 5 表示小写和数字 6 表示大写和数字 7 表示大写小写和数字

    public static final Integer SWITCHING_FIELD_DEFAULT_TYPE = 0;                       // 表示开关量的变换类型  -1表示只返回0值 0表示随机时间内返回0或者1 1表示值返回1 大于1的值表示指定时间内变换一次
    public static final Integer SWITCHING_FIELD_DEFAULT_MAX_VALUE = 100;                 // 表示开关量随机变化的时间段最大值
    public static final Integer SWITCHING_FIELD_DEFAULT_MIN_VALUE = 0;                   // 表示开关量随机变化的时间段最小值


    /**
     * Other Config
     */
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * Symbolic Config
     */
    public static final String EQUAL = "==";
    public static final String COMMA = ",";
    public static final String AND = "&&";


    /**
     * Data Field Type Config
     */
    public static final String STRING = "string";
    public static final String BOOLEAN = "boolean";
    public static final String DOUBLE = "double";
    public static final String INT = "int";
    public static final String FLOAT = "float";
    public static final String DATE = "date";
    public static final String SWITCHING = "switching";

}
