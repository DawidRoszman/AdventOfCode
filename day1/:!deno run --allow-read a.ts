const input = await Deno.readTextFile("./input.txt");
const lines = input.split("\n");

const calculateMaxCaloriesForN = (n: number) => {

  let sums : number[] = []
  let current : number[] = []
  lines.forEach((line) => {
    if(line == ""){
      sums.push(current.reduce((partialSum, a) => partialSum + a, 0))
      current = []
      return
    } 
    current.push(parseInt(line))
  })
  sums.sort()
  console.log(sums);
  
  return sums.slice(0,n)

} 

const res = calculateMaxCaloriesForN(1)
console.log(res)
