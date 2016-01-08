# Train Commander
Final group project for 3d year supinfo's students, year 2015-2016.

# Dev Notice
## Get ready to dev ? 
 `git clone https://github.com/titouanfreville/TrainCommander.git`
### VM
- install varant && virtualbox if you want to run it on a vm
- run : `vagrant up` from your shell.

### Ansible
- install ansible if you want to deploy it on your server or environement
- run : `ansible-playbook -i "localhost," -c local dev_deploy.yml` for a local deployment.

### Makefile (Ubuntu or apt-get based distributions)
- run `make install_dev`

### Your self
-  `sudo apt-get install nodejs npm gem gem-dev`
-	`sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D`
-	`sudo echo "deb https://apt.dockerproject.org/repo ubuntu-precise main\ndeb https://apt.dockerproject.org/repo ubuntu-trusty main\ndeb https://apt.dockerproject.org/repo ubuntu-vivid main\ndeb https://apt.dockerproject.org/repo ubuntu-wily main\n" >> /etc/apt/sources.list.d/docker.list`
-	`sudo apt-get update`
-	`sudo apt-get install docker-engine`
-	`sudo usermod -aG docker $(USER)`
-	`sudo gem install --verbose compass --pre`
-	`npm install`


Dev :)

## Use Git
- Create a branch for the feature your working on from the main branch your feature depend on (if not alredy done) (from the website)
- Dev, commit and push in this branch (`git add`,`git commit`, `git push`)
- MAKE SURE THE COMMIT MESSAGE MAKE SENSE (UPDATE feature --------- DONE: ----- TO DO: ------)
- Open a pull request to merge on the main branch (from the website)
- Another person have to test on make sure it worked for him, then say ok and merge on master.(from the website)
- MAKE SURE TO KEEP YOUR BRANCH UP TO DATE WITH YOUR MAIN (`git checkout master && git pull && git checkout feature_branch && git merge master && git push`)
- You can open a PR for master, or wait for work to be done.
- When a PR is done on master, please think to update all the main branches.

## Update the doc. 
Please update this doc to add the doc for the feature you worked on, so evry one can understang what you done, how and how to make it run.

#Feature Work
## Observations and link
- The rules to observe for the website : https://kinsta.com/learn/page-speed/
- Some help to test optimisation : https://kinsta.com/blog/best-free-website-performance-testing-tools/
## Basic diagramme(?)

# Usefull Links 
## CSS, SASS, COMPASS
- http://tympanus.net/codrops/ : css ressources
- http://www.hongkiat.com/blog/css3-animation-tools/ : 10 nice tools for CSS (don't use JavaScript for evrything :p )
- http://compass-style.org/ : compass documentaion.
- https://css-tricks.com/snippets/css/a-guide-to-flexbox/ : Flexbox guide.
- https://github.com/titouanfreville/web_site/tree/master/theatre/stylesheets/scss : exemple of compass+sass/scss design.

## Docker
- https://docs.docker.com/mac/ : get started 
- https://docs.docker.com/ : full documentation
- https://hub.docker.com/ : find images and push yours.

## Nodejs and frameworks
- http://sailsjs.org/ : Sails js (home page)
- http://sailsjs.org/get-started : Sails - get started
- http://sailsjs.org/documentation/concepts/ : Sails full doc
- https://github.com/gulpjs/gulp/blob/master/docs/getting-started.md : gulp full doc
- http://gulpjs.com/ : gulp presentation
- https://facebook.github.io/react/ : react js homepage
- https://facebook.github.io/react/docs/getting-started.html : reactjs full doc
- https://angularjs.org/ : AngularJS home page
- https://docs.angularjs.org/guide : AngularJS documentation
