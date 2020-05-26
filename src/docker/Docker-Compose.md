Docker Compose

一。概述

--------

​		负责快速的部署分布式应用，其定位是定义和运行多个Docker容器的应用。

​		compose允许用户通过一个单独的docker-compose.yml模板文件（YAML格式）来定义一组相关联的应用容器为一个项目。

Compose有两个重要的概念：

- 服务 ： 一个应用的容器，实际上可以包括若干个运行相同镜像的容器实例
- 项目 ： 有一组关联的应用容器组成的一个完整业务单元，在docker-compose.yml文件中定义。

Compose的默认管理对象是项目，通过子命令对项目的一组容器进行便捷德生命周期管理。

Compose项目有Python编写，实现上调用了Docker服务提供的API来对容器进行管理。因此，只要操作的平台支持Docker API，就可以在其上利用Compose来进行编排管理。

二。安装

-------

https://github.com/docker/compose/releases 页面获取安装信息

```
curl -L https://github.com/docker/compose/releases/download/1.25.4/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```

三。使用docker-compose

1.编写compose文件

注意事项： 1.通过空格，实现缩进，缩进表示层级关系

​					2.冒号和之间要有空格

![image-20200407161731508](C:\Users\11374\AppData\Roaming\Typora\typora-user-images\image-20200407161731508.png)	

2.docker-compose的相关命令

​	docker-compose up 

​	docker-compose up -d

​	docker-compose down 关闭并删除服务

​	docker-compose stop 服务名 关闭服务

​	docker-compose start 服务名 启动已关闭的服务