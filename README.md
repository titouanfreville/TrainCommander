# Train Commander
Final group project for 3d year supinfo's students, year 2015-2016.

# Dev Notice
## Get ready to dev ?
 `git clone https://github.com/titouanfreville/TrainCommander.git`
### VM
- install vagrant && virtualbox if you want to run it on a vm
- run : `vagrant up` from your shell.

### Ansible
- install ansible if you want to deploy it on your server or environment
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
Please update this doc to add the doc for the feature you worked on, so every one can understand what you done, how and how to make it run.

# Feature Work
## Observations and link
- The rules to observe for the website : https://kinsta.com/learn/page-speed/
- Some help to test optimization : https://kinsta.com/blog/best-free-website-performance-testing-tools/
## Basic diagramme(?)

# Part : User and session
## Session
Session can be opened as guest or registered.
### Guest Session
Guest session have to be close after 10minutes inactive. Guest can't store trips for regular use, the can't use the Application Social Network, comment on the trips/service, ...
### registered
Registered user can set the time they want to wait before closing session due to inactivity. They can register using FB, Google or Twitter. They can register trips for regular use and payment information. They have access to our social network and can book they're place from the graphical booker.

# Part : API Book Trip
## Way to do
The client have to be able to use the SNCF API or our own API. If he use the SNCF Api, a small app using ajax will do the calls and callback.
## Our API
Our API will use a MariaDB database to store the elements, and a LoopBack nodejs API (using MVC standart).
### The database
The database will have 2 tables : station and trips. The trips table link to elements from station table, adding the departure and arrival time (using a DATE-TIME sql format)
### The API
Manage communication with database directly in loopback.
#### Function
- `FIND : string A -> string B (?) -> string[] C (?) -> Json` (?) parameters are optionals. If only station A parameters is given : Return ALL the trips from station A within the next hours (can be configure from 1 hour to a the last time known in the DB). If A and B are provided : Return all trips from A to B in the next hours (can be configure from 1 hour to a the last time known in the DB). C is a parameters you can provide when doing an A to B research. Then, it will be use to set the change you are willing to have/accept/refuse.
- `BOOK : int Trip_ID -> Json` Put Trip in the session cart
- `PAY : Json Book -> Json` Call the payment web view (passing the Book to it) so the user can pay. Wait for the callback from the PDF generator API then return a reminder to the user.

# Part : Payment web view
This is a AngularJS application.
It require a Json when called to know the product description and price.
You can pay using credit card (can be scanned) or paypal. After payment complete, it will
call the PDF generator, passing the Json to it.

# Part : PDF generator
GoLang micro API. Take the json information and generate the PDF Ticket. This Ticket will be use a call back by the Book Trip API and send by mail to the user.

# Usefull Links
## CSS, SASS, COMPASS
- http://tympanus.net/codrops/ : css resources
- http://www.hongkiat.com/blog/css3-animation-tools/ : 10 nice tools for CSS (don't use JavaScript for everything :p )
- http://compass-style.org/ : compass documentation.
- https://css-tricks.com/snippets/css/a-guide-to-flexbox/ : Flexbox guide.
- https://github.com/titouanfreville/web_site/tree/master/theatre/stylesheets/scss : example of compass+sass/scss design.

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
- https://facebook.github.io/react/ : reactjs homepage
- https://facebook.github.io/react/docs/getting-started.html : reactjs full doc
- https://angularjs.org/ : AngularJS home page
- https://docs.angularjs.org/guide : AngularJS documentation
