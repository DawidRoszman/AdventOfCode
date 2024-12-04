package main

import (
	"fmt"
	"regexp"
	"strings"
)

func PartI(file []byte) {
	lines := strings.Split(string(file), "\n")
	lines = lines[:len(lines)-1]
	lineRegex := regexp.MustCompile("mul\\(\\d\\d?\\d?,\\d\\d?\\d?\\)")
	calculationRegex := regexp.MustCompile("\\d\\d?\\d?,\\d\\d?\\d?")

	productOfCalculations := 0

	for _, line := range lines {
		calculations := lineRegex.FindAllString(line, -1)
		for _, calculation := range calculations {
			numbers := ConvertSliceOfStringsToNumbers(strings.Split(calculationRegex.FindString(calculation), ","))
			if productOfCalculations == 0 {
				productOfCalculations = numbers[0] * numbers[1]
			} else {
				productOfCalculations += numbers[0] * numbers[1]
			}

		}
	}
	fmt.Println(productOfCalculations)

}
