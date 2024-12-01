package main

import (
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func PartII(fileName string) {
	file, err := os.ReadFile(fileName)
	if err != nil {
		log.Fatal("Couldn't read file", fileName)
	}
	lines := strings.Split(string(file), "\n")
	lines = lines[:len(lines)-1]
	leftMapIds := make(map[string]int)
	rightIds := make([]string, 0)
	leftIds := make([]string, 0)
	for _, line := range lines {
		pair := strings.Split(line, "   ")
		leftMapIds[pair[0]] = -1
		leftIds = append(leftIds, pair[0])
		rightIds = append(rightIds, pair[1])
	}

	for _, rightId := range rightIds {
		if leftMapIds[rightId] == 0 {
			continue
		}
		rightIdNumber, err := strconv.Atoi(rightId)
		if err != nil {
			log.Fatal(err)
		}
		if leftMapIds[rightId] == -1 {
			leftMapIds[rightId] += rightIdNumber + 1
		} else {
			leftMapIds[rightId] += rightIdNumber
		}
	}
	total := 0
	for _, idx := range leftIds {
		if leftMapIds[idx] == -1 {
			continue
		}
		total += leftMapIds[idx]
	}
	fmt.Println(total)
}
