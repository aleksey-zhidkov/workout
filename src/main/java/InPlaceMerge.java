import java.util.Arrays;
import java.util.Random;

public class InPlaceMerge {

    public static void merge(int[] a, int aLen, int[] b, int bLen) {

        int aIdx = aLen - 1;
        int bIdx = bLen - 1;
        int resIdx = a.length - 1;

        while (aIdx >= 0 || bIdx >= 0) {
            boolean isGetFromA;
            isGetFromA = aIdx >= 0 && (bIdx < 0 || a[aIdx] >= b[bIdx]);

            if (isGetFromA) {
                a[resIdx--] = a[aIdx--];
            } else {
                a[resIdx--] = b[bIdx--];
            }
        }

    }

    public static void main(String[] args) {
        Random rnd = new Random();
        for (int i = 1; i < 1000; i++) {
            int aLen = rnd.nextInt(i) + 1;
            int bLen = rnd.nextInt(aLen);
            int[] a = new int[aLen + bLen];
            int[] b = new int[bLen];

            for (int j = 0; j < aLen || j < bLen; j++) {
                if (j < aLen) {
                    a[j] = rnd.nextInt();
                }
                if (j < bLen) {
                    b[j] = rnd.nextInt();
                }
            }
            Arrays.sort(a, 0, aLen);
            Arrays.sort(b);

            int[] c = new int[aLen + bLen];
            System.arraycopy(a, 0, c, 0, aLen);
            System.arraycopy(b, 0, c, aLen, bLen);

            Arrays.sort(c);
            merge(a, aLen, b, bLen);

            for (int j = 0; j < c.length; j++) {
                assert c[j] == a[j] : "\n" + Arrays.toString(a) + "\n" + Arrays.toString(c);
            }
        }
    }

}
