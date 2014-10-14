package TopCoder;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LostParentheses {

	public static int minResult(String e) {
		ArrayList<Character> signos = new ArrayList<Character>();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		char cad[] = e.toCharArray();
		for (int i = 0; i < cad.length; i++) {
			if (cad[i] == '-' || cad[i] == '+') {
				signos.add(cad[i]);
			}
		}
		String r[] = e.split("\\+");
		for (int i = 0; i < r.length; i++) {
			String k[] = r[i].split("-");
			for (int j = 0; j < k.length; j++) {
				nums.add(Integer.parseInt(k[j]));
			}

		}

		int res = nums.get(0);
		for (int i = 1; i < nums.size();) {
			if (signos.get(i - 1) == '-') {
				res -= nums.get(i);
				
				for (int j = i; j < signos.size(); j++) {
					if (signos.get(j) == '+') {
						res -= nums.get(j + 1);
						i++;
					}else{
						break;
					}
				}
				i++;
			} else {
				res += nums.get(i);
				i++;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		System.out.println(minResult("62027+93403-16025+47588-94607+16226-24573"));
	}
}
