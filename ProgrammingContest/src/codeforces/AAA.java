package codeforces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class AAA {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		long inicio = System.currentTimeMillis();
		PrintWriter out = new PrintWriter(new PrintStream(new FileOutputStream(
				"output.txt")));
		BufferedReader in;
		File archivo = new File("input.txt");
		in = new BufferedReader(new FileReader(archivo));

		String line = "";
		String t = in.readLine();
		int i = Integer.parseInt(in.readLine());
		boolean left = "front".equals(t) ^ (i == 2);
		if (left)
			out.println("L");
		else
			out.println("R");
		
		out.close();
	}

}
