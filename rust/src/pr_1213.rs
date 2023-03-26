pub fn pr_1213() {
    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).unwrap();
    buffer = buffer.trim().parse::<String>().unwrap();

    let mut cnt:Vec<usize> = vec![0; 26];
    for char in buffer.chars() {
        let index = char as usize - 65;
        cnt[index] += 1;
    }

    let mut result = String::new();
    let mut odd_count = 0;
    for index in (0..cnt.len()).rev() {

        let mut count = cnt[index];
        let char = char::from_u32((index + 65) as u32).unwrap();

        if count%2 == 1 {
            count -= 1;
            odd_count = odd_count + 1;
            if odd_count > 1 {
                result = "I'm Sorry Hansoo".to_string();
                break;
            }
            result.insert(result.len()/2, char);
        }

        for _ in 0..count/2 {
            result.push(char);
            result.insert(0, char);
        }
    }

    println!("{}", result);
}