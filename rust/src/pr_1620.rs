pub fn pr_1620() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    let mut input:Vec<usize> = input.split_ascii_whitespace().map(|x| x.trim().parse::<usize>().unwrap()).collect();
    let (n, m ):(usize, usize) = (input[0], input[1]);

    let mut pokemon_map1:HashMap<String, usize> = HashMap::new();
    let mut pokemon_map2:HashMap<usize, String> = HashMap::new();

    for i in 1..n+1 {
        let mut input = String::new();
        io::stdin().read_line(&mut input).unwrap();
        input = input.trim().parse::<String>().unwrap();
        let input_clone = input.clone();
        pokemon_map1.insert(input, i);
        pokemon_map2.insert(i, input_clone);
    }

    for _ in 0..m {
        let mut buf:String = String::new();
        io::stdin().read_line(&mut buf).unwrap();
        buf = buf.trim().parse::<String>().unwrap();

        if is_string_numeric(&buf)  {
            let buf:usize = buf.parse::<usize>().unwrap();
            println!("{}", pokemon_map2.get(&buf).unwrap());
        }
        else {
            let buf:String = buf.parse::<String>().unwrap();
            println!("{}", pokemon_map1.get(&*buf).unwrap());
        }
    }
}

fn is_string_numeric(str: &String) -> bool {
    str.parse::<usize>().is_ok()
}
