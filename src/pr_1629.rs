use std::io;

pub fn pr_1629() {
    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).unwrap();
    let num_list:Vec<u32> = buffer.split_ascii_whitespace().map(|x| x.trim().parse::<u32>().unwrap()).collect();
    let (a, b, c) = (num_list[0], num_list[1], num_list[2]);

    println!("{}", go(a, b, c));
}

fn go(a:u32, b:u32, c:u32) -> u32 {
    println!("{}", a%c);
    if b == 1 { return a % c; }

    let mut ret = go(a, b/2, c);
    ret = (ret * ret) % c;

    if b % 2 == 1 { ret = (ret * a) % c; }

    ret
}
