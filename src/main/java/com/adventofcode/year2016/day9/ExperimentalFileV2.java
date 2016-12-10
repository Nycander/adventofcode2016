package com.adventofcode.year2016.day9;

public class ExperimentalFileV2 {
    private final long decompressedContentLenght;

    private ExperimentalFileV2(long decompressedContentLenght) {
        this.decompressedContentLenght = decompressedContentLenght;
    }

    public static ExperimentalFileV2 decompress(String compressed) {
        return new ExperimentalFileV2(decompressString(compressed));
    }

    private static long decompressString(String compressed) {
        long length = 0;

        char[] chars = compressed.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '(':
                    int end = compressed.indexOf(')', i);
                    int xPos = compressed.indexOf('x', i);
                    int num = Integer.parseInt(compressed.substring(i + 1, xPos));
                    int times = Integer.parseInt(compressed.substring(xPos + 1, end));

                    length += (long) times * decompressString(compressed.substring(end + 1, end + 1 + num));

                    i = end + num;
                    break;
                case ')':
                case ' ':
                case '\t':
                    break;
                default:
                    length++;
            }
        }
        return length;
    }

    public long getLength() {
        return decompressedContentLenght;
    }

}
