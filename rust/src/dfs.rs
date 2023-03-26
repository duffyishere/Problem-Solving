fn dfs(u: usize, adj: Vec<usize>, visited: Vec<bool>) {
    visited[u] = true;

    for v in adj[u] {
        if !visited[v] {
            dfs(v, adj, visited);
        }
    }
}