package main

import (
	"io"
	"log"
	"net/http"
	"os"
	"os/exec"
	"time"
)

func handleRequests() {
	srv := &http.Server{
		Addr:         ":8080",
		ReadTimeout:  5 * time.Second,
		WriteTimeout: 10 * time.Second,
	}

	http.HandleFunc("/video_upload", videoUpload)

	log.Fatal(srv.ListenAndServe())
}

func videoUpload(writer http.ResponseWriter, request *http.Request) {

	writer.Header().Set("Access-Control-Allow-Origin", "*")
	writer.Header().Set("Access-Control-Allow-Headers", "Content-Type")

	if request.Method == http.MethodPost {
		file, _, err := request.FormFile("video")

		if err != nil {
			log.Fatal(err)
		}
		defer file.Close()

		// how to create uuid
		out, err := exec.Command("uuidgen").Output()
		if err != nil {
			log.Fatal(err)
		}

		uuid := string(out)
		f, err := os.OpenFile("/tmp/"+uuid, os.O_WRONLY|os.O_CREATE, 0777)
		defer f.Close()
		io.Copy(f, file)

		//estimate(uuid)

	} else {
		http.Error(writer, "Bad method request", 400)
	}

}

func main() {
	handleRequests()
}
