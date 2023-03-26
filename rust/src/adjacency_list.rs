fn adjacency_list() {
    const V: usize = 10;
    let mut adj:Vec<Vec<usize>> = vec![vec![]; V];
    adj[1].push(2);
    adj[2].push(1);
    adj[1].push(3);
    adj[3].push(1);
    adj[3].push(4);
    adj[4].push(3);

    let mut visited = vec![false; V];

    for i in 0..V {
        if adj[i].len() != 0 && !visited[i] {
            go(&i, &adj, &mut visited)
        }
    }
}

fn go(i: &usize, adj: &Vec<Vec<usize>>, visited: &mut Vec<bool>) {
    println!("{i}");
    visited[*i] = true;

    for there in &adj[*i] {
        if visited[*there] { continue; }
        go(&there, adj, visited);
    }
}