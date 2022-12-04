const input = await Deno.readTextFile("./input.txt");

const range = (startNum: number, endNum: number) =>
  [...Array(endNum - startNum + 1).keys()].map((i) => i + startNum);

const split = input
  .split(/[\n,-]/)
  .slice(0, -1)
  .map((x) => parseInt(x));

let res = 0;

for (let i = 0; i < split.length; i += 4) {
  const [x, y, h, w] = split.slice(i, i + 4);
  const first_array = range(x, y);
  const second_array = range(h, w);

  //check if one array is in another
  const isSubset = (arr1: number[], arr2: number[]) =>
    arr2.every((val) => arr1.includes(val));
  if (
    isSubset(first_array, second_array) ||
    isSubset(second_array, first_array)
  ) {
    res++;
  }
}

console.log(res);
