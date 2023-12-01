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
losing_condition = {
        "A": "C",
        "B": "A",
        "C": "B"}
winning_condition = {
        "C": "A",
        "A": "B",
        "B": "C"}

def map_encrypted(choices):
    choice1, choice2 = choices
    if choice2 == "Y":
        choice2 = choice1
    if choice2 == "X":
        choice2 = losing_condition[choice1]
    if choice2 == "Z":
        choice2 = winning_condition[choice1]

    return (choice1, choice2)

with open("./input.txt") as f:
    lines = [x.strip().split(" ") for x in f.readlines()]
    lines = map(map_encrypted, lines)

def match_outcome(choice1: str, choice2: str) -> int:
    if choice1 == choice2:
        return 3
    if choice2 == losing_condition[choice1]:
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
