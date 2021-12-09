<?php
if(0) {
    define("FILENAME", "day7_sample.txt");
} else {
    define("FILENAME", "day7_data.txt");
}

function read()
{
    $myfile = fopen(FILENAME, "r") or die("Unable to open file!");
    $data = fread($myfile,filesize(FILENAME));
    fclose($myfile);
    return $data;
}

function part1()
{
    $data = explode(",", read());
    $max_pos = max($data);
    $min_fuel_cost = $max_pos * count($data);
    for ($i=1; $i < $max_pos; $i++) { 
        $fuel_cost = array();
        foreach ($data as $pos) {
            array_push($fuel_cost, abs($pos - $i));
        }
        $min_fuel_cost = min(array_sum($fuel_cost), $min_fuel_cost);
    }
    echo "Part 1: ", $min_fuel_cost, "\n";
}

function part2()
{
    $data = explode(",", read());
    $max_pos = max($data);
    $min_fuel_cost = $max_pos ** count($data);
    for ($i=0; $i < $max_pos; $i++) { 
        $fuel_cost = array();
        foreach ($data as $pos) {
            $n = abs($pos - $i);
            $n = (($n * ($n + 1)) / 2);
            array_push($fuel_cost, $n);
        }
        $min_fuel_cost = min(array_sum($fuel_cost), $min_fuel_cost);
    }
    echo "Part 2: ", $min_fuel_cost, "\n";
}

part1();
part2();
?>