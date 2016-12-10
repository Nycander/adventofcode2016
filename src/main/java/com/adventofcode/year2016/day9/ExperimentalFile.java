package com.adventofcode.year2016.day9;

public class ExperimentalFile {
    private final String decompressedContent;

    private ExperimentalFile(String decompressedContent) {
        this.decompressedContent = decompressedContent;
    }

    public static ExperimentalFile decompress(String compressed) {
        String decompressed = "";

        char[] chars = compressed.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '(':
                    int end = compressed.indexOf(')', i);
                    int xPos = compressed.indexOf('x', i);
                    int num = Integer.parseInt(compressed.substring(i + 1, xPos));
                    int times = Integer.parseInt(compressed.substring(xPos + 1, end));

                    String repeat = compressed.substring(end + 1, end + 1 + num);

                    for (int r = 0; r < times; r++) {
                        decompressed += repeat;
                    }

                    i = end + num;
                    break;
                case ')':
                case ' ':
                case '\t':
                    break;
                default:
                    decompressed += chars[i];
            }
        }

        return new ExperimentalFile(decompressed);
    }

    public int getLength() {
        return decompressedContent.length();
    }

    public String getContents() {
        return decompressedContent;
    }
}
