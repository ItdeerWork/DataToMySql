package cn.itdeer.core.control;

import cn.itdeer.common.config.Message;
import cn.itdeer.common.init.InitCommon;
import cn.itdeer.common.init.InitConfig;
import cn.itdeer.core.type.FieldsTypeData;
import cn.itdeer.core.type.PointTypeData;
import cn.itdeer.utils.Constants;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : 数据资源管理
 * PackageName : cn.itdeer.core.control
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/13:54
 */
@Slf4j
public class DataSource {

    /**
     * 启动整个数据源
     */
    public void start() {
        for (Message message : InitConfig.getMessageConfig()) {
            for (int i = 0; i < message.getThreads(); i++) {
                String threadName = message.getTable() + "-" + i;
                createDataInstance(message, threadName);
                log.info("Create A thread instance for table [{}]", message.getTable());
            }
        }
    }

    /**
     * 创建发数实例资源
     *
     * @param message    发送数据信息实例
     * @param threadName 线程名称
     */
    private void createDataInstance(Message message, String threadName) {
        switch (message.getFieldType().toLowerCase()) {
            case Constants.FIELDS:
                new FieldsTypeData(message, threadName).start();
                break;
            case Constants.POINTS:
                new PointTypeData(message, threadName).start();
                break;
            case Constants.FILES:
                message.setPoints(new InitCommon().initFilePoints(message));
                new PointTypeData(message, threadName).start();
                break;
            default:
                log.error("The current configuration type [{}] is not supported", message.getFieldType());
        }
    }
}
