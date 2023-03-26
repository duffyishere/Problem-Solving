use std::io;
use std::fmt::Write;

pub fn pr_4375() {
    let mut buffer = String::new();
    loop {
        let mut input = String::new();
        io::stdin().read_line(&mut input).unwrap();
        let num = match input.trim().parse::<u32>() {
            Ok(num) => { num }
            Err(_) => { break; }
        };

        let mut ret = 1;
        let mut count = 1;
        loop {
            if count % num == 0 {
                writeln!(&mut buffer, "{}", ret).unwrap();
                break;
            }
            else {
                count = count * 10 + 1;
                count %= num;
                ret += 1;
            }
        }
    }
    print!("{buffer}");
}