package estimation

import (
	"log"
	"os/exec"

	"gocv.io/x/gocv"

	"../database"
)

func Estimate(uuid string) int {

	img := gocv.NewMat()

	video, err := gocv.VideoCaptureFile("/tmp/" + uuid)

	if err != nil {
		log.Fatal(err)
	}

	var volume int

	for {
		if !video.IsOpened() {
			video.Close()
			break
		}
		video.Read(&img)

		out, err := exec.Command("uuidgen").Output()
		if err != nil {
			log.Fatal(err)
		}

		uuid := string(out)
		var params []int
		params = append(params, gocv.IMWritePngCompression)
		filepath := "/tmp/" + uuid + ".png"
		gocv.IMWriteWithParams(filepath, img, params)
		listObjects := Azure(filepath, "my-password")

		for _, object := range listObjects {
			if database.HasObject(object) {
				volume += (database.GetObject(object).Width * database.GetObject(object).Height * database.GetObject(object).Depth)
			}
		}
	}

	return volume

}
