## redis使用

1. reids安装：https://www.runoob.com/redis/redis-install.html
2. 修改redis.windows.conf，搜索requirepass，将所在行改为requirepass 123456。重启redis服务器，`.\redis-server.exe redis.windows.conf`

3. 客户端登陆`redis-cli.exe -h 127.0.0.1 -p 6379`，然后`auth 123456`验证

