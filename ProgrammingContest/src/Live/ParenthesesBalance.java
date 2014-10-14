package Live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ParenthesesBalance {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[ read.length ];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	// split de String
	public static String[] atos(String cad) {
		return cad.split(" ");
	}

	// parsing de String a int
	public static int parseo(String cad, int index) {
		return Integer.parseInt(cad.split(" ")[index]);
	}

	// memory status
	static String memoryStatus() {
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() >> 20) + "/"
				+ (Runtime.getRuntime().totalMemory() >> 20) + " MB";
	}

	// imprimir array
	static void printArrayInt(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				System.out.print(" ");
			System.out.print(array[i]);
		}
		System.out.println();
	}

	// impresion de datos
	static void print(Object... obj) {
		for (int i = 0; i < obj.length; i++) {
			if (i != 0)
				System.out.print(" ");
			System.out.print(obj[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		for (int k = 0; k < n; k++) {

			String line = in.readLine().trim();
			char arr[] = line.toCharArray();
			Stack<Character> pila = new Stack<Character>();
			boolean a = false;
			for (int i = 0; i < line.length(); i++) {
				if (pila.isEmpty() && (arr[i] == ']' || arr[i] == '}' || arr[i] == ')')) {
					a = true;
					break;
				} else {
					if (arr[i] == ']') {
						if (pila.pop() != '[') {
							a = true;
							break;
						}
					} else if (arr[i] == ')') {
						if (pila.pop() != '(') {

							a = true;
							break;
						}
					} else if (arr[i] == '}') {
						if (pila.pop() != '{') {

							a = true;
							break;
						}
					} else
						pila.add(new Character(arr[i]));
				}
			}
			if (!a && pila.isEmpty()) {
				System.out.println("Yes");
			} else
				System.out.println("No");
		}

	}

}
