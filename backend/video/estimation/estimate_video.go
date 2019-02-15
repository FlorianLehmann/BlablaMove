package estimation

import (
	"log"
	"os/exec"

	"gocv.io/x/gocv"
)

func Estimate(uuid string) {

	img := gocv.NewMat()

	video, err := gocv.VideoCaptureFile("/tmp/" + uuid)

	if err != nil {
		log.Fatal(err)
	}

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
		Azure(filepath, "my-password")

	}

}
