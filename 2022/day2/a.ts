const text = await Deno.readTextFile("./input.txt");
const lines = text.split("\n").map(line => line.split(" "));

const mapping = {
  "X": "A",
  "Y": "B",
  "Z": "C"
}

const winning_conditions = {
  "A": "C",
  "B": "A",
  "C": "B"
}

const points_mapping = {
  "A": 1,
  "B": 2,
  "C": 3
}
let score = 0;
lines.forEach(line => {
  const first_arg = line[0]
  const sec_arg  = mapping[line[1]]
  if (first_arg==sec_arg)
    score += (3 + points_mapping[mapping[line[1]]])

})
