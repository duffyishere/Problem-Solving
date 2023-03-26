fn pr_2468() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    let m = input.trim().parse::<usize>().unwrap();

    let mut map:Vec<Vec<usize>> = vec![vec![0; m]; m];

    for i in 0..m {
        let mut input = String::new();
        io::stdin().read_line(&mut input).unwrap();
        let input:Vec<usize> = input.trim().split_ascii_whitespace().map(|x| x.trim().parse::<usize>().unwrap()).collect();
        for j in 0..m {
            map[i][j] = input[j];
        }
    }

    let mut ret = 0;

    for d in 0..101 {
        let mut visited:Vec<Vec<bool>> = vec![vec![false; m]; m];

        let mut count = 0;
        for y in 0..m {
            for x in 0..m {
                if map[y][x] > d && !visited[y][x] {
                    dfs(x, y, &map, &mut visited, m, d);
                    count += 1;
                }
            }
        }
        if count > ret {
            ret = count;
        }
    }

    print!("{ret}");
}

fn dfs(x:usize, y:usize, map: &Vec<Vec<usize>>, visited:&mut Vec<Vec<bool>>, m:usize, d:usize) {
    let dy :Vec<isize> = vec![-1, 0, 1, 0];
    let dx :Vec<isize> = vec![0, 1, 0, -1];

    visited[y][x] = true;
    for i in 0..4 as usize {
        let my = dy[i] + y as isize;
        let mx = dx[i] + x as isize;

        if my < 0 || my >= m as isize || mx < 0 || mx >= m as isize{ continue }
        let my:usize = my as usize;
        let mx:usize = mx as usize;
        if map[my][mx] <= d { continue }
        if visited[my][mx] { continue }

        dfs(mx, my, map, visited, m, d);
    }
}