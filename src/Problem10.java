
public class Problem10 {
	private static final int LENGTH = 35;
	
	private static String randomString(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			double r = Math.random();
			sb.append(r > 0.5 ? 'a' : 'b');
		}
		return sb.toString();
	}
	
	private static int recursive(String x, String y) {
		if (x.equals("")) return 0;
		if (y.equals("")) return 0;
		if (x.charAt(0) == y.charAt(0)) {
			return recursive(x.substring(1), y.substring(1)) + 1;
		} else {
			return Math.max(
				recursive(x, y.substring(1)),
				recursive(x.substring(1), y)
			);
		}
	}
	
	private static int dynamic(String x, String y) {
		int[][] L = new int[x.length()+1][y.length()+1];
		for (int i = 0; i < x.length(); ++i) {
			for (int j = 0; j < y.length(); ++j) {
				if (x.charAt(i) == y.charAt(j)) {
					L[i+1][j+1] = L[i][j] + 1;
				} else {
					L[i+1][j+1] = Math.max(L[i][j+1], L[i+1][j]);
				}
			}
		}
		
		return L[x.length()][y.length()];
	}
	
	public static void main(String[] args) {
		/*String x = "babbabab";
		String y = "bbabbaaab";*/
		
		String x = randomString(LENGTH);
		String y = randomString(LENGTH);
		
		System.out.println("String X: " + x);
		System.out.println("String Y: " + y);
		System.out.println();
		
		long time0 = System.currentTimeMillis();
		System.out.println("Recursive solution: " + recursive(x, y));
		long time1 = System.currentTimeMillis();
		System.out.println("Dynamic solution: " + dynamic(x, y));
		long time2 = System.currentTimeMillis();
		int diff1 = (int)(time1 - time0);
		int diff2 = (int)(time2 - time1);
		System.out.println("Time for recursive: " + diff1 + "ms");
		System.out.println("Time for dynamic: " + diff2 + "ms");
	}
}
