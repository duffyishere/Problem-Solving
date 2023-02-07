fn pr_2828() {
    let mut buffer = String::new();
    stdin().read_line(&mut buffer).unwrap();
    let mut buffer:Vec<usize> = buffer.trim().split_ascii_whitespace().map(|x| x.trim().parse::<usize>().unwrap()).collect();
    let (n, m) = (buffer[0], buffer[1]);

    let mut buffer = String::new();
    stdin().read_line(&mut buffer).unwrap();
    let j = buffer.trim().parse::<usize>().unwrap();

    let mut start = 1;
    let mut ret = 0;

    // now < next => go Right => next - now - (size-1) => 5 - 1 - (2 - 1)
    // now > next: go Left: now - next - (size-1)
    for _ in 0..j {
        let mut buffer = String::new();
        stdin().read_line(&mut buffer).unwrap();
        let mut next= buffer.trim().parse::<usize>().unwrap();

        let end = start + m - 1;

        if start <= next && next <= end {continue}
        else {
            if next < start { // go left
                ret += start - next;
                start = next;
            }
            else { // go right
                start += next - end;
                ret += next - end;
            }
        }
    }

    print!("{ret}");
}