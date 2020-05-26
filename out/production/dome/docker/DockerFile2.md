## DockerFile

## 一。DockerFile是什么
	JavaEE Hello.java ----> Hello.class
	Docker images --------> DockerFile
	dockerfile是用来构建docker镜像的构建文件，是由一系列的命令和参数构成的脚本文件
二。docker的主流步骤
	1.手动编写一个dockerfile文件，必须符合file的规范
	2.docker build命令执行，获得一个自定义的镜像
		|docker build -f /mydocker/dockerfile -t dockerfileNames . 最后不要忘了.
		|docker build -t xxxx . 在当前路径下，会默认只想Dockerfile文件。
	3.run这个镜像
三。dockerfile构建过程解析
	1.dockerfile内容的基础知识
		1）每条保留字指令都必须为大写字母，并且垢面要跟至少一个参数
		2）指令按照从上到下的顺序执行
		3）#表示注解
		4）每条指令都会创建一个新的镜像层，并且对镜像进行提交
	2.docker执行dockerfile的大概过程
		1）docker从基础金Gina给运行一个容器
		2）执行一条指令对容器从一次修改。。。
		3）执行类似docker commit的操作提交一个新的镜像层
		4）docker再基于提交的镜像运行一个新的容器
		5）执行dockerfile中的下一条指令知道所有的指令都执行完毕
四。dockerfile体系结构(保留字指令)
	FROM - 基础镜像，当前镜像是基于哪个镜像的
	MAINTAINER - 镜像维护着的姓名和邮箱地址
	RUN - 构建容器时需要运行的命令
	EXPOSE - 当前容器对外暴露的端口
	WORKDIR - 指定在创建容器后，终端默认登陆进来的工作目录，一个落脚点。没有指定默认在根目录(/)
	ENV - 用来在构建镜像过程中设置环境变量
	ADD - 将宿主机目录下的文件拷贝到镜像并且ADD命令会自动解压缩和处理URL
	COPY - 类似ADD，拷贝文件和目录到镜像中。将从构建上下文目录中<源路径>的文件/目录复制到新一层的镜像内的<目标路径>位置
	VOLUME - 容器数据卷，用于数据保存和持久化工作
	CMD - 指定一个容器启动时要运行的命令
		- dockerfile中可以有多个CMD指令，但只有最后一个生效，CMD会被docker run之后的参数替换
	ENTRYPOINT - 指定一个容器启动时要运行的命令
			   - ENTRYPOINT的目的和CMD一样，都是在指定容器启动程序及参数，不一样的是，运行时，添加非dockerFile文件下的命令，CMD会被覆盖，ENTRYPOINT会被追加组合运用。
	ONBUILD	 - 当构建一个被继承的Dockefile时运行命令，父镜像在被子镜像继承后，父镜像的onbuild被触发

