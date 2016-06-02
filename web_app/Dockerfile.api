FROM node:argon

RUN mkdir -p /hom/traincommander/api

WORKDIR /home/traincommander/api

RUN npm install -g strongloop

COPY api/package.json /home/traincommander/api
RUN npm install

COPY api /home/traincommander/api

EXPOSE 4000 3000

CMD slc run
