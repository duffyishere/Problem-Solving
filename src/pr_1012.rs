use std::io;
use std::io::Write;

fn pr_1012() {
    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).unwrap();
    let t:usize = buffer.trim().parse::<usize>().unwrap();

    let mut result_buffer = String::new();
    use std::fmt::Write;
    for _ in 0..t {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let buffer:Vec<usize> = buffer.split_ascii_whitespace().map(|x| x.trim().parse::<usize>().unwrap()).collect();
        let (m, n, k):(usize, usize, usize) = (buffer[0], buffer[1], buffer[2]);

        let mut map = vec![vec![0; m]; n];
        let mut visited = vec![vec![false; m]; n];
        for _ in 0..k {
            let mut buffer = String::new();
            io::stdin().read_line(&mut buffer).unwrap();
            let buffer:Vec<usize> = buffer.split_ascii_whitespace().map(|x| x.trim().parse::<usize>().unwrap()).collect();
            let (x, y) = (buffer[0], buffer[1]);
            map[y][x] = 1;
        }

        let mut ret = 0;
        for i in 0..n {
            for j in 0..m {
                if map[i][j] == 0 { continue }
                if visited[i][j] { continue }
                dfs(j, i, &map, &mut visited, m, n);
                ret += 1;
            }
        }

        writeln!(result_buffer, "{ret}").unwrap();
    }

    print!("{}", result_buffer);
}

fn dfs(x:usize, y:usize, map:&Vec<Vec<usize>>, visited:&mut Vec<Vec<bool>>, max_x:usize, max_y:usize) {
    let dy: Vec<isize> = vec![-1, 0, 1, 0];
    let dx: Vec<isize> = vec![0, 1, 0, -1];

    visited[y][x] = true;

    for i in 0..4 {
        let my = dy[i] + y as isize;
        let mx = dx[i] + x as isize;

        if my < 0 || my >= max_y as isize || mx < 0 || mx >= max_x as isize { continue }

        let mx = mx as usize;
        let my = my as usize;
        if map[my][mx] == 0 { continue }
        if visited[my][mx] { continue }

        dfs(mx, my, map, visited, max_x, max_y);
    }
}