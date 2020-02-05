package cn.itdeer.common.init;

import cn.itdeer.common.config.ConfigBean;
import cn.itdeer.common.config.DataBase;
import cn.itdeer.common.config.Message;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static cn.itdeer.utils.Constants.CONFIG_FILE_DIRECTORY;
import static cn.itdeer.utils.Constants.CONFIG_FILE_NAME;

/**
 * Description : 初始化运行配置文件
 * PackageName : com.itdeer.common.init
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/12:54
 */
@Slf4j
public class InitConfig {

    private static String configFileName = CONFIG_FILE_NAME;
    private static StringBuffer sb = new StringBuffer();
    private static ConfigBean cb;

    /**
     * 静态代码块，加载配置文件
     */
    static {
        String filePath = System.getProperty("user.dir") + File.separator + CONFIG_FILE_DIRECTORY + File.separator + configFileName;
        try (
                FileReader reader = new FileReader(filePath);
                BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            init();
            log.info("Reading the configuration file is complete [{}]", configFileName);
        } catch (IOException e) {
            log.error("Error reading configuration file [{}] error message is as follows:", configFileName, e.getStackTrace());
        }
    }

    /**
     * 初始化配置为实体对象
     */
    private static void init() {
        cb = JSON.parseObject(sb.toString(), ConfigBean.class);
    }

    /**
     * 获取DataBase的配置
     *
     * @return DataBase的配置信息
     */
    public static DataBase getDataSourceConfig() {
        return cb.getDataBase();
    }

    /**
     * 获取需要发送数据的配置信息
     *
     * @return 需要发送的数据配置列表
     */
    public static List<Message> getMessageConfig() {
        return cb.getMessage();
    }

}