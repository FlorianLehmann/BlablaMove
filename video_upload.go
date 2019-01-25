package video_upload

import (
	"log"
	"net/http"
	"time"
)

func handleRequests()  {
	srv := &http.Server{
		Addr:         ":8080",
		ReadTimeout:  5 * time.Second,
		WriteTimeout: 10 * time.Second,
	}

	http.HandleFunc("/video_upload", videoUpload)

	log.Fatal(srv.ListenAndServe())
}

func videoUpload(writer http.ResponseWriter, request *http.Request) {

	writer.Header().Set("Content-Type", "application/json")
	writer.Header().Set("Access-Control-Allow-Origin", "*")
	writer.Header().Set("Access-Control-Allow-Headers", "Content-Type")

	if request.Method==http.MethodPost {
		file, _, err := request.FormFile("video")
		defer file.Close()

		if err != nil {
			log.Fatal(err)
		}

		estimate(file)

	} else {
		http.Error(writer, "Bad method request", 400)
	}

}

func main() {
	handleRequests()
}
