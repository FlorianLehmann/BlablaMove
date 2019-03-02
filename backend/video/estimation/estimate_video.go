package estimation

import (
	"log"
	"os/exec"

	"gocv.io/x/gocv"

	"../database"
)

func Estimate(uuid string) float64 {

	set := make(map[string]bool)

	img := gocv.NewMat()

	video, err := gocv.VideoCaptureFile("/tmp/" + uuid)

	if err != nil {
		log.Fatal(err)
	}

	var volume int

	database.ConnectionDatabase()

	log.Println("Start video")

	for {
		if !video.IsOpened() {
			video.Close()
			break
		}
		if !video.Read(&img) {
			log.Println("End video")
			return float64(volume) / 1000000.
		}

		out, err := exec.Command("uuidgen").Output()
		if err != nil {
			log.Fatal(err)
		}

		uuid := string(out)
		var params []int
		params = append(params, gocv.IMWritePngCompression)
		filepath := "/tmp/" + uuid + ".png"
		gocv.IMWriteWithParams(filepath, img, params)

		//var listObjects []string
		//listObjects = append(listObjects, "chair")

		listObjects := Azure(filepath, "API_KEYS")
		log.Println(listObjects)

		for _, object := range listObjects {
			if database.HasObject(object) {
				if !set[object] {
					volume += (database.GetObject(object).Width * database.GetObject(object).Height * database.GetObject(object).Depth)
				}
				set[object] = true
			}
		}
		log.Println(volume)
		return float64(volume) / 1000000.
	}

	return 0.

}
