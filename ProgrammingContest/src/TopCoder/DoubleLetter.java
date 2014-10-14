package TopCoder;
import java.util.ArrayList;

public class DoubleLetter {

	public static String ableToSolve(String S) {

		if (S.length() % 2 != 0)
			return "Impossible";
		ArrayList<Character> list = new ArrayList<Character>();
		for (int i = 0; i < S.length(); i++) {
			list.add(S.charAt(i));

		}
		boolean pos = false;
		while (pos == false) {
			pos = false;
			for (int i = 1; i < list.size(); i++) {
				if (list.get(i) == list.get(i - 1)) {
					list.remove(i - 1);
					list.remove(i - 1);
					pos = true;
					break;
				}
			}

			if (pos == false || list.size() == 0)
				break;
			else
				pos = false;
		}

		if (list.size() == 0)
			return "Possible";
		return "Impossible";

	}

	public static void main(String[] args) {
		System.out.println(ableToSolve("aabccb"));
	}
}
