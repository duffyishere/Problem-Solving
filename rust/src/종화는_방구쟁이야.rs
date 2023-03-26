use std::io;

fn 종화는_방구쟁이야() {
    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).unwrap();
    let buffer:Vec<usize> = buffer.split_ascii_whitespace()
        .map(|x| x.trim().parse::<usize>().unwrap())
        .collect();
    let (n, m) = (buffer[0], buffer[1]);

    let mut adj = vec![vec![0; m]; n];

    for i in 0..n {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let buffer:Vec<usize> = buffer.split_ascii_whitespace()
            .map(|x| x.trim().parse::<usize>().unwrap())
            .collect();
        for j in 0..m {
            adj[i][j] = buffer[j];
        }
    }

    let mut ret = 0;
    let mut visited = vec![vec![false; m]; n];

    for i in 0..n {
        for j in 0..m {
            if !visited[i][j] && adj[i][j] == 1 {
                dfs(i, j, &adj, &mut visited, (n, m));
                ret += 1;
            }
        }
    }

    print!("{ret}");
}

fn dfs(y:usize, x:usize, adj: &Vec<Vec<usize>>, visited:&mut Vec<Vec<bool>>, (n, m): (usize, usize)) {
    let dy:Vec<isize> = vec![-1, 0, 1, 0];
    let dx:Vec<isize> = vec![0, 1, 0, -1];
    visited[y][x] = true;

    for i in 0..4 {
        let ny:isize = y as isize - dy[i];
        let nx:isize = x as isize - dx[i];

        if ny < 0 || ny >= n as isize || nx < 0 || nx >= m as isize { continue }
        if visited[ny as usize][nx as usize] { continue }
        if adj[ny as usize][nx as usize] as usize == 0 { continue }
        dfs(ny as usize, nx as usize, &adj, visited, (n, m));
    }
}