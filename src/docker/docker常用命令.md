# docker常用命令 

一。帮助类命令
	1.docker version  / docker -- version
	2.docker info 获取docker的信息
	3.docker -- help 
二。镜像命令
	1.查看本机下的镜像 docker images 
	REPOSITORY 表示镜像的仓库原
	TAG 镜像的标签 或者叫版本
	IMAGE ID 镜像id
	CREATED 镜像创建时间
	SIZE 镜像大小
	同1仓库原可以有多个TAG,表示仓库原不同的版本。我们可以使用REPOSITORY:TAG来定义不同的镜像。如果不指定	标签，默认是使用REPOSITORY：latest镜像
		| docker images -a 列出本地所有的镜像（含中间映像层）
		| docker images -q 列出本地镜像的id
			-qa可以组合使用，查看所有的镜像的id
		| docker images --digests 查看镜像描述/摘要信息
		| docker images --no-trunc显示完整的镜像信息，可以和disgests合并使用
	2.搜索某个镜像名称 docker search 某个镜像名称
		| docker search tomcat -s 30 表示点赞数超过30的tomcat镜像，也可以写成docker search -s 30 tomcat
		| docker search -s 30 --no-trunc tomcat 显示完整的搜寻结果
		| docker search tomcat --automated 显示automated为🆗的信息
	3.拉取镜像 docker pull 镜像名:[tag]
		| docker pull tomcat 等价于 docker pull tomcat:latest 不加标签，默认拉去最新版
	4.删除镜像 docker rmi 镜像名:[标签]  也可以跟镜像id删除 
		| docker rmi -f 镜像名:[标签] 强制删除
		| docker rmi -f 镜像名1:[标签] 镜像名2:[标签] 删除多个
		| docker rmi -f $(docker images -qa)
	5.提交镜像 docker commit 提交容器副本，使之成为一个新的镜像
		| docker commit -m="提交的描述信息" -a="作者" 容器id 要创建的镜像名称:[标签名]
		| docker commit -a="peipei" -m="whitout docs tomcat" 7a8c07f1afca atpeipei/mytomcat:1.01
	6.列出镜像的变更历史 docker history 镜像名/镜像id
三。容器命令
	1.新建并启动容器 docker run [options] image[command][arg]
		options说明（常用）：有些是-，有些是--
		--name="容器新名称" ：为容器指定一个名称
		-d ：后台运行的容器，并返回容器id，也即启动守护容器；
		-i ：以交互模式运行容器，通常与-t同时使用
		-t ：为容器重新分配一个为输入终端，通常与-i同时使用；
		-P ：随机端口映射（注意大写）
		-p ：指定对口映射，有以下4中格式
		ip:hostPort:containerPort
		ip::containerPort
		hostPort:containerPort
		containerPort
		| docker run -it centos
		| dpcker run -it --name mytomcat tomcat
	2.查看docker正在运行的容器 docker ps[options]
		options说明（常用）：
		-a :列出当前所有长在运行的容器+历史上运行过的容器
		-l :显示最近创建的容器
		-n :显示最近的n个创建的容器
		-q :静默模式，只显示容器编码
		--no-trunc :不截断输出
		|docker ps 查看正在运行的容器
		|docker ps -l
		|docker ps -lq/-ql
		|docker ps -n 3
		....都可以组合使用
	3.退出容器 |exit 容器停止后退出
					  |ctrl+P+Q 容器不停止并退出
	4.启动容器 docker start 容器名<指的是镜像run时命名的名字>或是容器id

​	5.重启容器 docker restart 容器名或是容器id
​	6.停止容器 docker stop 容器名或是容器id
​	7.强制停止容器 docker kill 容器名或是容器id
​	8.删除容器 docker rm 容器名或是容器id 
​		| docker rm -f 容器名或是容器id 
  	   删除多个容器
  		|docker rm -f $(docker ps -aq)
  		|docker ps -aq | xargs docker rm   |表示管道符，意思是将|左边的结果集传给右边的命令

 容器命令 -- 重要部分
 1. 启动守护式容器 docker run -d 容器名/容器id
 	如果docker ps -a 发现容器已经退出，不要慌，这是docker容器的机制。容器后台启动后，如果没有事可做，就会自动退出。所以最佳方案就是，将要运行的容器以前台进程的形式运行。
 	docker run --d mycentos /bin/sh -c "while true;do echo hello zzyy;sleep 2;done"
 	这样，即使是守护式启动，让它每两秒打印hello zzyy,他就不会自动退出
  2. 查看容器日志 docker logs [-f] [-t] [--tail数字] 容器id/容器名
       	-t 加入时间戳 -f 跟随最新的日志打印 --tail数字 显示最后多少条
  3. 查看容器内运行的进程 docker top 容器id/容器名

  4. 查看容器内部细节 docker inspect 容器id/容器名
  5. 进入正在运行的容器并以命令行交互
       	|docker exec -it 容器id bashShell
       	|docker attach 容器id
       		attach 是直接进入容器启动命令的终端，不会启动新的进程
       		exec 是在容器中打开新的终端，并且可以启动新的进程
       		docker attach 0923rfef23r 进入容器新的终端
       		docker exec -it 0923rfef23r ls -l /tmp 不用打开新的终端，也可以访问新的终端的内容
       		docker exec -it 0923rfef23r /bin/bash 打开新的终端
       		简而言之，exec的功能比attach强大
  6. 从容器内拷贝文件到主机上 docker cp 容器id：容器内路径 主机的目的路径（拷贝的目的路径）
       	|docker cp 1a99d1d46191:/tmp/ks-script-_srt3u3c /root