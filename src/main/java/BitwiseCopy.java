import java.util.Random;

public class BitwiseCopy {

	public static int copyBits(int n, int m, int i, int j) {

		if (i > j || i < 0 || j >= 32) {
			throw new IllegalArgumentException("invalid bit indexes: i=" + i + ", j =" + j);
		}

		System.out.println("i=" + i + ", j=" + j);
		System.out.println(" n=" + normalize(Integer.toBinaryString(n)));
		System.out.println(" m=" + normalize(Integer.toBinaryString(m)));

		int upperShiftedM = j == 31 ? 0 : Integer.MIN_VALUE >> Math.max(0, 30 - j);
		System.out.println("um=" + normalize(upperShiftedM));
		int bottomShiftedM = (1 << i) - 1;
		int middleShiftedM = m << i;
		System.out.println("mm=" + normalize(middleShiftedM));
		System.out.println("bm=" + normalize(bottomShiftedM));
		int shiftedM = upperShiftedM | middleShiftedM | bottomShiftedM;
		System.out.println("sm=" + normalize(Integer.toBinaryString(shiftedM)));
		int mask = (j == 31 ? -1 : ((1 << j + 1) - 1)) & (Integer.MAX_VALUE << i);
		System.out.println("ms=" + normalize(Integer.toBinaryString(mask)));

		int res = (n | mask) & shiftedM;
		System.out.println("n2=" + normalize(Integer.toBinaryString(res)));
		System.out.flush();
		return res;
	}

	public static void main(String[] args) {
		Random rnd = new Random();

		for (int i = 0; i < 100; i++) {
			int n = rnd.nextInt();
			int m = rnd.nextInt();
			int x = rnd.nextInt(32);
			int y = x + rnd.nextInt(32 - x);

			int n2 = copyBits(n, m, x, y);

			String nStr = normalize(n);
			String mStr = normalize(m);
			String n2Str = normalize(n2);

			String upper = n2Str.substring(0, 31 - y);
			String middle = n2Str.substring(31 - y, 31 - x + 1);
			String bottom = n2Str.substring(31 - x + 1);

			System.out.println("x=" + x + ", y=" + y);
			assert nStr.startsWith(upper) : "U: " + upper;
			assert mStr.endsWith(middle) : "M: " + middle;
			assert nStr.endsWith(bottom) : "B: " + bottom;
		}
	}

	public static String normalize(int i) {
		return normalize(Integer.toBinaryString(i));
	}

	public static String normalize(String str) {
		if (str.length() == 32) {
			return str;
		} else {
			return normalize("0" + str);
		}
	}

}
