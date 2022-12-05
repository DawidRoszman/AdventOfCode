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

const moveStacks = (stacks: string[][], from: number, to: number, times: number) => {
  const stack : string[] = [];
  for(let i = 0; i < times; i++) {
    const temp_stack : string = stacks[from].pop()!;
    stack.push(temp_stack);
  }
  stack.reverse().forEach((s) => {
    stacks[to].push(s);
  });

}
const moves = groups[1].split('\n').slice(0,-1).map(line => line.match(/[0-9]+/g)!.map(val => parseInt(val)))

moves.forEach(move => {
    moveStacks(stacks, move[1]-1, move[2]-1, move[0]);
});


stacks.forEach(stack => {
  console.log(stack[stack.length - 1]);
});
