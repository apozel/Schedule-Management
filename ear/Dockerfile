FROM payara/server-full

# Default payara ports to expose
# 4848: admin console
# 9009: debug port (JPDA)
# 8080: http
# 8181: https
EXPOSE 4848 9009 8080 8181

USER root

ARG JAR_FILE

COPY ./target/${JAR_FILE}.ear $DEPLOY_DIR
COPY ./docker/mysql-connector-java-8.0.19.jar /
COPY  ./docker/post-boot-commands.asadmin $POSTBOOT_COMMANDS

RUN chmod a+x $POSTBOOT_COMMANDS
