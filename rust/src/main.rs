extern crate core;

use std::cmp::Ordering;
use std::collections::HashMap;
use std::iter::Map;

fn main() {
    let mut buffer = String::new();
    std::io::stdin().read_line(&mut buffer).unwrap();
    let buffer:Vec<usize> = buffer.split_ascii_whitespace()
        .map(|x| x.trim().parse::<usize>().unwrap())
        .collect();
    let (n, c):(usize, usize) = (buffer[0], buffer[1]);

    let mut buffer = String::new();
    std::io::stdin().read_line(&mut buffer).unwrap();
    let messages:Vec<usize> = buffer.split_ascii_whitespace()
        .map(|x| x.trim().parse::<usize>().unwrap())
        .collect();

    // 정렬 조건: 1. 많이 나온 것, 2. 먼저 나온 것
    // key = 문자열, value = 문자열의 index
    let mut vector: Vec<(usize, usize)> = vec![];
    let mut map: HashMap<usize, usize> = HashMap::new();
    for i in 0..messages.len() {
        if map.get(&i).is_none() {
           map.insert(messages[i], i);
        }
    }

    for (key, value) in &map {
        vector.push((*value, *key));
    }

    vector.sort_by(|&(a1, a2), &(b1, b2)| {
        if a1 == b1 {
            if &map[&a2] < &map[&b2] {
                Ordering::Greater
            } else {
                Ordering::Less
            }
        }
        else {
            if a1 > b1 {
                Ordering::Greater
            }
            else {
                Ordering::Less
            }
        }
    })

    for
}