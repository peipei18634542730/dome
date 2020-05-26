docker容器数据卷
一。主要作用
	数据的持久化和容器间继承+数据共享
二。特点
	1.容器卷可以在容器之间共享数据或重用数据
	2.卷中的更改可以直接生效
	3.数据卷中的更改不会包含在镜像的更新中
	4.数据卷的生命周期一直持续到没有容器使用它为止
三。数据据卷容器内添加
	1.命令添加
		|docker run -ti -v/宿主机绝对路径目录：/容器内目录 镜像名
		docker inspect 镜像id查看json信息
		"HostConfig": {
            "Binds": [
                "/myDataValue:/ContainerDataValue"
            ],
            "ContainerIDFile": "",
            "LogConfig": {
                "Type": "json-file",
                "Config": {}
            }
            说明绑定成功
           容器停止退出后，主机修改后数据让可同步
        | docker run -ti -v /宿主机绝对路径目录：/容器内目录 :ro 镜像名
        ro ：Read-only 只读 容器针对宿主机的只读，可以读到宿主机关联的数据，容器无法对命令的文件夹内容进行操作。
        [root@5e55a09a1d94 ContainerDataValue]# touch container.txt
		touch: cannot touch 'container.txt': Read-only file system
	2.DockerFile添加
	出于可移植和分享的考虑，用-V 主机目录：容器目录的这种方式不能直接在DockerFile中实现。由于宿主机目录是依赖于特定宿主机的，并不能保证在所有的宿主机上都存在这样的特定目录。
		1.根目录下新建mydocker文件夹并进入 mkdir mydocker
		touch dockerfile
		
		2.可在DockerFile中使用VOLUME指令来给镜像添加一个或多个数据卷
			file构建
			vim dockerfile
			#volume test
			FROM centos
			VOLUME ["/dataVOlumeContainer1","/dataVolumeContainer2"]
			CMD echo "finished,--------success"
			CMD /bin/bash
		3.build后生成镜像
		docker build -f /mydocker/dockerfile -t pei/centos.
		-f :指明dockerfile的路径 -t镜像名. :命名空间
		4.run容器
		docker run -ti pei/centos
		5.宿主机目录
			由体统默认生成
			"Mounts": [
            {
                "Type": "volume",
                "Name": "344c933f69832abfe8945cab0ba102c99125663f2fa7dcfe62b6bf2e1b285a4a",
            ———>"Source": "/var/lib/docker/volumes/344c933f69832abfe8945cab0ba102c99125663f2fa7dcfe62b6bf2e1b285a4a/_data",
                "Destination": "/dataVOlumeContainer1",
                "Driver": "local",
                "Mode": "",
                "RW": true,
                "Propagation": ""
            },
            {
                "Type": "volume",
                "Name": "9e781839c4eded49c75e0bfeaed44dd069b70a8face3d7ae204643b8734d5bd7",
            ———>"Source": "/var/lib/docker/volumes/9e781839c4eded49c75e0bfeaed44dd069b70a8face3d7ae204643b8734d5bd7/_data",
                "Destination": "/dataVolumeContainer2",
                "Driver": "local",
                "Mode": "",
                "RW": true,
                "Propagation": ""
            }



