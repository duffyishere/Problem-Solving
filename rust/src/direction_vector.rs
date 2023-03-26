/*
Q. 3 * 3 맵을 입력받아야 함. 이 맵은 1과 0으로 이루어져있고 {0, 0}은 무조건 1임을 보장한다.
{0, 0}부터 4방향을 기준으로 한칸씩 탐색해나가며 방문한 정점은 다시 방문하지 않으며 방문하는 좌표를 출력하는 코드.
0은 갈 수 없는 지역. 1은 갈 수 있는 지역을 구현하시오.

1 0 1
1 0 1
0 1 1
*/
fn four_way_search() {
    const n:usize = 3;
    let mut visited = vec![vec![false; n]; n];
    let mut map = vec![vec![0; n]; n];

    for y in 0..n {
        let mut buffer = String::new();
        stdin().read_line(&mut buffer).unwrap();
        let buffer: Vec<usize> = buffer.split_ascii_whitespace()
            .map(|x| x.trim().parse::<usize>().unwrap())
            .collect();
        for x in 0..n {
            map[y][x] = buffer[x];
        }
    }

    go(&map, &mut visited, 0, 0);
}

fn go(map: &Vec<Vec<usize>>, visited:&mut Vec<Vec<bool>>, y:usize, x:usize) {
    let dy:Vec<isize> = vec![-1, 0, 1, 0];
    let dx:Vec<isize> = vec![0, 1, 0, -1];

    visited[y][x] = true;
    println!("y: {y}, x: {x}");

    for i in 0..4 {
        let ny:isize = y as isize + dy[i];
        let nx:isize = x as isize+ dx[i];
        if ny < 0 || ny >= 3 || nx < 0 || nx >=3 { continue }
        if map[ny as usize][nx as usize] == 0 { continue }
        if visited[ny as usize][nx as usize] { continue }
        go(&map, visited, ny as usize, nx as usize);
    }
}