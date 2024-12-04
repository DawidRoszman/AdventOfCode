package main

import (
	"fmt"
	"strings"
)

var wordToFind = "XMAS"

func PartI(file []byte) {

	wordCount := 0

	lines := strings.Split(string(file), "\n")
	lines = lines[:len(lines)-1]
	letters := make([][]string, 0)
	for _, line := range lines {
		lettersInLine := strings.Split(line, "")
		letters = append(letters, lettersInLine)
	}

	for i := range letters {
		for j := range letters[i] {
			if letters[i][j] == "X" {
				if searchToRight(i, j, &letters) {
					wordCount++
				}
				if searchToLeft(i, j, &letters) {
					wordCount++
				}
				if searchDown(i, j, &letters) {
					wordCount++
				}
				if searchUp(i, j, &letters) {
					wordCount++
				}
			}
		}
	}
	fmt.Println(wordCount)
}

func searchToRight(i, j int, letters *[][]string) bool {
	if len((*letters)[i]) < j+len(wordToFind) {
		return false
	}
	line := (*letters)[i][j : j+len(wordToFind)]
	return strings.Contains(strings.Join(line, ""), wordToFind)

}

func searchToLeft(i, j int, letters *[][]string) bool {
	if j < len(wordToFind) {
		return false
	}
	line := (*letters)[i][j-len(wordToFind) : j]
	return strings.Contains(strings.Join(line, ""), StringReverse(wordToFind))
}

func searchDown(i, j int, letters *[][]string) bool {
	if len(*letters) < i+len(wordToFind) {
		return false
	}
	searchingWord := ""
	line := (*letters)[i : i+len(wordToFind)]
	for _, val := range line {
		searchingWord += val[j]
	}
	if searchingWord == wordToFind {
		return true
	}
	return false
}

func searchUp(i, j int, letters *[][]string) bool {
	if i-len(wordToFind) < 0 {
		return false
	}
	searchingWord := ""
	line := (*letters)[i-len(wordToFind) : i]
	for _, val := range line {
		searchingWord += val[j]
	}
	if StringReverse(searchingWord) == wordToFind {
		return true
	}
	return false
}

func StringReverse(inputString string) string {

	characters := strings.Split(inputString, "")

	for i, j := 0, len(characters)-1; i < j; i, j = i+1, j-1 {
		characters[i], characters[j] = characters[j], characters[i]
	}
	reversedString := strings.Join(characters, "")
	return reversedString
}
