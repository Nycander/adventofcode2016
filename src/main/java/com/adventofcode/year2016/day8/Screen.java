package com.adventofcode.year2016.day8;

public class Screen {
    private final boolean[][] pixels;

    public Screen(int width, int height) {
        this.pixels = new boolean[height][width];
    }

    public void rect(int width, int height) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                this.pixels[row][col] = true;
            }
        }
    }

    // rotate row y=A by B shifts all of the pixels in row A (0 is the top row) right by B pixels.
    // Pixels that would fall off the right end appear at the left end of the row.
    public void rotateRow(int row, int shift) {
        boolean[] newRow = new boolean[pixels[row].length];
        for (int i = pixels[row].length - 1; i >= 0; i--) {
            newRow[(i + shift) % (pixels[row].length)] = pixels[row][i];
        }
        pixels[row] = newRow;
    }

    // rotate column x=A by B shifts all of the pixels in column A (0 is the left column) down by B pixels.
    // Pixels that would fall off the bottom appear at the top of the column.
    public void rotateColumn(int column, int shift) {
        boolean[] newColumn = new boolean[pixels.length];
        for (int i = pixels.length - 1; i >= 0; i--) {
            newColumn[(i + shift) % pixels.length] = pixels[i][column];
        }

        for (int i = 0; i < newColumn.length; i++) {
            this.pixels[i][column] = newColumn[i];
        }
    }

    public int countLitPixels() {
        int count = 0;
        for (boolean[] row : pixels) {
            for (boolean pixel : row) {
                if (pixel) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public boolean[][] getPixels() {
        return pixels;
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j]) {
                    out += "#";
                } else {
                    out += ".";
                }
            }
            out += "\n";
        }
        return out;
    }
}
