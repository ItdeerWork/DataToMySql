package cn.itdeer.utils;

import cn.itdeer.common.init.InitConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static cn.itdeer.utils.Constants.CONFIG_FILE_DIRECTORY;

/**
 * Description : 数据库配置工具类
 * PackageName : cn.itdeer.utils
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/15:54
 */
@Slf4j
public class DataBaseConfig {

    private static Properties prop = null;
    private static FileInputStream in = null;

    /**
     * 初始化配置信息
     */
    private void init() {
        try {
            if (prop == null) {
                prop = new Properties();
                String runTimePath = System.getProperty("user.dir");
                in = new FileInputStream(new File(runTimePath + File.separator + CONFIG_FILE_DIRECTORY + File.separator + InitConfig.getDataSourceConfig().getType() + ".properties"));
                prop.load(in);
                log.info("loading *.properties file finish");
            }
        } catch (Exception e) {
            log.error("loading *.properties file appear exception: " + e.getStackTrace());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    log.error("close InputStream appear exception: " + e.getStackTrace());
                }
            }
        }
    }

    /**
     * 获取DataBase配置属性实例
     *
     * @return Properties 实例
     */
    public Properties getProp() {
        if (prop == null) {
            init();
        }
        return prop;
    }
}
