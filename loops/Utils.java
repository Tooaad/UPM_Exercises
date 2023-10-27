package aed.loops;

public class Utils {
	public static int maxNumRepeated(Integer[] a, Integer elem) {
		int count = 0;
		int maxrep = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i].equals(elem)) {
				count++;
				if (maxrep < count) {
					maxrep = count;
				}
			} else {
				count = 0;
			}
		}
		return maxrep;
	}
}
