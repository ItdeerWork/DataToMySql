package cn.itdeer.common.config;

import lombok.Data;

import java.util.List;

/**
 * Description : 总体配置实体
 * PackageName : com.itdeer.common.config
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/09:54
 */
@Data
public class ConfigBean {

    private DataBase dataBase;
    private List<Message> message;

}
