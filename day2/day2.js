const { assert } = require('console');

fs = require('fs');

const DIRS = {
    forward: 0,
    down: 1,
    up: 1,
    aim: 2,
}

const mult = {
    forward: 1,
    down: 1,
    up: -1
}

function Part1() {
    let pos = [0, 0];

    fs.readFile('./day2_data.txt', 'utf-8', (err, data) => {
        if (err) console.log(err);
        data = data.split("\n");
        data.forEach(point => {
            point = point.split(" ");
            let direction = point[0];
            let amount = parseInt(point[1]);
            pos[DIRS[direction]] += mult[direction] * amount;
        });
        console.log(pos);
        console.log(pos[0] * pos[1]);
    });
}

function Part2() {
    let pos = [0, 0, 0];

    fs.readFile('/Volumes/shared/AdventOfCode/day2/day2_data.txt', 'utf-8', (err, data) => {
        if (err) console.log(err);
        data = data.split("\n");
        data.forEach(point => {
            point = point.split(" ");
            let direction = point[0];
            let amount = parseInt(point[1]);

            if(direction == "forward") {
                pos[0] += amount;
                pos[1] += amount * pos[2];
            } else {
                pos[2] += mult[direction] * amount;
            }
        });
        console.log(pos);
        console.log(pos[0] * pos[1]);
    });

}
Part2();