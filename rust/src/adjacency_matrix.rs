use std::os::unix::raw::gid_t;

fn adjacency_matrix() {
    let M:usize = 10;
    let mut adj = vec![vec![false; M]; M];
    let mut visit = vec![false; M];
    adj[1][2] = true; adj[2][1] = true;
    adj[1][3] = true; adj[3][1] = true;
    adj[3][4] = true; adj[4][3] = true;

    for i in 0..M {
        for j in 0..M {
            if adj[i][j] && !visit[i] {
                go(i, &adj, &mut visit);
            }
        }
    }
}

fn go(index:usize, adj:&Vec<Vec<bool>>, visit: &mut Vec<bool>) {
    println!("{}", index);
    visit[index] = true;
    for j in 0..10 {
        if visit[index] { continue; }
        if adj[index][j] { go(index, adj, visit); }
    }
}