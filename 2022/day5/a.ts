const text = await Deno.readTextFile("input.txt");
//split text by blank line
const groups = text.split(/\n\s*\n/)!;
const columns = groups[0].match(/\s?[\s\[].[\s\]]/g)!.map((c) => c.replace(/\n/, ""));
const numOfStacks : number = parseInt(columns[columns.length - 1].trim());
const stacks : string[][] = Array.from(Array(numOfStacks), () => []);

columns.forEach((column, index) => {
  if (!column || !column.match(/[A-Z]/)) return;
  stacks.forEach((stack, stackIndex) => {
    if (index % numOfStacks === stackIndex) {
      stack.push(column.match(/[A-Z]/)![0]);
    }
  });
});
stacks.map((stack) => {
  stack.reverse();
});

const moveStacks = (stacks: string[][], from: number, to: number) => {
  const stack = stacks[from].pop();
  if (stack) stacks[to].push(stack);
}
const moves = groups[1].split('\n').slice(0,-1).map(line => line.match(/[0-9]+/g)!.map(val => parseInt(val)))

moves.forEach(move => {
  for(let i = 0; i < move[0]; i++) {
    moveStacks(stacks, move[1]-1, move[2]-1);
  }
});


stacks.forEach(stack => {
  console.log(stack[stack.length - 1]);
});
