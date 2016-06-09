FROM nginx:stable

RUN mkdir -p var/log/rtt.troll/ && echo "" > var/log/rtt.troll/nginx.access.log && echo "" > /var/log/rtt.troll/nginx.error.log
#RUN rm /etc/nginx/conf.d/default.conf
ADD config /etc/nginx/conf.d/rtt.conf
ADD hosts /etc/hosts

ADD index.html /home/rtt/
