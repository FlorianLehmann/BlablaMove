package main

import (
	"bytes"
	"encoding/json"
	"io/ioutil"
	"net/http"
	"testing"

	"github.com/google/go-cmp/cmp"
)

type RelocationRequest struct {
	AddressArrival   Address   `json:"addressArrival"`
	AddressDeparture Address   `json:"addressDeparture"`
	StartDate        Date      `json:"startDate"`
	EndDate          Date      `json:"endDate"`
	Dimension        Dimension `json:"dimension"`
}

type Address struct {
	Zipcode string `json:"zipcode"`
	Street  string `json:"street"`
	City    string `json:"city"`
}

type Date struct {
	Day   int `json:"day"`
	Month int `json:"month"`
	Year  int `json:"year"`
}

type Dimension struct {
	Height int `json:"height"`
	Width  int `json:"width"`
	Depth  int `json:"depth"`
}

var relocationRequest RelocationRequest = RelocationRequest{
	AddressArrival: Address{
		Zipcode: "06560",
		Street:  "3 Route de Nice",
		City:    "Valbonne",
	},
	AddressDeparture: Address{
		Zipcode: "06560",
		Street:  "4 Route de Nice",
		City:    "Valbonne",
	},
	StartDate: Date{
		Day:   12,
		Month: 11,
		Year:  2016,
	},
	EndDate: Date{
		Day:   12,
		Month: 11,
		Year:  2016,
	},
	Dimension: Dimension{
		Height: 11,
		Width:  12,
		Depth:  13,
	},
}

func TestRelocation(t *testing.T) {
	askRelocation(t)
	checkRelocation(t)
}

func checkRelocation(t *testing.T) {
	url := "http://localhost:8080/blablamove/rest/relocations"

	var relocations []RelocationRequest

	resp, err := http.Get(url)
	if err != nil {
		t.Error(err)
	}

	defer resp.Body.Close()

	if resp.StatusCode != 200 {
		t.Errorf("Bad status code, got: %d, want: %d.", resp.StatusCode, 200)
	}

	bodyBytes, _ := ioutil.ReadAll(resp.Body)

	err = json.Unmarshal(bodyBytes, &relocations)
	if err != nil {
		t.Error(err)
	}

	if cmp.Equal(relocations[0], relocationRequest) != true {
		t.Error("Relocation are not equals")
	}

}

func askRelocation(t *testing.T) {

	url := "http://localhost:8080/blablamove/rest/relocations"

	body, err := json.Marshal(relocationRequest)
	if err != nil {
		t.Error(err)
	}

	resp, err := http.Post(url, "application/json", bytes.NewBuffer(body))
	if err != nil {
		t.Error(err)
	}

	defer resp.Body.Close()

	if resp.StatusCode != 200 {
		t.Errorf("Bad status code, got: %d, want: %d.", resp.StatusCode, 200)
	}
}
