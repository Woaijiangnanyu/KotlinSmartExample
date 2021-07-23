package gjl.utils;

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
}
