const text = await Deno.readTextFile("input.test.txt");

const lines = text.split("\n").slice(0,-1).map((line) => {
  const temp = line.split(" ");
  const [dir, val] = [temp[0], parseInt(temp[1])];
  return { dir, val };
});
console.log(lines);
let head = [0,0];
let tail = [0,0];
let s = [0,0];
lines.forEach((line) => {
  switch (line.dir) {
    case "U":
      head = [head[0], head[1] + line.val];
      if(head[0] - tail[0] > 1) {
        tail = [head[0], head[1] - 1];
      }
      break;
    case "D":
      head = [head[0], head[1] - line.val];
      tail = [head[0], head[1] + 1];
      break;
    case "L":
      head = [head[0] - line.val, head[1]];
      tail = [head[0] + 1, head[1]];
      break;
    case "R":
      head = [head[0] + line.val, head[1]];
      tail = [head[0] - 1, head[1]];
      break;
  }
});
