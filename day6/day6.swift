#!/usr/bin/swift

import Foundation

let sample_data = "3,4,3,1,2"
let part1_data = "2,3,1,3,4,4,1,5,2,3,1,1,4,5,5,3,5,5,4,1,2,1,1,1,1,1,1,4,1,1,1,4,1,3,1,4,1,1,4,1,3,4,5,1,1,5,3,4,3,4,1,5,1,3,1,1,1,3,5,3,2,3,1,5,2,2,1,1,4,1,1,2,2,2,2,3,2,1,2,5,4,1,1,1,5,5,3,1,3,2,2,2,5,1,5,2,4,1,1,3,3,5,2,3,1,2,1,5,1,4,3,5,2,1,5,3,4,4,5,3,1,2,4,3,4,1,3,1,1,2,5,4,3,5,3,2,1,4,1,4,4,2,3,1,1,2,1,1,3,3,3,1,1,2,2,1,1,1,5,1,5,1,4,5,1,5,2,4,3,1,1,3,2,2,1,4,3,1,1,1,3,3,3,4,5,2,3,3,1,3,1,4,1,1,1,2,5,1,4,1,2,4,5,4,1,5,1,5,5,1,5,5,2,5,5,1,4,5,1,1,3,2,5,5,5,4,3,2,5,4,1,1,2,4,4,1,1,1,3,2,1,1,2,1,2,2,3,4,5,4,1,4,5,1,1,5,5,1,4,1,4,4,1,5,3,1,4,3,5,3,1,3,1,4,2,4,5,1,4,1,2,4,1,2,5,1,1,5,1,1,3,1,1,2,3,4,2,4,3,1"

var fishes = Array(repeating: 0, count: 9)
var tomorrow_fishes = Array(repeating: 0, count: 9)
for f in part1_data.components(separatedBy: ",") {
    let fi = Int(f)!
    fishes[fi]+=1
}

var days = 0

repeat {
    // print("BEFORE:", fishes, fishes.reduce(0, +))
    for i in (0...8).reversed() {
        if(i == 0) {
            tomorrow_fishes[6] += fishes[0]
            tomorrow_fishes[8] += fishes[0]
        } else {
            tomorrow_fishes[i-1] = fishes[i]
        }
    }

    // print("AFTER: ", tomorrow_fishes, tomorrow_fishes.reduce(0, +), "\n")
    fishes = tomorrow_fishes;
    tomorrow_fishes = Array(repeating: 0, count: 9)
    days += 1
} while days < 256

print("Part1:", fishes.reduce(0, +))

// 6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8
// 3, 5, 3, 2, 2, 1, 