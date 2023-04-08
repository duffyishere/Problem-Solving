extern crate core;

use std::cmp::Ordering;
use std::collections::HashMap;
use std::io::stdin;
use std::iter::Map;

fn main() {
    const MAX: usize = 1000000;
    let mut check:[bool; 1000001] = [false; 1000001];
    let mut prime:Vec<usize> = vec![];
    check[0] = true;
    check[1] = true;

    let mut i = 2;
    while i <= MAX {
        if !check[i] {
            prime.push(i);
            let mut j= i*2;
            while j <= MAX {
                check[j] = true;
                j += i;
            }
        }
        i += 1;
    }

    loop {
        let mut num = String::new();
        stdin().read_line(&mut num).unwrap();
        let num =  num.trim().parse::<usize>().unwrap();
        if num == 0 {
            break;
        }
        if num <= 4 || num % 2 == 1 {
            break;
        }
        let mut p = 0;
        while p < prime.len() {
            let p_num = prime[p];
            if !check[num - p_num] {
                println!("{} = {} + {}", num, p_num, num-p_num);
                break;
            }
            p += 1;
        }
    }
}