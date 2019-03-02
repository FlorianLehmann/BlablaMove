package main

import (
	"encoding/json"
	"io"
	"log"
	"net/http"
	"os"
	"os/exec"
	"time"

	"./estimation"
)

type Response struct {
	Volume float64 `json:"volume"`
}

func handleRequests() {
	srv := &http.Server{
		Addr:         ":8081",
		ReadTimeout:  100 * time.Second,
		WriteTimeout: 100 * time.Second,
	}

	http.HandleFunc("/video_upload", videoUpload)

	log.Fatal(srv.ListenAndServe())
}

func videoUpload(w http.ResponseWriter, request *http.Request) {

	log.Println("New connection")
	if request.Method == http.MethodPost {
		file, _, err := request.FormFile("video")
		log.Println("Receive video")
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
		log.Println(uuid)
		f, err := os.OpenFile("/tmp/"+uuid, os.O_WRONLY|os.O_CREATE, 0777)
		defer f.Close()
		io.Copy(f, file)

		res := Response{estimation.Estimate(uuid)}
		w.Header().Set("Content-Type", "application/json")
		w.Header().Set("Access-Control-Allow-Origin", "*")
		w.Header().Set("Access-Control-Allow-Headers", "Content-Type")
		json.NewEncoder(w).Encode(&res)

	} else {
		http.Error(w, "Bad method request", 400)
	}

}

func main() {
	handleRequests()
}
