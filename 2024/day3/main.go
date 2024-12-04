package main

import (
	"log"
	"os"
	"strconv"
)

func main() {
	fileName := "input.txt"
	file, err := os.ReadFile(fileName)
	if err != nil {
		log.Fatal("Could not open file", fileName)
	}
	PartI(file)
	PartII(file)

}

func ConvertSliceOfStringsToNumbers(sliceOfStrings []string) []int {
	result := make([]int, 0)
	for _, value := range sliceOfStrings {
		number, err := strconv.Atoi(value)
		if err != nil {
			panic("String is not a number " + value)
		}
		result = append(result, number)
	}
	return result
}
