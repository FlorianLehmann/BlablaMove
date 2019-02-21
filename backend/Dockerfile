FROM golang:alpine

RUN mkdir /app 
ADD video_upload.go /app/

WORKDIR /app 
RUN go build -o main .

EXPOSE 8080

CMD ["./main"]