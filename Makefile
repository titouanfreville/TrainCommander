
default: dev-build

install_dev:
	sudo apt-get update && sudo apt-get upgrade;
	sudo apt-get install nodejs npm gem gem-dev;
	sudo ln -s /usr/bin/nodejs /usr/bin/node
	sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D;
	sudo echo "deb https://apt.dockerproject.org/repo ubuntu-precise main\ndeb https://apt.dockerproject.org/repo ubuntu-trusty main\ndeb https://apt.dockerproject.org/repo ubuntu-vivid main\ndeb https://apt.dockerproject.org/repo ubuntu-wily main\n" >> /etc/apt/sources.list.d/docker.list;
	sudo apt-get update;
	sudo apt-get install docker-engine;
	sudo usermod -aG docker $(USER);
	sudo gem install --verbose compass --pre;
	npm install;
