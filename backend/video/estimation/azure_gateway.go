package estimation

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"

	"net/http"

	"time"
)

func Azure(image []byte, subscriptionKey string) {
	const uriBase = "https://westeurope.api.cognitive.microsoft.com/vision/v2.0/analyze"
	const params = "?visualFeatures=Objects,Description&details=Landmarks&language=en"
	const uri = uriBase + params

	client := &http.Client{
		Timeout: time.Second * 2,
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
	var f interface{}
	json.Unmarshal(data, &f)

	// Format and display the JSON result
	jsonFormatted, _ := json.MarshalIndent(f, "", "  ")
	fmt.Println(string(jsonFormatted))
}
