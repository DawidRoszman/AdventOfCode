package main

import (
	"fmt"
	"log"
	"slices"
	"strconv"
	"strings"
)

func PartI(file []byte){
  parts := strings.Split(string(file), "\n\n")
  ordering := strings.Split(parts[0], "\n")
  updated := strings.Split(parts[1], "\n")
  updated = updated[:len(updated)-1]
  orderingMap := make(map[string][]string, 0)
  results := make([][]string, 0)
  for _, order := range ordering {
    splitted := strings.Split(order, "|")
    orderingMap[splitted[0]] = append(orderingMap[splitted[0]], splitted[1])
  }
  for _, update := range updated {
    currentUpdated := strings.Split(update, ",")
    currentChecking := make([]string, 0)
    currentLineIsWrong := false
    for _, currentUpdate := range currentUpdated {
      for _, val := range orderingMap[currentUpdate]{
        if slices.Contains(currentChecking, val){
          currentLineIsWrong = true
          break
        }
      }
      if(currentLineIsWrong){
        break
      }
      currentChecking = append(currentChecking, currentUpdate)
    }
    if(!currentLineIsWrong){
      results = append(results, currentUpdated)

    }
  }
  middleSum := 0
  for _, result := range results{
    middle,err := strconv.Atoi(result[len(result)/2])
    if err != nil{
      log.Fatal("Could not convert string to int", middle)
    }
    middleSum += middle

  }
  fmt.Println(middleSum)

}
