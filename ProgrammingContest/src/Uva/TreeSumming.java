package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreeSumming {

	public static class Node {
		long date;
		boolean v;
		Node der;
		Node izq;
		Node father;

		public Node(long date, Node father) {
			this.date = date;
			this.der = null;
			this.izq = null;
			this.father = father;
		}

		@Override
		public String toString() {
			return "[" + (izq != null ? izq.toString() : "") + date
					+ (der != null ? der.toString() : "") + "]";
		}

	}

	public static class Tree {
		Node root;

		public Tree(Node root) {
			this.root = root;
		}

	}

	public static boolean check(StringBuilder in) {
		int izq = 0;
		int der = 0;
		//izq++;
		int cont = 0;
		while (cont < in.length()) {
			if (in.charAt(cont) == '(')
				izq++;
			else if (in.charAt(cont) == ')')
				der++;
			if (izq == der && izq>0)
				return true;
			cont++;
		}

		return false;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		StringBuilder arr = new StringBuilder();

		String line = "";
		int cont = 0;
		int izq = 0;
		int der = 0;
		String num;
		while ((line = in.readLine().trim()) != null) {
			line = line.replaceAll("[' ']*", "");
			arr.append(line);
			if (check(arr)) {
				izq=0;
				der=0;
				cont=0;
				num = "";
				if (arr.charAt(cont) == '-') {
					num += "-";
					cont++;
				}

				while (arr.charAt(cont) >= '0' && arr.charAt(cont) <= '9') {
					num += arr.charAt(cont) + "";
					cont++;
				}

				// ArrayList<Character> list = new ArrayList<Character>();
				// numbers.add(Long.parseLong(num));
				izq = 1;
				der = 0;
				cont++;
				res = false;
				search = Long.parseLong(num);
				if (arr.charAt(cont) != ')') {

					String c[] = nextNumber(cont, arr).split(" ");
					cont = Integer.parseInt(c[1]);
					Tree tree = new Tree(new Node(Long.parseLong(c[0]), null));
					Node actual = tree.root;
					actual.der = new Node(Integer.MIN_VALUE, null);
					actual.der.v = false;
					actual.izq = new Node(Integer.MIN_VALUE, null);
					actual.izq.v = false;

					// armado del arbol
					while (izq > der) {

						if (arr.charAt(cont) == '(') {
							izq++;
							cont++;
							if (arr.charAt(cont) == ')') {
								der++;
								if (actual.izq.v == false) {
									actual.izq.v = true;
								} else {
									actual.der.v = true;
									actual = actual.father;
								}
								cont++;
							} else if (actual.izq.v == false) {

								c = nextNumber(cont, arr).split(" ");
								cont = Integer.parseInt(c[1]);
								actual.izq.date = Long.parseLong(c[0]);
								actual.izq.father = actual;
								actual.izq.v = true;
								actual = actual.izq;

								actual.izq = new Node(Integer.MIN_VALUE, actual);
								actual.izq.v = false;
								actual.der = new Node(Integer.MIN_VALUE, actual);
								actual.der.v = false;
							} else {

								c = nextNumber(cont, arr).split(" ");
								cont = Integer.parseInt(c[1]);
								actual.der.date = Long.parseLong(c[0]);
								actual.der.father = actual;
								actual.der.v = true;
								actual = actual.der;

								actual.izq = new Node(Integer.MIN_VALUE, actual);
								actual.izq.v = false;
								actual.der = new Node(Integer.MIN_VALUE, actual);
								actual.der.v = false;

							}
						} else {
							if (izq == der + 1) {
								break;
							}
							if (actual.der.v == true) {
								actual = actual.father;

							}
							der++;
							cont++;
						}
					}
					cont++;

					Node temp = tree.root;
					dfs(temp, temp.date);
					if (res) {
						System.out.println("yes");
						//out.append("yes\n");
					} else {
						System.out.println("no");
						//out.append("no\n");
					}

				} else {
					//out.append("no\n");
					System.out.println("no");
					cont++;
				}
				
				arr.delete(0, cont);
			}
			
		}

		
		izq=0;
		der=0;
		cont=0;
		

		while (cont < arr.length()) {

			num = "";
			if (arr.charAt(cont) == '-') {
				num += "-";
				cont++;
			}

			while (arr.charAt(cont) >= '0' && arr.charAt(cont) <= '9') {
				num += arr.charAt(cont) + "";
				cont++;
			}

			izq=1;
			der = 0;
			cont++;
			res = false;
			search = Long.parseLong(num);
			if (arr.charAt(cont) != ')') {

				String c[] = nextNumber(cont, arr).split(" ");
				cont = Integer.parseInt(c[1]);
				Tree tree = new Tree(new Node(Long.parseLong(c[0]), null));
				Node actual = tree.root;
				actual.der = new Node(Integer.MIN_VALUE, null);
				actual.der.v = false;
				actual.izq = new Node(Integer.MIN_VALUE, null);
				actual.izq.v = false;

				// armado del arbol
				while (izq > der) {
					
					if (arr.charAt(cont) == '(') {
						izq++;
						cont++;
						if (arr.charAt(cont) == ')') {
							der++;
							if (actual.izq.v == false) {
								actual.izq.v = true;
							} else {
								actual.der.v = true;
								actual = actual.father;
							}
							cont++;
						} else if (actual.izq.v == false) {

							c = nextNumber(cont, arr).split(" ");
							cont = Integer.parseInt(c[1]);
							actual.izq.date = Long.parseLong(c[0]);
							actual.izq.father = actual;
							actual.izq.v = true;
							actual = actual.izq;

							actual.izq = new Node(Integer.MIN_VALUE, actual);
							actual.izq.v = false;
							actual.der = new Node(Integer.MIN_VALUE, actual);
							actual.der.v = false;
						} else {

							c = nextNumber(cont, arr).split(" ");
							cont = Integer.parseInt(c[1]);
							actual.der.date = Long.parseLong(c[0]);
							actual.der.father = actual;
							actual.der.v = true;
							actual = actual.der;

							actual.izq = new Node(Integer.MIN_VALUE, actual);
							actual.izq.v = false;
							actual.der = new Node(Integer.MIN_VALUE, actual);
							actual.der.v = false;

						}
					} else {
						if(izq == der+1){
							break;
						}
						if (actual.der.v == true){
							actual = actual.father;
							
						}
						der++;
						cont++;
					}
				}
				cont++;

				Node temp = tree.root;
				try {
					dfs(temp, temp.date);
				} catch (Exception e) {
					while(true);
				}
				
				if (res) {
					System.out.println("yes");
					//out.append("yes\n");
				} else {
					System.out.println("no");
					//out.append("no\n");
				}

			} else {
				//out.append("no\n");
				System.out.println("no");
				cont++;
			}


		}

		//System.out.print(out);

	}

	public static long search;
	public static boolean res;

	public static boolean dfs(Node arbol, long sum) {
		if (res)
			return true;

		if ((arbol.izq == null || arbol.izq.date == Integer.MIN_VALUE)
				&& (arbol.der.date == Integer.MIN_VALUE)) {
			if (sum == search) {
				res = true;
				return true;
			}
		} else {
			if (arbol.izq.date != Integer.MIN_VALUE) {
				dfs(arbol.izq, sum + arbol.izq.date);
			}
			if (res)
				return true;
			if (arbol.der.date != Integer.MIN_VALUE) {
				dfs(arbol.der, sum + arbol.der.date);
			}
		}
		return res;
	}

	public static String nextNumber(int index, StringBuilder actually) {
		String cad = "";
		if (actually.charAt(index) == '-') {
			cad += "-";
			index++;
		}
		while (actually.charAt(index) >= '0' && actually.charAt(index) <= '9') {
			cad += actually.charAt(index) + "";
			index++;
		}

		return cad + " " + index;
	}
}
