package main

import (
	"log"
	"os"
)

func main() {
	fileName := "test_input.txt"
	file, err := os.ReadFile(fileName)
	if err != nil {
		log.Fatal("Could not open file", fileName)
	}
	PartI(file)
	// PartII(file)

}
