package cn.itdeer;

import cn.itdeer.core.control.DataSource;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : 主函数
 * PackageName : cn.itdeer
 * ProjectName : DataToMySql
 * CreatorName : itdeer.cn
 * CreateTime : 2020/02/03/16:54
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        new DataSource().start();
        log.info("Start the main program to work ......");
    }
}
