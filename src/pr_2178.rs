fn pr_2178() {
    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).unwrap();
    let buffer:Vec<usize> = buffer.split_ascii_whitespace().map(|x| x.trim().parse::<usize>().unwrap()).collect();
    let (n, m) = ( buffer[0], buffer[1] );

    let mut map:Vec<Vec<usize>> = vec![vec![0; m]; n];
    let mut visited:Vec<Vec<usize>> = vec![vec![0; m]; n];

    for i in 0..n {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let buffer:Vec<String> = buffer.trim().split("").map(|x| x.trim().parse::<String>().unwrap()).collect();
        for j in 0..m {
            map[i][j] = buffer[j+1].parse::<usize>().unwrap();
        }
    }

    let (sy, sx) = (0, 0);
    bfs(sy, sx, &map, &mut visited, (n as isize, m as isize));

    let (ey, ex) = (n-1, m-1);
    print!("{}", visited[ey][ex]);
}

fn bfs(y:isize, x:isize, map: &Vec<Vec<usize>>, visited: &mut Vec<Vec<usize>>, (n, m):(isize, isize)) {
    let dy:Vec<isize> = vec![-1, 0, 1, 0];
    let dx:Vec<isize> = vec![0, 1, 0, -1];

    let mut queue = LinkedList::new();
    queue.push_back((y, x));
    visited[y as usize][x as usize] = 1;

    while queue.len() != 0 {
        let (y, x) = queue.pop_front().unwrap();
        for i in 0..4 {
            let ny = dy[i] + y;
            let nx = dx[i] + x;

            if ny < 0 || ny >= n || nx < 0 || nx >= m { continue }
            if map[ny as usize][nx as usize] == 0 { continue }
            if visited[ny as usize][nx as usize] != 0 { continue }

            visited[ny as usize][nx as usize] = visited[y as usize][x as usize] + 1;
            queue.push_back((ny, nx));
        }
    }
}