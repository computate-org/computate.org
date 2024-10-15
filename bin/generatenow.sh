#!/bin/bash

cd $HOME/.local/src/computate.org
env WATCH=false GENERATE_NOW=true VARS_PATH=$HOME/.local/src/computate.org/vars.yaml COMPUTATE_SRC=$HOME/.local/src/computate SITE_LANG=enUS $HOME/.local/src/computate/bin/enUS/generate.sh
