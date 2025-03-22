# Bước 1: Build ứng dụng trong Alpine Linux
FROM golang:alpine AS builder

WORKDIR /app
COPY . .

RUN go mod init myapp || true
RUN go mod tidy

# Build ứng dụng Go
RUN go build -o myapp

# Bước 2: Dùng Alpine Linux để chạy ứng dụng
FROM alpine:latest

WORKDIR /root/
COPY --from=builder /app/myapp .

# Cấp quyền thực thi
RUN chmod +x myapp

EXPOSE 8080
CMD ["./myapp"]