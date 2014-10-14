package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class NonstopTravel {

	// split de array
	public static double[] atoi(String cad) {
		String read[] = cad.split(" ");
		double res[] = new double[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Double.parseDouble(read[i]);
		}
		return res;
	}

	public static class Node implements Comparable<Node> {
		double arr[];

		public Node(double[] arr) {
			super();
			this.arr = arr;
		}

		@Override
		public int compareTo(Node o) {
			if (arr[0] < o.arr[0]) {
				return -1;
			} else if (arr[0] > o.arr[0])
				return 1;
			return 0;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int times = 0;

		while ((line = in.readLine()) != null) {

			if (times++ > 0)
				line = in.readLine();
			
			if (line.equals("-1"))
				break;
			int n = Integer.parseInt(line);
			Node matrix[] = new Node[n];

			for (int i = 0; i < n; i++) {
				String cad = in.readLine();
				while (cad != cad.replace("  ", " "))
					cad = cad.replace("  ", " ");
				matrix[i] = new Node(atoi(cad));
			}
			Arrays.sort(matrix);
			boolean cond = true;
			double total = 0;
			double min;
			int cant;
			int first = -1;
			int ultimate = -1;
			boolean k=false;
			out.append("Case "+times+":");
			for (int i = 30; i <= 60; i++) {
				cond=true;
				for (int j = 0; j < matrix.length; j++) {
					total = matrix[j].arr[1] + matrix[j].arr[2]
							+ matrix[j].arr[3];
					min = (matrix[j].arr[0] / (i * 1.0)) * 3600;
					cant = (int) (min / total);
					total = total * cant;
					if (total + matrix[j].arr[1] +matrix[j].arr[2]< min) {
						cond = false;
						break;
					}
				}
				if (cond) {
					k=true;
					if (ultimate == -1) {
						first = i;
						ultimate = i;
					} else {
						ultimate = i;
					}
				} else {
					if (ultimate != -1)
						if (first == ultimate) {
							out.append(" " + first + ",");
						} else {
							out.append(" " + first + "-" + ultimate + ",");
						}
					ultimate = -1;
				}
			}
			if(ultimate!=-1){
				if (first == ultimate) {
					out.append(" " + first + ",");
				} else {
					out.append(" " + first + "-" + ultimate + ",");
				}
			}
			if(k){
				out.deleteCharAt(out.length() - 1);
				out.append("\n");
			}else{
				out.append(" No acceptable speeds.\n");
			}
		}
		System.out.print(out);
	}
}
