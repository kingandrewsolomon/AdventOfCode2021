from time import sleep


def part1():
    with open("day1_data.txt", "r") as f:
        init_val = int(f.readline())
        val = int(f.readline())
        incr = 0
        while val != '':
            val = int(val)
            if val > init_val:
                incr += 1
            init_val = val
            val = f.readline()

        print(incr)
        f.close()


def part2():
    with open("day1_data.txt", "rb") as f:
        window = (f.readline(), f.readline(), f.readline())
        vals = []

        while b'' not in window:
            vals.append(sum([int(x) for x in window]))
            count = sum([len(x) for x in window[1:]])
            f.seek(-count, 1)
            # sleep(1 * 10**-3)
            window = (f.readline(), f.readline(), f.readline())

        incr = 0
        init_val = vals[0]
        for x in vals:
            if x > init_val:
                incr += 1
            init_val = x

        print(incr)


part1()
part2()
