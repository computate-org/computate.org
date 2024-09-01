FROM registry.access.redhat.com/ubi9/openjdk-17:latest

USER root

RUN microdnf install -y git

RUN install -d /home/default/computate.org
COPY . /home/default/computate.org

RUN git clone https://github.com/computate-org/computate-base.git /home/default/computate-base
RUN git clone https://github.com/computate-org/computate-search.git /home/default/computate-search
RUN git clone https://github.com/computate-org/computate-vertx.git /home/default/computate-vertx

WORKDIR /home/default/computate-base
RUN mvn clean install -DskipTests
WORKDIR /home/default/computate-search
RUN mvn clean install -DskipTests
WORKDIR /home/default/computate-vertx
RUN mvn clean install -DskipTests
WORKDIR $HOME/computate.org
RUN mvn clean install -DskipTests
RUN rm -rf /home/default/computate-base /home/default/computate-search /home/default/computate-vertx

WORKDIR /home/default/computate.org
RUN mvn clean install -DskipTests
RUN mvn dependency:build-classpath -Dmdep.outputFile=/home/default/computate.org/cp.txt -q
CMD java -cp "$(cat /home/default/computate.org/cp.txt):/home/default/computate.org/target/classes" org.computate.site.verticle.MainVerticle
