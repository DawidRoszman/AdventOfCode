package main

import (
	"fmt"
	"log"
	"os"
	"sort"
	"strconv"
	"strings"
)

func PartI(fileName string) {
	file, err := os.ReadFile(fileName)
	if err != nil {
		log.Fatal("Couldn't read file", fileName)
	}
	lines := strings.Split(string(file), "\n")
	lines = lines[:len(lines)-1]
	leftIds := make([]int, 0)
	rightIds := make([]int, 0)
	for _, line := range lines {
		pair := strings.Split(line, "   ")
		left, err := strconv.Atoi(pair[0])
		if err != nil {
			log.Fatal("Couldn't read file", fileName)
		}
		right, err := strconv.Atoi(pair[1])
		if err != nil {
			log.Fatal("Couldn't read file", fileName)
		}
		leftIds = append(leftIds, left)
		rightIds = append(rightIds, right)
	}
	sort.Slice(leftIds, func(i, j int) bool {
		return leftIds[i] < leftIds[j]
	})
	sort.Slice(rightIds, func(i, j int) bool {
		return rightIds[i] < rightIds[j]
	})
	totalDifference := 0
	for idx := range leftIds {
		difference := rightIds[idx] - leftIds[idx]
		if difference < 0 {
			difference = difference * -1
		}
		totalDifference += difference
	}
	fmt.Println(totalDifference)
}
