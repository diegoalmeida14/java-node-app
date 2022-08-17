FROM node:latest
RUN mkdir -p /var/app/dist /var/app/logs
WORKDIR /var/app
RUN rm -rf dist/
COPY . /var/app
RUN yarn install
RUN yarn build
EXPOSE 4000
CMD ["yarn","dev"]