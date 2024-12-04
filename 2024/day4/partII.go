package main

import (
	"fmt"
	"regexp"
	"strings"
)


func PartII(file []byte) {

	wordCount := 0

	lines := strings.Split(string(file), "\n")
	lines = lines[:len(lines)-1]
	letters := make([][]string, 0)
	for _, line := range lines {
		lettersInLine := strings.Split(line, "")
		letters = append(letters, lettersInLine)
	}

  grids := extractOverlapping3x3(letters)
	pattern := regexp.MustCompile("M.S.A.M.S|S.S.A.M.M|M.M.A.S.S|S.M.A.S.M")
  for _, grid := range grids{
    if (pattern.MatchString(grid)){
      wordCount++
    }
  }
  fmt.Println(wordCount)
}

func extractOverlapping3x3(grid [][]string) []string {
	var result []string

	rows := len(grid)
	if rows < 3 {
		return result // Not enough rows for a 3x3 block
	}
	cols := len(grid[0])
	if cols < 3 {
		return result // Not enough columns for a 3x3 block
	}

	// Iterate over the grid with overlapping 3x3 blocks
	for i := 0; i <= rows-3; i++ {
		for j := 0; j <= cols-3; j++ {
			var sb strings.Builder
			// Extract the 3x3 block and join strings
			for k := 0; k < 3; k++ {
				sb.WriteString(strings.Join(grid[i+k][j:j+3], ""))
			}
			result = append(result, sb.String())
		}
	}

	return result
}
