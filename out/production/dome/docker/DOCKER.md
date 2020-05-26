# DOCKER
一。概述

1.docker是什么
一句话简述：解决了运行环境和配置问题的软件容器，方便做持续集成并有助于整体发布的容器虚拟化技术。
2.三大要素
镜像/仓库/容器
镜像就是一个只读的模板，镜像可以用来创建docker容器，一个镜像可以创建多个容器。
容器就是用镜像创建的运行实例。
仓库是集中存放镜像文件的场所，仓库和仓库注册器（registry）是有区别的，仓库注册器上往往存放着多个仓库，每个仓库又包含了多个镜像，每个镜像有不同的标签（tag）。标签类似于版本号
3.优点
更轻量/更高效/更便捷/更灵活
4.官网
docker-cn.com  https://docs.docker.com  下载
二。安装docker

1. 下载docker-ce的repo
curl https://download.docker.com/linux/centos/docker-ce.repo -o /etc/yum.repos.d/docker-ce.repo
2. 安装依赖（这是相比centos7的关键步骤）
yum install https://download.docker.com/linux/fedora/30/x86_64/stable/Packages/containerd.io-1.2.6-3.3.fc30.x86_64.rpm
3. 安装docker-ce
yum install docker-ce
4.sudo systemctl start docker

docker --version

5.开机自启
sudo systemctl enable docker

6.检验安装 是否成功
docker run hello-world

7.配置阿里镜像加速器
https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
支付宝登陆后，显示针对个人的加速器地址，以及配置方法，直接全复制就好
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://mk96seoy.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
三。docker run都干了什么
以docker run hello-world为例，首先docker在本机上寻找该镜像，若本机上有则以该镜像为模板，生产容器实例运行。若本机上没有，就去docker Hub上找（这里更新为阿里镜像仓库），如果有则下载该镜像到本地，然后生产对应的容器实例并运行，如果没有则返回错误信息（image dose not exist）。
四。docker运行机制