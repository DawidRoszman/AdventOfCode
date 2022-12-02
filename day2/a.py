# For win 6 points
# For draw 3 points
# For lose 0 points
# For rock 1 point
# For paper 2 points
# For scissors 3 points

shapes_score = {
        "A": 1,
        "B": 2,
        "C": 3
        }
map_encrypted = {
        "X": "A",
        "Y": "B",
        "Z": "C"
        }

with open("./input.txt") as f:
    lines = [x.strip().split(" ") for x in f.readlines()]
    lines = [(z[0], map_encrypted[z[1]]) for z in lines]

def match_outcome(choice1, choice2):
    if choice1 == choice2:
        return 3
    if (choice1 == "A" and choice2 == "C") or (choice1 == "C" and choice2 == "B") or (choice1 == "B" and choice2 == "A"):
        return 0
    return 6

def calculate_score(matches: list[tuple]) -> int:
    score = 0
    for match in matches:
        score += shapes_score[match[1]]
        score += match_outcome(*match)
    return score

def main():
    print(calculate_score(lines))

if __name__ == "__main__":
    main()
