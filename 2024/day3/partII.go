package main

import (
	"fmt"
	"regexp"
	"strings"
)

func PartII(file []byte) {
	lines := strings.Split(string(file), "\n")
	input := strings.Join(lines[:len(lines)-1], "")
	lineRegex := regexp.MustCompile("mul\\(\\d{1,3},\\d{1,3}\\)")
	calculationRegex := regexp.MustCompile("\\d{1,3},\\d{1,3}")

	productOfCalculations := 0

	disabled := false
	lineToSplit := input
	enabled := make([]string, 0)
	for lineToSplit != "" {
		if !(strings.Contains(lineToSplit, "do()") || strings.Contains(lineToSplit, "don't()")) {
			if !disabled {
				enabled = append(enabled, lineToSplit)
			}
			break
		}
		if disabled {
			splittedByDos := strings.SplitN(lineToSplit, "do()", 2)
			if len(splittedByDos) != 2 {
				break
			}
			lineToSplit = splittedByDos[1]
			disabled = false
		} else {
			splittedByDonts := strings.SplitN(lineToSplit, "don't()", 2)
			if len(splittedByDonts) != 2 {
				break
			}
			disabled = true
			enabled = append(enabled, splittedByDonts[0])

			lineToSplit = splittedByDonts[1]
		}
	}

	fmt.Println(enabled)

	for _, value := range enabled {
		calculations := lineRegex.FindAllString(value, -1)
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
