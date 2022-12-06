const text = await Deno.readTextFile("input.txt");

const characters = text.split("");

const result = (n : number) => {
for(let i = 0; i < characters.length-n; i++){
  const nextFour = characters.slice(i, i+n);
  // if duplicate in nextFour skip if not log i and break
  if(nextFour.length === new Set(nextFour).size){
    return i+n;
  }
}
}

console.log(result(4));
