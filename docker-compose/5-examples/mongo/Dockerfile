
# build mongo container from Dockerfile
FROM mongo

COPY static/init.js /docker-entrypoint-initdb.d/init.js

# docker build -t mongodb .
# docker run mongodb