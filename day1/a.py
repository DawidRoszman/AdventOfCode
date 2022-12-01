with open("a_input.txt") as f:
    lines = [x.strip() for x in f.readlines()]

max = 0
current = 0
for line in lines:
    if line == "":
        if current > max:
            max = current
        current = 0
        continue
    current += int(line)

print(max)
