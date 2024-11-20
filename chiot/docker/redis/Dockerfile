# 基础镜像
FROM redis
# author
MAINTAINER chdzq
# 创建目录
RUN mkdir -p /home/redis/conf
# 指定路径
WORKDIR /home/redis
# 复制conf文件到路径
ADD ./conf/redis.conf /home/redis/conf/redis.conf
