package main

import (
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

// var filename = "test_input.txt"
var filename = "input.txt"

func main() {
	content, err := os.ReadFile(filename)
	if err != nil {
		log.Fatal("File not found")
	}
	lines := strings.Split(string(content), "\n")
	lines = lines[:len(lines)-1]
	calibrationValues := make([]string, 0)
	for _, line := range lines {
		chars := strings.Split(line, "")
		l, r := 0, len(chars)-1
		lFound, rFound := false, false
		for !lFound || !rFound {
			if _, err := strconv.Atoi(chars[l]); err == nil {
				lFound = true
			}
			if _, err := strconv.Atoi(chars[r]); err == nil {
				rFound = true
			}
			if !lFound {
				l += 1
			}
			if !rFound {
				r -= 1
			}
		}
		calibrationValues = append(calibrationValues, chars[l]+chars[r])
	}
	sumOfCalibrationValues := 0
	for _, calibrationValue := range calibrationValues {
		calibrationValueNumber, err := strconv.Atoi(calibrationValue)
		if err != nil {
			log.Fatal("Calibration value could not be converted to number")
		}
		sumOfCalibrationValues += calibrationValueNumber
	}
	fmt.Println(sumOfCalibrationValues)

}
