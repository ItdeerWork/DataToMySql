# DataToMySql
Generate data to send to mysql database


## 编译环境

> JDK1.8 Maven3.3.9及以上

```

编译命令：mav clean package

把target/DataToMySql-1.0.0.jar 和config配置目录

config下的runtime.json根据具体情况进行配置,mysql.properties文件为数据库连接池配置信息

```

## 程序运行

```

nohup java -jar DataToMySql-1.0.0.jar 1>/dev/null 2>&1 &

日志在程序包所在的目录下的logs目录，DataToMySql-all.log为所有的日志，可以进行查看运行状态
```

## 整体的配置

> 运行配置如下：

```
{
  "dataBase": {
    "type": "mysql" 数据库类型，如果变成其他数据库，如添加数据库A 需要确定在pom.xml中添加A的JDBC驱动依赖，添加一个A.properties的配置文件（需要Druid的数据库），此时的类型需要配置成A
  },
  "message": [
    {
      "table": "demo1",  //表名
      "threads": 1,     //线程数
      "batchSize": 20,  //批大小
      "fieldType": "fields", //点位信息生成类型（fields points files 三种类型）
      "dataNumber": 1000,   //发送的数据量
      "timeFrequency": 500, //每条数据之间的时间间隔
      "fieldMapping": "tagname,tagvalue,isgood,sendts,pits",  // 表结构的字段（下面配置的必须要按照字段的顺序配置）
      "fields": [
        {
        "field": "tagName==string(5)"   //字段名称为 tagName 类型为string 长度为5 长度不设置则使用默认值
      },
      {
        "field": "tagValue==double(0,10)" //字段名称为 tagValue 类型为double 取值范围 0,10，范围不设置则使用默认值
      },
      {
        "field": "isGood==boolean(0)" //字段名称为 isGood 类型为boolean 取值方式不设置则使用默认值 0 表示随机true或false
      },
      {
        "field": "sendTS==date(now,yyyy-MM-dd HH:mm:ss,1000)" //字段名称为 sendTS 类型为date 起始值为当前时间，时间格式为yyyy-MM-dd HH:mm:ss 最后的参数为生成的时间值与上一个值直接的间隔
      },
      {
        "field": "piTS==date(2018-01-30 00:00:00.000,yyyy-MM-dd HH:mm:ss.SSS)" //字段名称为 piTS 类型为date 起始值为指定时间点（2018-01-30 00:00:00.000）格式要和后面的格式保持一致，时间格式为yyyy-MM-dd HH:mm:ss.SSS
      },
      {
        "field": "sw==switching(0)" //表示开关量 表示开关量的变换类型  -1表示只返回0值 0表示随机时间内返回0或者1 1表示值返回1 大于1的值表示指定时间内变换一次
      }
      ]
    },
    {
      "table": "demo2",
      "threads": 1,
      "batchSize": 20,
      "fieldType": "points",  //点位信息生成类型（fields points files 三种类型）
      "dataNumber": 1000,
      "timeFrequency": 500,
      "fieldMapping": "tagname,tagvalue,isgood,sendts,pits",
      "points": [
        //tagname字段 值为CH4.A4037， tagvalue字段 值为int类型 范围为（0,10）， isgood字段 取值为boolean随机，sendts字段和pits为时间类型，当前时间开始，格式不同
        {
          "point": "tagname==CH4.A1&&tagvalue==int(0,10)&&isgood==boolean(0)&&sendts==date(now,yyyy-MM-dd HH:mm:ss)&&pits==date(now,yyyy-MM-dd HH:mm:ss.SSS)"
        },
        {
          "point": "tagname==CH4.A2&&tagvalue==int(5,10)&&isgood==boolean(0)&&sendts==date(now,yyyy-MM-dd HH:mm:ss)&&pits==date(now,yyyy-MM-dd HH:mm:ss.SSS)"
        },
        {
          "point": "tagname==CH4.A3&&tagvalue==int(10,20)&&isgood==boolean(0)&&sendts==date(now,yyyy-MM-dd HH:mm:ss)&&pits==date(now,yyyy-MM-dd HH:mm:ss.SSS)"
        },
        {
          "point": "tagname==CH4.A4&&tagvalue==int(20,30)&&isgood==boolean(0)&&sendts==date(now,yyyy-MM-dd HH:mm:ss)&&pits==date(now,yyyy-MM-dd HH:mm:ss.SSS)"
        },
        {
          "point": "tagname==CH4.A5&&tagvalue==int(20,40)&&isgood==boolean(0)&&sendts==date(now,yyyy-MM-dd HH:mm:ss)&&pits==date(now,yyyy-MM-dd HH:mm:ss.SSS)"
        }
      ]
    },
    {
      "table": "demo3",
      "threads": 1,
      "batchSize": 20,
      "fieldType": "files", //点位信息生成类型（fields points files 三种类型）
      "dataNumber": 1000,
      "timeFrequency": 500,
      "fieldMapping": "tagname,tagvalue,isgood,sendts,pits",
      "files": {
        "fields": "tagname==row_1&&tagvalue==row_2(row_3,row_4)&&isgood==boolean(0)&&sendts==date&&pits==date",// 点位文件的每一行数据在整体数据中所在位置
        "mapping": "row_1,row_2,row_3,row_4", //点位文件的具体行
        "fileName": "itdeer.csv"  //点位文件名称
      }
    }
  ]
}
```

> CSV点位配置样例

```
6131.B10DCS10FQ001,switching,2,0
6131.B10DCS10FQ002,switching,3,0
6131.B10DCS10FQ002A,int,10,50
6131.B10DCS10FQ002B,int,100,200
6131.B10HTA50CQ101,float,0,100
6131.B10HTB62CT901A,float,100,200
6131.B10HTA50CM101A,double,50,90
6131.B10HSK30CP101A,double,-8,10
```