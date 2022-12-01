def get_top_n_calories(n: int) -> list:
    """Return a list of the top n calories in the file

    Args:
        n (int): number of top calories to return

    Returns:
        list: list with the top n calories
    """
    with open("a_input.txt") as f:
        lines = [x.strip() for x in f.readlines()]

    sum_for_each = []
    current = 0
    for line in lines:
        if line == "":
            sum_for_each.append(current)
            current = 0
            continue
        current += int(line)
    sum_for_each.sort()
    return sum_for_each[-n:]


def main():
    print(sum(get_top_n_calories(3)))


if __name__ == "__main__":
    main()
