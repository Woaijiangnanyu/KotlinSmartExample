package gjl.utils;

import java.util.ArrayList;

public class SmartSort {
    public static void basket(int[] data)//data为待排序数组
    {
        int n = data.length;
        int[][] bask = new int[10][n];
        int[] index = new int[10];
        int max = Integer.MIN_VALUE;
        for (int datum : data) {
            max = Math.max(max, (Integer.toString(datum).length()));
        }
        StringBuilder str;
        for (int i = max - 1; i >= 0; i--) {
            for (int datum : data) {
                str = new StringBuilder();
                if (Integer.toString(datum).length() < max) {
                    for (int k = 0; k < max - Integer.toString(datum).length(); k++)
                        str.append("0");
                }
                str.append(Integer.toString(datum));
                bask[str.charAt(i) - '0'][index[str.charAt(i) - '0']++] = datum;
            }
            int pos = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < index[j]; k++) {
                    data[pos++] = bask[j][k];
                }
            }
            for (int x = 0; x < 10; x++) index[x] = 0;
        }
    }

    public String convertToTitle (int n) {
        StringBuilder str = new StringBuilder();

        while (n > 0) {
            n--;
            str.append ( (char) ( (n % 26) + 'A'));
            n /= 26;
        }
        return str.reverse().toString();
    }

    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < (1 << n); i++) {
            System.out.println("i = "+i + "  i >> 1  = " + (i >> 1)  + "   i ^ (i >> 1)  = " + (i ^ (i >> 1)));
            result.add(i ^ (i >> 1));
        }
        return result;
    }
}
