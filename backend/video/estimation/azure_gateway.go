package estimation

import (
	"bytes"
	"encoding/json"
	"io/ioutil"
	"log"

	"net/http"

	"time"
)

type AzureAnswer struct {
	Categories []struct {
		Name   string  `json:"name"`
		Score  float64 `json:"score"`
		Detail struct {
			Landmarks []interface{} `json:"landmarks"`
		} `json:"detail"`
	} `json:"categories"`
	Description struct {
		Tags     []string `json:"tags"`
		Captions []struct {
			Text       string  `json:"text"`
			Confidence float64 `json:"confidence"`
		} `json:"captions"`
	} `json:"description"`
	Objects   []interface{} `json:"objects"`
	RequestID string        `json:"requestId"`
	Metadata  struct {
		Width  int    `json:"width"`
		Height int    `json:"height"`
		Format string `json:"format"`
	} `json:"metadata"`
}

func Azure(filepath string, subscriptionKey string) []string {
	image, err := ioutil.ReadFile(filepath)
	if err != nil {
		panic(err)
	}
	const uriBase = "https://westeurope.api.cognitive.microsoft.com/vision/v2.0/analyze"
	const params = "?visualFeatures=Objects,Description&details=Landmarks&language=en"
	const uri = uriBase + params

	client := &http.Client{
		Timeout: time.Second * 10,
	}
	stream := bytes.NewBuffer(image)

	req, err := http.NewRequest("POST", uri, stream)
	if err != nil {
		panic(err)
	}
	req.Header.Set("Content-Type", "application/octet-stream")
	req.Header.Add("Ocp-Apim-Subscription-Key", subscriptionKey)

	resp, err := client.Do(req)
	if err != nil {
		panic(err)
	}

	defer resp.Body.Close()

	// Read the response body
	// Note, data is a byte array
	data, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}

	// Parse the JSON data from the byte array
	var ans AzureAnswer
	err = json.Unmarshal(data, &ans)
	if err != nil {
		log.Fatal(err)
	}

	return ans.Description.Tags
}
