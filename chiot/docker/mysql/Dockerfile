# 基础镜像
FROM mysql:8.0.31
# author
MAINTAINER chdzq

# 执行sql脚本
ADD ./db/*.sql /docker-entrypoint-initdb.d/
RUN chown -R mysql:mysql /docker-entrypoint-initdb.d
EXPOSE 3306
CMD ["mysqld", "--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]
