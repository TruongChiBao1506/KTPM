# Sử dụng image php:8.2-apache làm base
FROM php:8.2-apache


# Kích hoạt module rewrite của Apache (tùy chọn)
RUN a2enmod rewrite

# Thiết lập thư mục làm việc
WORKDIR /var/www/html

# Expose port 80
EXPOSE 80