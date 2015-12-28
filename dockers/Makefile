# This is a basic makefile for docker we will create. Please adapt it for the dokcers your building.
NS = 
VERSION ?= latest
SHELL = /bin/bash

REPO = mailstorage_composed
NAME = mailstorage_composed
INSTANCE = default

PS=$(shell docker ps -a -q)
IM=$(shell docker images -q)

default: composer build

build:
	docker-compose build;

push:
	docker push $(NS)/$(REPO):$(VERSION);

release: build
	make push -e VERSION=$(VERSION)

run:
	docker run $(NS)/$(REPO):$(VERSION)

up:
	docker-compose --x-networking up

# Stop all containers not forced
stop:
	docker stop $(PS)

# Clean all the container stoped not forced
rm:
	docker rm $(PS)

# Clean all images not forced
rmi:
	whoami
	echo $(IM)
	docker rmi $(IM)

clean:
	bash -c "if [ -a tmp ] ; then cd tmp && composer remove;rm -rf tmp ;fi;"

clean_build: stop rm rmi
	make clean
