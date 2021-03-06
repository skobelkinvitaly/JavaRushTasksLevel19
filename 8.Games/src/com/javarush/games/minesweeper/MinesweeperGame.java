package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private int score;
    private int countClosedTiles = SIDE * SIDE;
    private boolean isGameStopped;
    private int countFlags;
    private static final String FLAG = "\uD83D\uDEA9";
    private static final String MINE = "\uD83D\uDCA3";
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
                setCellValue(x, y, "");
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        List<GameObject> list;
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                GameObject obj = gameField[i][j];
                if (!obj.isMine) {
                    list = getNeighbors(obj);
                    for (int k = 0; k < list.size(); k++) {
                        if (list.get(k).isMine) obj.countMineNeighbors++;
                    }
                }
            }
        }
    }

    private void openTile(int x, int y) {
        if (gameField[y][x].isOpen == true || gameField[y][x].isFlag == true || isGameStopped == true) {

        } else {
            int count = gameField[y][x].countMineNeighbors;
            gameField[y][x].isOpen = true;
            countClosedTiles--;
            setCellColor(x, y, Color.GREEN);
            if (gameField[y][x].isMine && count != 0) {
            }
            if (!gameField[y][x].isMine && count != 0) {
                setCellNumber(x, y, gameField[y][x].countMineNeighbors);
            }
            if (!gameField[y][x].isMine && count == 0) {
                List<GameObject> neighbors = getNeighbors(gameField[y][x]);
                setCellValue(x, y, "");
                for (int i = 0; i < neighbors.size(); i++) {
                    if (neighbors.get(i).isOpen == false) openTile(neighbors.get(i).x, neighbors.get(i).y);
                }
            }
            if (gameField[y][x].isMine == true) {
                setCellValueEx(x, y, Color.RED, MINE);
                gameOver();
            }
            if (countClosedTiles == countMinesOnField && gameField[y][x].isMine == false) win();
            if (gameField[y][x].isMine == false && gameField[y][x].isOpen == true) {
                score = score + 5;
                setScore(score);
            }
        }
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped == true) {
            restart();
        } else openTile(x, y);
    }

    private void markTile(int x, int y) {
        if (isGameStopped == true) {
        } else {
            if (gameField[y][x].isOpen == false && countFlags != 0 && gameField[y][x].isFlag == false) {
                gameField[y][x].isFlag = true;
                countFlags = countFlags - 1;
                setCellValue(x, y, FLAG);
                setCellColor(x, y, Color.YELLOW);
            } else if (gameField[y][x].isOpen == false && gameField[y][x].isFlag == true) {
                gameField[y][x].isFlag = false;
                countFlags++;
                setCellValue(x, y, "");
                setCellColor(x, y, Color.ORANGE);
            }
        }
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "Game over!", Color.BLACK, 50);


    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.YELLOW, "Win!", Color.BLACK, 50);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        countMinesOnField = 0;
        setScore(score);
        createGame();
//        for (int i = 0; i < SIDE; i++) {
//            for (int j = 0; j < SIDE; j++) {
//                setCellValue(j, i, "");
//            }
//        }
    }
}