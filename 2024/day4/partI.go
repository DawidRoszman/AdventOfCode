package main

import (
	"fmt"
	"strings"
)


func PartI(file []byte) {

  wordToFind := "XMAS"
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
			wordCount += search2D(letters, i, j, wordToFind)
		}
	}
	fmt.Println(wordCount)
}

func search2D(grid [][]string, row, col int, word string) int {
	m := len(grid)
	n := len(grid[0])

	wordLen := len(word)
	x := []int{-1, -1, -1, 0, 0, 1, 1, 1}
	y := []int{-1, 0, 1, -1, 1, -1, 0, 1}

	count := 0

	for dir := 0; dir < 8; dir++ {
		currX, currY := row, col
		k := 0
		found := true

		for k < wordLen {
			if currX < 0 || currX >= m || currY < 0 || currY >= n {
				found = false
				break
			}

			if grid[currX][currY] != string(word[k]) {
				found = false
				break
			}

			currX += x[dir]
			currY += y[dir]
			k++
		}

		if found && k == wordLen {
			count++
		}
	}

	return count
}
