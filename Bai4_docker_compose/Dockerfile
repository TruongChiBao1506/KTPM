FROM node:20-alpine

WORKDIR /app

COPY package.json .
RUN npm install

COPY src/ ./src/

# Mở port
EXPOSE 3000

# Chạy ứng dụng
CMD ["npm", "start"]