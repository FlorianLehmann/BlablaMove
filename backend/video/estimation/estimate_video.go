package estimation

import (
	"log"

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

		Azure(img.ToBytes(), "password")

	}

}
