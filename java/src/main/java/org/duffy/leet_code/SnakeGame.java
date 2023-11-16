package org.duffy.leet_code;

public class SnakeGame {

    private int[][] food;
    private int[][] map;

    private int height, width;

    private int y = 1;
    private int x = 1;

    private int score = 0;

    public SnakeGame(int width, int height, int[][] food) {
        this.food = food;
        this.map = new int[height + 1][width + 1];
        this.height = height;
        this.width = width;

        setNextFood();
    }

    public int move(String direction) {
        try {
            if (direction.equals("D"))
                moveDown();
            else if (direction.equals("T"))
                moveTop();
            else if (direction.equals("L"))
                moveLeft();
            else if (direction.equals("R"))
                moveRight();
        } catch (IllegalArgumentException e) {
            return -1;
        }

        // 먹이일 경우
        if (map[y][x] == -1) {
            map[y][x]++;
            score++;
            if (score < food.length)
                setNextFood();
        }

        return score;
    }

    private void moveLeft() {
        int movedX = x - 1;
        if (movedX <= 0 || width < movedX)
            throw new IllegalArgumentException(""); // todo 에러 로그 관리
        this.x = movedX;
    }

    private void moveRight() {
        int movedX = x + 1;
        if (movedX <= 0 || width < movedX)
            throw new IllegalArgumentException("");
        this.x = movedX;
    }

    private void moveTop() {
        int movedY = y - 1;
        if (movedY <= 0 || height < movedY)
            throw new IllegalArgumentException("");
        this.y = movedY;
    }

    private void moveDown() {
        int movedY = y + 1;
        if (movedY <= 0 || height < movedY)
            throw new IllegalArgumentException("");
        this.y = movedY;
    }

    private void setNextFood() {
        int[] position = food[score];
        map[position[0] + 1][position[1] + 1] = -1;
    }
}
