package cn.itdeer.common.fields;

import java.util.Random;

import static cn.itdeer.utils.Constants.BOOLEAN_FIELD_DEFAULT_FLAG;

/**
 * Description : Boolean类型字段
 * PackageName : cn.itdeer.common.fields
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/11:46
 */
public class BooleanField implements FieldInterface {

    /**
     * 默认flag的值
     */
    private int flag = BOOLEAN_FIELD_DEFAULT_FLAG;

    /**
     * 无参构造函数（getValue方法的flag使用默认值）
     */
    public BooleanField() {
    }

    /**
     * 一个参数构造函数
     *
     * @param flag 标志true false
     */
    public BooleanField(int flag) {
        this.flag = flag;
    }

    /**
     * 实现返回一个指定Boolean类型的对象
     *
     * @return Boolean类型的对象
     */
    @Override
    public Object getValue() {
        if (flag > 0) {
            return true;
        }
        if (flag < 0) {
            return false;
        }
        return new Random().nextBoolean();
    }
}
