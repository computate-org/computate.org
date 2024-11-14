#!/bin/bash

cd /home/ctate/.local/src/computate.org
env VARS_PATH=/home/ctate/.local/src/computate.org/vars.yaml \
  COMPUTATE_SRC=/home/ctate/.local/src/computate \
  COMPUTATE_VERTX_SRC=/home/ctate/.local/src/computate-vertx \
  SITE_LANG=enUS \
  /home/ctate/.local/src/computate/bin/enUS/generate.sh
