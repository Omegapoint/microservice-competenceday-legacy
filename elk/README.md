Run 
docker build . -t elkos

And theeeen
docker run -p 5601:5601 -p 9200:9200 -p 5044:5044 -it --name elk elkos
