fn adjacency_matrix() {
    let mut visit = vec![];
    a[1][2] = true; a[2][1] = true;
    a[1][3] = true; a[3][1] = true;
    a[3][4] = true; a[4][3] = true;

    for i in 0..10 {
        for j in 0..10 {
            if a[i][j] && !visit[i] {
                go(i);
            }
        }
    }

    let go = |from: usize| {
        visit[from] = true;
        println!("{from}");

        for i in 0..10 {
            if visit[i] { continue; }
            if a[from][i] {
                go(i);
            }
        }
    };
}