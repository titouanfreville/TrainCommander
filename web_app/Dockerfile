FROM debian:jessie

RUN apt-get update -q && apt-get install --no-install-recommends -qyyy git npm

RUN /usr/bin/update-alternatives --install /usr/bin/node node /usr/bin/nodejs 60

CMD ["npm", "install"]
