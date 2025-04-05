# Sử dụng image postgres:15 làm base
FROM postgres:15

# Thiết lập biến môi trường
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=secretpassword
ENV POSTGRES_DB=myapp

# Sao chép file SQL vào thư mục initdb của PostgreSQL
COPY init.sql /docker-entrypoint-initdb.d/

# Expose port mặc định của PostgreSQL
EXPOSE 5432