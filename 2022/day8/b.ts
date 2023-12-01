const text = await Deno.readTextFile("input.txt");

const rows = text.split("\n").slice(0, -1);
const trees = rows.map((row) => row.split("").map((tree) => parseInt(tree)));

const isVisible = (treeHeight: number, line: number[]) => {
  let out = 0;
  if(line[0] > treeHeight){
    return 1;
  }
  for (let i = 0; i < line.length; i++) {
    if (line[i] < treeHeight) {
      out++;
    } else if (line[i] >= treeHeight) {
      out++;
      break;
    }
  }
  return out;
};
let max_res = 0;
let res = 0;
for (let i = 1; i < trees.length-1; i++) {
  for (let j = 1; j < trees.length-1; j++) {
    const top = isVisible(trees[i][j], trees[i].slice(0, j).reverse());
    const bottom = isVisible(trees[i][j], trees[i].slice(j + 1));
    const left = isVisible(
      trees[i][j],
      trees.slice(0, i).map((row) => row[j]).reverse(),
    );
    const right = isVisible(
      trees[i][j],
      trees.slice(i + 1).map((row) => row[j])
    );

    res = top * bottom * left * right;
    if (res > max_res) {
      max_res = res;
    }
  }
}

console.log(max_res);
