FROM node:7.5

EXPOSE 80

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY .babelrc /usr/src/app/
COPY config.js /usr/src/app/
COPY data_access/ /usr/src/app/data_access/
COPY database.js /usr/src/app/
COPY index.js /usr/src/app/
COPY package.json /usr/src/app/
COPY resolvers/ /usr/src/app/resolvers/
COPY server.js /usr/src/app/
COPY type_defs/ /usr/src/app/type_defs/

RUN npm install --global nodemon babel-core babel-cli
RUN npm install

CMD [ "nodemon", "-L", "--watch", "/usr/src/app", "index.js", "--exec", "babel-node"]
