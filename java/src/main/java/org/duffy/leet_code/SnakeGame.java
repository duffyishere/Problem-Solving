package org.duffy.leet_code;

public class SnakeGame {
    private final int[][] food;
    private final int[][] cell;

    private final int height;
    private final int width;

    private int y = 1;
    private int x = 1;
    private int score = 0;

    public SnakeGame(int width, int height, int[][] food) {
        this.food = food;
        this.cell = new int[height + 1][width + 1];
        this.height = height;
        this.width = width;

        setNextFood();
    }

    public int move(String direction) {
        try {
            switch (direction) {
                case "D":
                    moveDown();
                    break;
                case "T":
                    moveTop();
                    break;
                case "L":
                    moveLeft();
                    break;
                case "R":
                    moveRight();
                    break;
            }
        } catch (IllegalArgumentException e) {
            return -1;
        }

        if (cell[y][x] == -1) {
            cell[y][x]++;
            score++;
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
        if (food.length <= score) return;

        int[] position = food[score];
        cell[position[0] + 1][position[1] + 1] = -1;
    }
}
