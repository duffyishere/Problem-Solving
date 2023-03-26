fn pr_1992() {
    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).unwrap();
    let n = buffer.trim().parse::<usize>().unwrap();

    let mut map:Vec<Vec<usize>> = vec![vec![0; n]; n];

    for i in 0..n {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let mut buffer:Vec<String> = buffer.trim().split("").map(|x| x.trim().parse::<String>().unwrap()).collect();
        for j in 0..n {
            map[i][j] = buffer[j + 1].parse::<usize>().unwrap();
        }
    }

    println!("{}", go(0, 0, n, &map));
}

// (x: 0, y: 0)
fn go(x:usize, y:usize, size:usize, map:&Vec<Vec<usize>>) -> String {
    if size == 1 { return map[y][x].to_string() }
    let b = map[y][x];
    let mut ret = String::new();

    for i in y..y+size {
        for j in x..x+size {
            if map[i][j] != b {
                ret.push_str("(");
                ret.push_str(&*go(x, y, size / 2, map));
                ret.push_str(&*go(x + size / 2, y, size / 2, map));
                ret.push_str(&*go(x, y + size / 2, size / 2, map));
                ret.push_str(&*go(x + size / 2, y + size / 2, size / 2, map));
                ret.push_str(")");

                return ret
            }
        }
    }

    map[y][x].to_string()
}