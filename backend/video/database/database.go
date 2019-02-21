package database

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/lib/pq"
)

const (
	DB_USER     = "florian"
	DB_PASSWORD = "IbkS=&S"
	DB_NAME     = "volume"
)

type Object struct {
	ObjectId int
	Name     string
	Width    int
	Height   int
	Depth    int
}

var db *sql.DB

func connectionDatabase() {

	var err error
	databaseInformation := fmt.Sprintf("user=%s password=%s dbname=%s sslmode=disable", DB_USER, DB_PASSWORD, DB_NAME)
	db, err = sql.Open("postgres", databaseInformation)
	checkErr(err)

	if err := db.Ping(); err == nil {
		log.Println("Connected to the database")
	} else {
		checkErr(err)
	}
}

func HasObject(name string) bool {
	rows, err := db.Query("SELECT * FROM volume WHERE name=$1", name)
	checkErr(err)

	return rows.Next()
}

func GetObject(name string) Object {
	rows, err := db.Query("SELECT * FROM volume WHERE name=$1", name)
	checkErr(err)

	var object Object
	err = rows.Scan(&object.ObjectId, &object.Name, &object.Width, &object.Depth, &object.Height)
	checkErr(err)
	return object

}

func closeDatabase(db *sql.DB) {
	if err := db.Close(); err == nil {
		log.Println("Close connection to the database")
	} else {
		checkErr(err)
	}
}

func checkErr(err error) {
	if err != nil {
		log.Println(err)
	}
}
