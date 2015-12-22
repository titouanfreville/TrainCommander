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

### Makefile
- run `make install_dev`

### Your self
- install node, docker, gem
- `npm install`
- `gem install compass --pre` (to check)

Dev :)

## Use Git
- Create a branch for the feature your working on (if not alredy done) (from the website)
- Dev, commit and push in this branch (`git add`,`git commit`, `git push`)
- MAKE SURE THE COMMIT MESSAGE MAKE SENSE (UPDATE feature --------- DONE: ----- TO DO: ------)
- Open a pull request to merge on master(from the website)
- Another person have to test on make sure it worked for him, then say ok and merge on master.(from the website)
- MAKE SURE TO KEEP YOUR BRANCH UP TO DATE WITH MASTER (`git checkout master && git pull && git checkout feature_branch && git merge master && git push`)

## Update the doc. 
Please update this doc to add the doc for the feature you worked on, so evry one can understang what you done, how and how to make it run.

#Feature Work
