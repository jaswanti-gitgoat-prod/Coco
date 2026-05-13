FROM node:18-alpine

WORKDIR /app

ARG APP_DIR=Image1

COPY ${APP_DIR}/package.json ./

RUN npm install

COPY ${APP_DIR}/ ./

CMD ["node", "index.js"]
