package cn.itdeer.common.fields;

import java.text.DecimalFormat;
import java.util.Random;

import static cn.itdeer.utils.Constants.*;

/**
 * Description : Float类型字段
 * PackageName : cn.itdeer.common.fields
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/11:46
 */
public class FloatField implements FieldInterface {

    /**
     * 默认最小最大值
     */
    private float min = FLOAT_FIELD_DEFAULT_MIN;
    private float max = FLOAT_FIELD_DEFAULT_MAX;

    private DecimalFormat df = new DecimalFormat(FLOAT_FIELD_DEFAULT_FORMAT);

    /**
     * 无参构造函数（getValue方法的最大最小值使用默认值）
     */
    public FloatField() {
    }

    /**
     * 一个参数构造函数（getValue方法的最小值使用默认值）
     *
     * @param max 最大值
     */
    public FloatField(float max) {
        this.max = max;
    }

    /**
     * 两个参数构造函数
     *
     * @param min 最小值
     * @param max 最大值
     */
    public FloatField(float min, float max) {
        this.min = min;
        this.max = max;
    }

    /**
     * 实现返回一个在指定范围内随机生成的Float类型的数值
     *
     * @return Float类型的数值
     */
    @Override
    public Object getValue() {
        return Float.parseFloat(df.format(min + ((max - min) * new Random().nextFloat())));
    }
}
