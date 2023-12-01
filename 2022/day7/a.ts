const text = await Deno.readTextFile("input_test.txt");

const lines = text.split("\n");
const dirSizes : { [name: string]: number } = {};

const searchDir = (dirName: string, lines: string[]) => {
  let dirSize = 0; 
  let depth = 1;
  for (let i = 0; i < lines.length; i++) {
    const line = lines[i];
    if (line.startsWith("d")) {
      dirName = line.split(" ")[1];
      const dirLines = lines.slice(lines.indexOf(`$ cd ${dirName}`, i) + 1);
      const dirSize = searchDir(dirName, dirLines);
      dirSizes[dirName] = dirSize;
    } 
    else if (line.startsWith("$ cd ") && line != "$ cd .."){
      depth += 1
    }
    else if (line.match(/\d/)) {
      const fileSize = parseInt(line.split(" ")[0]);
      dirSize += fileSize;
    } else if (line.startsWith("$ cd ..")) {
        depth -= 1;
        if(depth <= 0)
          return dirSize;
    }
  }
  return dirSize;
};

const main = () => {
  searchDir("", lines);
  console.log(dirSizes);
  console.log(Object.values(dirSizes).filter(size => size <= 100000).reduce((sum, a) => sum + a));
};

main();

