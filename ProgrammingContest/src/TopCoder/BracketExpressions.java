package TopCoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class BracketExpressions {

	public static final String possible = "possible";
	public static final String impossible = "impossible";
	public static HashMap<Character, Character> tabla = new HashMap<Character, Character>();

	public static boolean validate(Stack<Character> p, char k) {
		Iterator<Character> it = p.iterator();
		ArrayList<Character> list = new ArrayList<Character>();
		;
		while (it.hasNext()) {
			list.add(it.next());
		}
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == index) {
				index = i;
			}
		}
		if (index == -1) {
			if (p.peek() == 'X')
				return true;
			return false;
		}
		for (int i = index + 1; i < list.size(); i++) {
			if (list.get(i) != 'X')
				return false;
		}
		p.clear();
		for (int i = 0; i < list.size(); i++) {
			if (i != index)
				p.add(list.get(i));
		}
		p.add('m');

		return true;

	}

	public static String ifPossible(String expression) {
		tabla.put('(', ')');
		tabla.put('{', '}');
		tabla.put('[', ']');
		String cad = "";
		Stack<Character> pila = new Stack<Character>();

		char arr[] = expression.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (pila.isEmpty()
					&& (arr[i] == '}' || arr[i] == ')' || arr[i] == ']')) {
				cad = impossible;
				break;
			} else {
				if (arr[i] == '(' || arr[i] == '{' || arr[i] == '['
						|| arr[i] == 'X') {
					pila.push(arr[i]);
				} else if (arr[i] == '}' || arr[i] == ')' || arr[i] == ']') {
					if (pila.peek()!='X' && tabla.get(pila.peek()) == arr[i]) {
						pila.pop();
					} else if (validate(pila, tabla.get(pila.peek()))) {
						pila.pop();
					} else {
						cad = impossible;
						break;
					}
				} else {
					cad = impossible;
					break;
				}
			}
		}
		w: if (!pila.isEmpty()) {
			int cont = 0;
			while (!pila.isEmpty()) {
				if (pila.peek() != 'X') {
					cad = impossible;
					break w;
				} else {
					cont++;
					pila.pop();
				}

			}
			if (cont % 2 == 0) {
				cad = possible;
			} else {
				cad = impossible;
			}
		}
		if (cad.equals(""))
			cad = possible;

		return cad;
	}

	public static void main(String[] args) {
		ifPossible("([]X()[()]XX}[])X{{}}]");
	}
}
