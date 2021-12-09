#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <string.h>
#include <math.h>
#include <vector>

#if 1
#define FILENAME "day5_data.txt"
#define B 1000
#else
#define FILENAME "day5_sample.txt"
#define B 10
#endif

using namespace std;

int grid[B][B];

struct Point {
    int x;
    int y;
};

vector<string> readFile(string filename) {
    vector<string> raw;
    ifstream file(filename);
    if(file.is_open()) {
        string buffer;
        while(file.good()) {
            getline(file, buffer);
            raw.push_back(buffer);
        }
    }
    
    file.close();
    return raw;
}

Point extractCoords(string line) {
    Point p;
    size_t comma = line.find(",");
    p.x = atoi(line.substr(0, comma).c_str());
    p.y = atoi(line.substr(comma + 1).c_str());
    return p;
}

void swap(int *a, int *b) {
    int *t;
    t = a;
    a = b;
    b = t;
}

void drawVertLine(Point p1, Point p2) {
    if(p1.y > p2.y) {
        swap(p1.y, p2.y);
    }

    for(int y = p1.y; y <= p2.y; y++) {
        grid[y][p1.x]++;
    }
}

void drawHorizLine(Point p1, Point p2) {
    if(p1.x > p2.x) {
        swap(p1.x, p2.x);
    }

    for(int x = p1.x; x <= p2.x; x++) {
        grid[p1.y][x]++;
    }
}

int sign(int x) {
    if (x == 0) return 0;
    return (signbit(x)) ? -1 : 1;
}

void drawLine(Point p1, Point p2) {
    int x, y, i;
    int dx = p2.x - p1.x;
    int dy = p2.y - p1.y;
    int n = max(abs(dx), abs(dy))+1;
    for(i = 0; i < n; i++) {
        x = p1.x + sign(dx)*i;
        y = p1.y + sign(dy)*i;
        grid[y][x]++;
    }
}

void drawBoard() {
    for(int i = 0; i < B; i++) {
        for(int j = 0; j < B; j++) {
            if(grid[i][j])
                printf("%d ", grid[i][j]);
            else
                printf(". ");
        }
        printf("\n");
    }
}

void fillBoard(vector<string> data, bool part2=false) {
    for(size_t i = 0; i < data.size(); i++) {
        size_t arrow = data.at(i).find(" -> ");
        Point p1 = extractCoords(data.at(i).substr(0, arrow));
        Point p2 = extractCoords(data.at(i).substr(arrow + 4));
        if(part2) {
            drawLine(p1, p2);
        } else {
            if(p1.x == p2.x)
                drawVertLine(p1, p2);
            if(p1.y == p2.y)
                drawHorizLine(p1, p2);
        }
    }
}

int countBoard() {
    int result = 0;
    for(int i = 0; i < B; i++) {
        for(int j = 0; j < B; j++) {
            if(grid[i][j] > 1) result++;
        }
    }
    return result;
}

void part1() {
    memset(grid, 0, B*B);
    vector<string> data = readFile(FILENAME);
    fillBoard(data);
    cout << "Part 1: " << countBoard() << "\n";
}

void part2() {
    memset(grid, 0, sizeof(int)*B*B);
    vector<string> data = readFile(FILENAME);
    fillBoard(data, true);
    cout << "Part 2: " << countBoard() << "\n";
}


int main()
{
    part1();
    part2();
    return 0;
}



