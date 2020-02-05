package cn.itdeer.common.config;

import lombok.Data;

import java.util.List;

/**
 * Description : 配置信息实体
 * PackageName : com.itdeer.common.config
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/09:54
 */
@Data
public class Message {

    private String table;
    private Integer threads;
    private Integer batchSize;
    private String fieldType;
    private Integer dataNumber;
    private Integer timeFrequency;
    private String fieldMapping;
    private List<Fields> fields = null;
    private List<Points> points = null;
    private Files files = null;

}
