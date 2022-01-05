package sample;

import javafx.scene.control.Button;

public class WinDetector {
    public static boolean isWin(Button[][] buttons, String player) {
        int count = 0;
        return isVertical(buttons, player, count) || isRow(buttons, player, count) || isColumn(buttons, player, count);
    }

    public static boolean isVertical(Button[][] buttons, String player, int count) {
        for (int row = 0; row < buttons.length; row++) {
            if (buttons[row][row].getText().equals(player)) {
                count++;
            }
            if (count == buttons.length) {
                return true;
            }
        }
        count = 0;
        for (int row = 0; row < buttons.length; row++) {
            if (buttons[row][buttons.length - row - 1].getText().equals(player)) {
                count++;
            }
            if (count == buttons.length) {
                return true;
            }
        }
        count = 0;
        return false;
    }

    public static boolean isRow(Button[][] buttons, String player, int count) {
        for (int row = 0; row < buttons.length; row++) {
            for (int i = 0; i < buttons[row].length; i++) {
                if (buttons[row][i].getText().equals(player)) {
                    count++;
                }
                if (count == buttons.length) {
                    return true;
                }
            }
            count = 0;
        }
        return false;
    }

    public static boolean isColumn(Button[][] buttons, String player, int count) {
        for (int row = 0; row < buttons.length; row++) {
            for (int i = 0; i < buttons[row].length; i++) {
                if (buttons[i][row].getText().equals(player)) {
                    count++;
                }
                if (count == buttons.length) {
                    return true;
                }
            }
            count = 0;
        }
        return false;
    }

    public static boolean isNobodyWin(Button[][] buttons, int size) {
        int count = 0;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {

                if (!buttons[i][j].getText().equals("")) {
                    count++;
                }
                if (count == Math.pow(size, 2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
