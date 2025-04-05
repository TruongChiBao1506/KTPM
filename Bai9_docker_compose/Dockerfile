FROM python:3.9-alpine

WORKDIR /app

# Cài đặt Flask
RUN pip install --no-cache-dir flask

COPY ./app /app

EXPOSE 5000


CMD ["python", "main.py"]