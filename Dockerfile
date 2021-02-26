# 该镜像需要依赖的基础镜像
FROM adoptopenjdk/openjdk14:x86_64-alpine-jre-14.0.1_7
# 将targer目录下的jar包复制到docker容器/app/目录下面
COPY ./target/mdapp-0.0.1-SNAPSHOT.jar /app/mdapp-0.0.1-SNAPSHOT.jar

# 声明服务运行在8085端口
EXPOSE 8085
# 执行命令
CMD ["java","-jar","/app/mdapp-0.0.1-SNAPSHOT.jar"]