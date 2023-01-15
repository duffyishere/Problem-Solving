pub fn pr_9375() {
    let mut str = String::new();
    io::stdin().read_line(&mut str).unwrap();

    let test_case_count = str.trim().parse::<usize>().unwrap();
    for _ in 0..test_case_count { // test case count
        let mut n = String::new();
        io::stdin().read_line(&mut n).unwrap();
        let n:usize = n.trim_end().parse::<usize>().unwrap();
        let mut dress_map:HashMap<String, usize> = HashMap::new();

        for _ in 0..n { // 의상의 수
            let mut input = String::new();
            io::stdin().read_line(&mut input).unwrap();
            let input:Vec<String> = input.split_ascii_whitespace().map(|x| x.trim().parse::<String>().unwrap()).collect();
            let category:String = input.last().unwrap().to_owned();

            let dress_count = dress_map.get(&category);
            if dress_count.is_none() {
                dress_map.insert(category, 1);
            }
            else {
                dress_map.insert(category, dress_count.unwrap()+1);
            }
        }

        let mut ret = 1;
        for map in dress_map {
            ret *= map.1+1;
        }

        println!("{}", ret-1)
    }
}