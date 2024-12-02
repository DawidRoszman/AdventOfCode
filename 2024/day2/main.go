package main

import (
	"log"
	"math"
	"os"
	"strconv"
)

func main() {
	fileName := "input.txt"
	file, err := os.ReadFile(fileName)
	if err != nil {
		log.Fatal("Could not open file", fileName)
	}
	// PartI(file)
	PartII(file)

}

func ConvertSliceOfStringsToNumbers(sliceOfStrings []string) []float64 {
	result := make([]float64, 0)
	for _, value := range sliceOfStrings {
		number, err := strconv.ParseFloat(value, 64)
		if err != nil {
			panic("String is not a number " + value)
		}
		result = append(result, number)
	}
	return result
}
func CheckIfLineIsSafe(numbers []float64) (bool, int) {
	previousNumber := numbers[0]
	previousOrder := numbers[0]-numbers[1] > 0
	safeLine := true
	unSafeIndex := -1
	for idx, number := range numbers[1:] {
		if previousNumber-number > 0 != previousOrder || number == previousNumber || math.Abs(number-previousNumber) > 3 {
			safeLine = false
			unSafeIndex = idx
			break
		}
		previousNumber = number
	}

	return safeLine, unSafeIndex

}
