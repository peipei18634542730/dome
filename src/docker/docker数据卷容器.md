docker数据卷容器
命令
继承运行容器 --volumes-from
一。概念
	命名的容器挂在数据卷，其他容器通过挂载这个（父容器）实现数据共享，挂载数据卷的容器，称之为数据卷容器。
	简单来讲就是容器间的数据共享
二。案列（以之间创建的pei/centos为例）
	1.先启动一个父容器 dc01
	docker run -ti --name dc01 pei/centos
		在dataVolumeContainer2新增内容
		touch dc01_add.txt
	2.dc02/dc03继承自dc01
	docker run -ti --name dc02 --volumes-from dc01 pei/centos
	docker run -ti --name dc03 --volumes-from dc01 pei/centos
		在dc02/dc03。dataVolumeContainer2新增内容
		touch dc02_add.txt
		touch dc03_add.txt
	3.删除dc01,dc02修改后dc03可否访问
		docker rm -f dc01    
	 	touch dc02_update.txt
	4.删除dc02后，dc03可否访问
	docker rm -f dc02
	5.新建dc04继承dc03后在删除dc03
	docker run -ti --name dc04 --volumes-from dc01 pei/centos
	docker rm -f dc03
	结论：
		容器之间配置信息的传递，数据卷的生命周期一直持续到没有容器使用它为止