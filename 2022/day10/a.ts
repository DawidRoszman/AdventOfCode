const text = await Deno.readTextFile("input.txt");
const lines = text.split("\n");

const findNthSignalStrength = (cycle: number) => {
  let X = 1;
  let cycleCount = 0;
  lines.forEach((line) => {
    if(line.startsWith("addx")){
      const [_, signal] = line.split(" ");
      if(cycleCount+2 < cycle){
        X += parseInt(signal);
        cycleCount+=2;
      }
    }
    else if(line.startsWith("noop")){
      cycleCount+=1;
    }
    if(cycleCount >= cycle){
      return;
    } 
  }
  );
  return X*cycle;
};

const cycles = [20, 60, 100, 140, 180, 220];
let res = 0
cycles.forEach((cycle) => {
  res+=findNthSignalStrength(cycle);
});

console.log(res);
