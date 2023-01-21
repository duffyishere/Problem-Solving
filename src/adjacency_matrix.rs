use std::os::unix::raw::gid_t;

fn adjacency_matrix() {
    const M:usize = 10;
    let mut adj = vec![vec![false; M]; M];
    let mut visit = vec![false; M];
    a[1][2] = true; a[2][1] = true;
    a[1][3] = true; a[3][1] = true;
    a[3][4] = true; a[4][3] = true;

    for i in 0..M {
        for j in 0..M {
            if a[i][j] && !visit[i] {
                go(i, &adj, &mut visit);
            }
        }
    }
}

fn go(index:usize, adj:&Vec<Vec<bool>>, visit: &mut Vec<bool>) {
    println!("{}", index);
    visit[index] = true;
    for j in 0..V {
        if visit[index] { continue; }
        if adj[index][j] { go(index, adj, visit); }
    }
}