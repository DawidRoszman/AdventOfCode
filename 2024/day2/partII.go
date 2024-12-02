package main

import (
	"fmt"
	"strings"
)

func PartII(file []byte) {
	safeResults := 0
	lines := strings.Split(string(file), "\n")
	for _, line := range lines[:len(lines)-1] {
		numbers := ConvertSliceOfStringsToNumbers(strings.Split(line, " "))
		lineIsSafe, _ := CheckIfLineIsSafe(numbers)
		if !lineIsSafe {
			for id := range numbers {
				copyNumbers := make([]float64, len(numbers))
				copy(copyNumbers, numbers)
				copyNumbers = append(copyNumbers[:id], copyNumbers[id+1:]...)
				lineIsSafe, _ = CheckIfLineIsSafe(copyNumbers)
				if lineIsSafe {
					break
				}
			}
			if lineIsSafe {
				safeResults += 1
			}
		} else {
			safeResults += 1
		}
	}
	fmt.Println(safeResults)

}
