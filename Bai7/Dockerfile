# Sử dụng image Python chính thức
FROM python:3.9-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file app.py
COPY app.py .

# Thiết lập biến môi trường
ENV APP_ENV=development

CMD ["python", "app.py"]