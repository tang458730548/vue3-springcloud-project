## 后端项目

### 项目介绍

- 采用springcloud搭建微服务
- 模块拆分如下：<br>
    - 服务admin-parent为整个工程的父工程
    - 服务admin-base为基础服务、集成eureka、zuul
    - 服务admin-common为公共服务，集成http统一返回、常量、异常等
    - 服务admin-services为主要服务模块，细分为基础数据服务、微信服务等等
    - admin-portal为门户服务，提供前端页面所需要的接口 </br>
- 依赖关系如下：<br>
    - admin-services服务依赖admin-base、admin-common
    - admin-portal服务依赖admin-base、admin-common、admin-services
- 启动顺序：<br>
    - 先启动admin-eureka服务
    - 。。。

### 开发日志
#### 2023-3-28
```
  1、引入admin-parent pom依赖
  2、集成admin-base-eureka ， 访问方式 http://127.0.0.1:8901
  3、增加admin-service-base 、 admin-service-weixin两个服务模块
  4、增加admin-common模块，并且添加了一些统一http返回、常量枚举等
  5、admin-service-base服务整合mybatis-generate自动生成实体类
  6、admin-service-base服务整合logback日志
  7、集成热部署
  8、集成openfeign,在admin-portal服务中调用admin-service-base
     的服务接口
  9、修改fastjson版本号、集成日志切面，打印日志信息
```
#### 2023-3-30
```
  1、集成axios
  2、提供登陆接口
 
```

