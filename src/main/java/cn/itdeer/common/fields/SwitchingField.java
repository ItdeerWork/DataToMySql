package cn.itdeer.common.fields;

import java.util.Random;

import static cn.itdeer.utils.Constants.*;

/**
 * Description : Switching类型字段
 * PackageName : cn.itdeer.common.fields
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/11:46
 */
public class SwitchingField implements FieldInterface {

    /**
     * 默认开关量的变换类型
     */
    private Integer switchingType = SWITCHING_FIELD_DEFAULT_TYPE;

    /**
     * 随机生成的时间段的范围
     */
    private Integer max = SWITCHING_FIELD_DEFAULT_MAX_VALUE;
    private Integer min = SWITCHING_FIELD_DEFAULT_MIN_VALUE;

    /**
     * 差距时间毫秒值
     */
    private long leadTime = 0l;

    /**
     * 定义一个默认的标识
     */
    private int flag = 0;

    private int num = 0;


    /**
     * 无参的构造函数 默认变换类型
     */
    public SwitchingField() {
        init();
    }

    /**
     * 一个参数的构造函数
     *
     * @param switchingType 指定变换类型
     */
    public SwitchingField(Integer switchingType) {
        this.switchingType = switchingType;
        init();
    }

    /**
     * 初始化参数值
     */
    private void init() {

    }

    /**
     * 实现返回一个指定时间的String类型时间字符串
     *
     * @return String类型的时间值
     */
    @Override
    public Object getValue() {

        if (switchingType == -1)
            return 0;

        if (switchingType == 1)
            return 1;

        if (switchingType == 0 && System.currentTimeMillis() > leadTime) {
            getTimeHorizonAndSetFlag(1);
        }

        if (switchingType > 1 && System.currentTimeMillis() > leadTime) {
            getTimeHorizonAndSetFlag(switchingType);
        }

        return flag;
    }

    /**
     * 获取一个随机的时间范围
     *
     * @return 范围值
     */
    public void getTimeHorizonAndSetFlag(int time) {
        if (time > 1) {
            leadTime = System.currentTimeMillis() + time * 1000 * 60;
        } else {
            int value = new Random().nextInt(max - min + 1) + min;
            leadTime = System.currentTimeMillis() + value * 1000 * 60;
        }
        flag = num % 2;
        num++;
    }
}
