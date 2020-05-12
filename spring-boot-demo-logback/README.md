# Spring-logback

### 1.配置方式

​	默认情况下springboot是不将日志输出到日志文件中的 但是支持对日志框架的两种配置方式:

- application.properties 或  application.yml （系统层面）
- logback-spring.xml                （自定义文件方式）

> 说明:
>
> ​	第一种方式比较简单 可做的事情比较少 比如配置日志文件路径,日志文件格式等
>
> ​	第二种方式比较复杂 比如可以区分info和error日志 可以每天产生一个日志文件等		

##### 	1.1采用application.properties 或 application.yml配置方式

```yaml
logging:
  pattern:
    console: "%d - %msg%n"
  path: D:/
  file: demo.log
  level:
    com.xkcoding.logback: trace
```

> 说明:
>
> ​	若只配置logging.path 会在指定的位置生成一个spring.log的配置文件 名字是不可以i修改的 是可以创建目录的
>
> ​	若只配置logging.file 就会在当前项目的的根目录生成一个对应名字的日志文件
>
> ​	若logging.path和file同时配置 只有logging.file生效
>
> ​	都可以使用绝对和相对路径

##### 	1.2采用logback-spring.xml配置方式

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="true">

    <!-- 彩色日志 -->
    <!-- 彩色日志依赖的渲染类 -->
    <conversionRule conversionWord="clr" converterClass="org.springframework.boot.logging.logback.ColorConverter" />
    <conversionRule conversionWord="wex" converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter" />
    <!-- 彩色日志格式 -->
    <property name="CONSOLE_LOG_PATTERN" value="%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(${PID:- }) [%t]{magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n%wex"/>
    <property name="FILE_LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${PID:- } --- [%t] %-40.40logger{39} : %m%n%wex"/>
     
    <!--多环境的日志输出-->
    <!--根据不同环境（prod:生产环境，test:测试环境，dev:开发环境）来定义不同的日志输出，-->
    <!--在 logback-spring.xml中使用 springProfile 节点来定义，方法如下：-->
    <springProfile name="prod">
        <property name="LOG_HOME" value="/data/dubbo/platform-action/log/" />
    </springProfile>
    <springProfile name="stg">
        <property name="LOG_HOME" value="/data/dubbo/platform-action/log/" />
    </springProfile>
    <springProfile name="dev">
        <property name="LOG_HOME" value="/data/dubbo/platform-action/log/" />
    </springProfile>
    <springProfile name="pre">
        <property name="LOG_HOME" value="/data/dubbo/platform-action/log/" />
    </springProfile>

    <!--输出到控制台-->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
            <charset>utf8</charset>
        </encoder>
    </appender>

    <!--info 级别的日志-->
    <!-- 按照每天生成日志文件 -->
    <appender name="INFO" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">

            <!--日志文件输出的文件名-->
            <fileNamePattern>${LOG_HOME}/info.%d{yyyy-MM-dd}.log</fileNamePattern>

            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
        </rollingPolicy>

        <!--日志文件最大的大小-->
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
          <MaxFileSize>10MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    
    <!--WARN 级别的日志-->
    <appender name="WARN" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>WARN</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/warn.%d{yyyy-MM-dd}.log</fileNamePattern>
            <MaxHistory>30</MaxHistory>
        </rollingPolicy>
    </appender>

    <!--ERROR 级别的日志-->
    <appender name="ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/error.%d{yyyy-MM-dd}.log</fileNamePattern>
            <MaxHistory>30</MaxHistory>
        </rollingPolicy>
    </appender>
    
    <!-- 日志输出级别 -->
    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="INFO"/>
        <appender-ref ref="WARN"/>
        <appender-ref ref="ERROR"/>
    </root>
</configuration>
```

