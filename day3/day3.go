package main

import(
	"fmt"
	"os"
	"bufio"
	"strings"
	"strconv"
)

func part1() int64 {
	file, err := os.Open("./day3_data.txt")
	if err != nil {
		fmt.Println(err);
		return -1;
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	oneCounter := make([]int, 12)
	zedCounter := make([]int, 12)
	
	for scanner.Scan() {
		line := scanner.Text()

		for i, char := range line {
			if char == 49 {
				oneCounter[i]++
			} else {
				zedCounter[i]++
			}
		}
	}
	g := ""
	e := ""
	for i := 0; i < len(oneCounter); i++ {
		if oneCounter[i] > zedCounter[i] {
			g += "1"
			e += "0"
		} else {
			g += "0"
			e += "1"
		}
	}
	gamma, _ := strconv.ParseInt(g, 2, 64)
	epsilon, _ := strconv.ParseInt(e, 2, 64)
	return gamma * epsilon
}

type Read struct {
	Value string
	Receding string
}

func part2() int64 {
	file, err := os.Open("./day3_data.txt")
	// file, err := os.Open("./day3_sample.txt")
	if err != nil {
		fmt.Println(err);
		return -1;
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	readings := make([]Read, 0)

	for scanner.Scan() {
		line := scanner.Text()
		readings = append(readings, Read{line, line})
	}

	oxy := calculate(readings, 1)
	cO2 := calculate(readings, 0)

	return oxy * cO2
}

func calculate(input []Read, which int) int64 {
	if len(input) == 1 {
		res, _ := strconv.ParseInt(input[0].Value, 2, 64)
		return res
	}

	oneCount := 0
	zeroCount := 0
	for _, val := range input {
		bits := strings.Split(val.Receding, "")
		if bits[0] == "1" {
			oneCount++
		} else {
			zeroCount++
		}
	}
	var result[]Read
	if which == 1 {
		result = filterOx(input, zeroCount, oneCount)
	} else {
		result = filterCO(input, zeroCount, oneCount)
	}
	return calculate(result, which)
}

func filterOx(input[]Read, zcount, ocount int) []Read {
	var result[]Read
	if ocount >= zcount {
		result = filter(input, "1")
	} else {
		result = filter(input, "0")
	}
	return result
}

func filterCO(input[]Read, zcount, ocount int) []Read {
	var result[]Read
	if zcount <= ocount {
		result = filter(input, "0")
	} else {
		result = filter(input, "1")
	}
	return result
}

func filter(input []Read, bit string) []Read {
	var result[]Read
	for _, val := range input {
		firstChar := val.Receding[0:1]
		if firstChar == bit {
			result = append(result, Read{
				Value: val.Value, 
				Receding: val.Receding[1:],
			})
		} 
	}

	return result
}


func main()  {
	fmt.Println(part2())
}