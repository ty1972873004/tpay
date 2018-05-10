# tpay
聚合支付  分布式框架

<a href='https://gitee.com/trazen/tpay/stargazers'><img src='https://gitee.com/trazen/tpay/badge/star.svg?theme=dark' alt='star'></img></a>


项目使用框架
```
    分布式服务框架 Dubbo
    分布式服务治理 Zookeeper
    基础框架 SpringMVC+Spring+Mybatis
    消息队列  ActiveMQ
    分布式缓存数据库 Redis
    数据库  Mysql5.7+
    接口测试框架 Swagger2
    项目构建管理 Maven
    安全框架 Shiro
```
项目结构
```
tpay
├── tpay-common -- 公共模块
├── tpay-common-cache -- 缓存模块
├── tpay-common-service -- service基础模块
├── tpay-common-shiro -- 权限基础模块
├── tpay-mq -- 消息队列模块
|    ├── tpay-mq-notiofy -- 第三方支付通知模块
├── tpay-notify -- 消息通知服务
|    ├── tpay-notify-common -- 公共模块
|    ├── tpay-notify-dao -- db层
|    ├── tpay-notify-facade -- 接口包
|    ├── tpay-notify-service -- Dubbo服务提供者
├── tpay-order -- 订单服务
|    ├── tpay-order-common -- 公共模块
|    ├── tpay-order-dao -- db层
|    ├── tpay-order-facade -- 接口包
|    ├── tpay-order-service -- Dubbo服务提供者
├── tpay-sys -- 系统服务
|    ├── tpay-sys-common -- 公共模块
|    ├── tpay-sys-dao -- db层
|    ├── tpay-sys-facade -- 接口包
|    ├── tpay-sys-service -- Dubbo服务提供者
├── tpay-user -- 用户服务
|    ├── tpay-user-common -- 公共模块
|    ├── tpay-user-dao -- db层
|    ├── tpay-user-facade -- 接口包
|    ├── tpay-user-service -- Dubbo服务提供者
├── tpay-payment -- 支付中心服务
|    ├── tpay-payment-common -- 公共模块
|    ├── tpay-payment-dao -- db层
|    ├── tpay-payment-facade -- 接口包
|    ├── tpay-payment-service -- Dubbo服务提供者
├── tpay-upms-web -- 用户权限管理系统
├── tpay-admin-web --商户管理系统
├── tpay-payment-web --支付回调web
├── tpay-open-web --开放接口web
```
项目打包命令
clean package -Dmaven.test.skip=true

service启动 XXDubboProvider.main()
jvm参数 -Ddubbo.application.logger=slf4j -Ddruid.logType=slf4j

web启动命令  clean package -P dev tomcat7:run
jvm参数 -Ddubbo.application.logger=slf4j

