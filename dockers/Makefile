include env_file
# FROZEN VAR DECLARATION
PWD=$(shell pwd)
PS=$(shell docker ps -a -q)
IM=$(shell docker images -q)

# Standart docker variables
NS ?= titouanfreville
REPO ?= tc_npm
VERSION ?= latest

# Project specific Variables
SHELL ?= /bin/bash
DEV_CODE ?= $(PWD)
HOSTNAME ?= $(shell hostname)

export HOSTNAME

# determine platform
ifeq (Boot2Docker, $(findstring Boot2Docker, $(shell docker info)))
  PLATFORM := OSX
else
  PLATFORM := Linux
endif

# map user and group from host to container
ifeq ($(PLATFORM), OSX)
  CONTAINER_USERNAME = root
  CONTAINER_GROUPNAME = root
  HOMEDIR = /root
  CREATE_USER_COMMAND =
  COMPOSER_CACHE_DIR = ~/dev/composer
  BOWER_CACHE_DIR = ~/dev/bower
else
  CONTAINER_USERNAME = dummy
  CONTAINER_GROUPNAME = dummy
  HOMEDIR = /home/$(CONTAINER_USERNAME)
  GROUP_ID = $(shell id -g)
  USER_ID = $(shell id -u)
  CREATE_USER_COMMAND = \
    groupadd -f -g $(GROUP_ID) $(CONTAINER_GROUPNAME) && \
    useradd -u $(USER_ID) -g $(CONTAINER_GROUPNAME) $(CONTAINER_USERNAME) && \
    mkdir -p $(HOMEDIR) &&
  COMPOSER_CACHE_DIR = /var/dev/composer
  BOWER_CACHE_DIR = /var/dev/bower
endif

# map SSH identity from host to container
DOCKER_SSH_IDENTITY ?= ~/.ssh/id_rsa
DOCKER_SSH_KNOWN_HOSTS ?= ~/.ssh/known_hosts
ADD_SSH_ACCESS_COMMAND = \
  mkdir -p $(HOMEDIR)/.ssh && \
  test -e /var/dev/id && cp /var/dev/id $(HOMEDIR)/.ssh/id_rsa ; \
  test -e /var/dev/known_hosts && cp /var/dev/known_hosts $(HOMEDIR)/.ssh/known_hosts ; \
  test -e $(HOMEDIR)/.ssh/id_rsa && chmod 600 $(HOMEDIR)/.ssh/id_rsa ;

# utility commands
AUTHORIZE_HOME_DIR_COMMAND = chown -R $(CONTAINER_USERNAME):$(CONTAINER_GROUPNAME) $(HOMEDIR) &&
EXECUTE_AS = sudo -u $(CONTAINER_USERNAME) HOME=$(HOMEDIR)

# If the first argument is one of the supported commands...
SUPPORTED_COMMANDS := composer phpunit compass bower
SUPPORTS_MAKE_ARGS := $(findstring $(firstword $(MAKECMDGOALS)), $(SUPPORTED_COMMANDS))
ifneq "$(SUPPORTS_MAKE_ARGS)" ""
  # use the rest as arguments for the command
  COMMAND_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  # ...and turn them into do-nothing targets
  $(eval $(COMMAND_ARGS):;@:)
endif

.PHONY: build push shell run start stop rm release

# nOOb user Tasks --------------------------------------------------------------
default: start_dev

dev: build up

start_dev: require rsync quick_conf dev

dev_update: update rsync dev
# ------------------------------------------------------------------------------
# Install part -----------------------------------------------------------------
test_require:
	./test_require.sh $(RSYNC_NAME)

require: test_require
	./require.sh $(NAME)

quick_conf:
	./quick_conf.sh

rsync:
	mkdir -p $(RSYNC_REPO)
	./rsync.sh $(RSYNC_NAME) $(RSYNC_REPO) $(PWD)

update:
	cd dev && git pull
	sudo composer self-update
	cd dev && composer update
# ------------------------------------------------------------------------------
# Docker compose tasks ---------------------------------------------------------
build:
	export PWD=$(DEV_CODE) && export RSYNC_REPO=$(RSYNC_REPO) && export HOSTNAME=$(HOSTNAME) && docker-compose -p $(REPO) build

up:
	./check_port.sh
	export PWD=$(DEV_CODE) && export RSYNC_REPO=$(RSYNC_REPO) && export HOSTNAME=$(HOSTNAME)  && docker-compose  -p $(REPO) up

push:
	docker push $(NS)/$(REPO):$(VERSION)

shell:
	docker-compose -p $(REPO) run web bash

cstart:
	docker-compose -p $(REPO) start

cstop:
	docker-compose -p $(REPO) stop

ckill:
	docker-compose -p $(REPO) kill

crm:
	docker-compose -p $(REPO) rm
# ------------------------------------------------------------------------------
# Other docker part ------------------------------------------------------------
clean_vfs:
	docker run -v /var/run/docker.sock:/var/run/docker.sock -v /var/lib/docker:/var/lib/docker --rm martin/docker-cleanup-volumes

release: build
	make push -e VERSION=$(VERSION)

stop:
	@-docker stop $(PS)

# Clean all the container stoped not forced
rm:
	@-docker rm -v $(PS)

# Clean all images not forced
rmi:
	@-docker rmi $(IM)
# ------------------------------------------------------------------------------
# Test part --------------------------------------------------------------------
test_build:
	docker build -t nexway-phpunit .

phpunit:
	docker run --rm -v $(DEV_CODE):/home/httpd/softgallery -v $(PWD)/logs:/home/httpd/softgallery/logs nexway-phpunit $(TEST_VAR)

test: test_build phpunit
# ------------------------------------------------------------------------------
# Clean Part -------------------------------------------------------------------
clean_rsync:
	@-rm -rf $(RSYN_REPO)

clean_docker: clean_vfs stop rm rmi

# Delete dev
clean_build:
	@-sudo rm -rf dev

clean_logs:
	@-echo -n "" > rsync.logs
	@-echo -n "" > rsync_errors.logs
	@-echo -n "" > logs/phpunit_errors.logs

clean: clean_build clean_logs clean_docker
	# docker rm $(docker ps -q -f status=exited)

distclean:
	docker images  --no-trunc | grep 'none' | awk '{print $3}' | xargs -r docker rmi

fixgit:
	find serveurs -type f -exec git update-index --assume-unchanged {} +
	find private -type f -exec git update-index --assume-unchanged {} +
	find i18n -type f -exec git update-index --assume-unchanged {} +
# ------------------------------------------------------------------------------
