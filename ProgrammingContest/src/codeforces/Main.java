package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

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
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BigInteger nu = new BigInteger(in.readLine() + "");
		BigInteger num = new BigInteger("1");
		BigInteger num2 = new BigInteger("2");
		BigInteger num3 = new BigInteger("3");
		BigInteger num4 = new BigInteger("4");
		BigInteger num5 = new BigInteger("5");
		BigInteger r = num4.modPow(nu, num5).add(num3.modPow(nu, num5))
				.add(num2.modPow(nu, num5)).add(num.modPow(nu, num5));
		System.out.println(r.mod(num5));
	}
}
