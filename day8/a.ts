const text = await Deno.readTextFile("input.txt");

const rows = text.split("\n").slice(0, -1);
const trees = rows.map(row => (row.split("").map(tree => parseInt(tree))));

const isVisible = (treeHeight: number, line: number[]) => {
  return line.filter(tree => tree >= treeHeight).length == 0;
}
let res = 0
for (let i = 1; i < trees.length-1; i++){
  for (let j = 1; j < trees.length-1; j++){
    const top = isVisible(trees[i][j], trees[i].slice(0, j));
    const bottom = isVisible(trees[i][j], trees[i].slice(j+1));
    const left = isVisible(trees[i][j], trees.slice(0, i).map(row => row[j]));
    const right = isVisible(trees[i][j], trees.slice(i+1).map(row => row[j]));
    if (top || bottom || left || right) {
      res++;
    }

  }
}

console.log(res+ (trees.length*2)+((trees.length-2)*2));
