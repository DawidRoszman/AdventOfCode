package main

import (
	"fmt"
	"strings"
)

func PartI(file []byte) {
	safeResults := 0
	lines := strings.Split(string(file), "\n")
	for _, line := range lines[:len(lines)-1] {
		numbers := ConvertSliceOfStringsToNumbers(strings.Split(line, " "))
		lineIsSafe, _ := CheckIfLineIsSafe(numbers)
		if lineIsSafe {
			safeResults += 1
		}
	}
	fmt.Println(safeResults)
}
