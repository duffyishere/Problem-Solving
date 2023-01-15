pub fn pr_1940() {
    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).unwrap();
    let n = buffer.trim().parse::<usize>().unwrap();

    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).unwrap();
    let m = buffer.trim().parse::<usize>().unwrap();

    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).unwrap();
    let num_list:Vec<usize> = buffer.split_ascii_whitespace().map(|x| x.trim().parse::<usize>().unwrap()).collect();

    if m > 200000 {
        println!("{}", 0);
    }
    else {
        let mut count = 0;

        for i in 0..num_list.len()-1 {
            for j in i+1..num_list.len() {
                if m == num_list[i] + num_list[j] {
                    count += 1;
                }
            }
        }

        println!("{}", count);
    }
}