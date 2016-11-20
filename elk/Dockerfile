FROM sebp/elk

RUN rm /etc/logstash/conf.d/02-beats-input.conf
RUN rm /etc/logstash/conf.d/30-output.conf

COPY 01-tcp.conf /etc/logstash/conf.d/01-tcp.conf
COPY 30-output.conf /etc/logstash/conf.d/30-output.conf