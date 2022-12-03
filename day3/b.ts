const text  = await Deno.readTextFile("./input.prod.txt")
const lines = text.split("\n").slice(0, -1)
const three_lines : string[][] = []
let current : string[] = []
lines.forEach((line, idx) => {
  current.push(line)
  if((idx+1)%3 == 0){
    three_lines.push(current)
    current = []
  }
})

const out = three_lines.map((line) => {
  const first_line = line[0].split("")
  const second_line = line[1].split("")
  const third_line = line[2].split("")
  const same_char = first_line.find(n => second_line.includes(n) && third_line.includes(n))
  if (same_char == same_char?.toUpperCase())
    return same_char!.charCodeAt(0)-38
  else
    return same_char!.charCodeAt(0)-96
})

console.log(out.reduce((sum, a) => sum + a));

