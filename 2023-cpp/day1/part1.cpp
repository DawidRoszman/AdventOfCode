#include <cctype>
#include <iostream>
#include <fstream>
#include <ostream>
#include <string>

int findNumbersInLine(std::string *line){
  int l = 0, r = (*line).length();
  char lValue, rValue;
  bool lFound = false, rFound = false;
  while (!(lFound && rFound))
  {
    if(isdigit((*line)[l]))
    {
      lValue = (*line)[l];
      lFound = true;
    }
    if(!lFound)
      l++;
    if(isdigit((*line)[r]))
    {
      rValue = (*line)[r];
      rFound = true;
    } 
    if(!rFound)
      r--;  
  }
  std::string result = std::string(1,lValue) + rValue;
  return std::stoi(result);
}

int main(){
  std::ifstream inf{"input.txt"};
  if(!inf)
  {
    std::cerr << "File could not be opened for reading!\n";
    return 1;
  }

  int sum = 0;
  std::string strInput;
  while (inf >> strInput)
  {
    int lineNumber = findNumbersInLine(&strInput);
    sum+=lineNumber;

  }
  std::cout << sum << std::endl;
  return 0;
}
