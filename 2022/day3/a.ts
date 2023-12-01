const text  = await Deno.readTextFile("./input.prod.txt")
const lines = text.split("\n").map(line => {
  return [line.slice(0, line.length/2), line.slice(line.length/2, line.length)]
}).slice(0, -1)

const out = lines.map((line) => {
  const chr_first = line[0].split("")
  const chr_sec = line[1].split("")
  const same_char = chr_first.find(n => chr_sec.includes(n))
  if (same_char == same_char?.toUpperCase())
    return same_char!.charCodeAt(0)-38
  else
    return same_char!.charCodeAt(0)-96
})

console.log(out.reduce((sum, a) => sum + a));

