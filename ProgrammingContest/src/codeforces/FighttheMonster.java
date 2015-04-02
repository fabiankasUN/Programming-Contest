package codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FighttheMonster {

	public static int[] readInts(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static long[] readLongs(String cad) {
		String read[] = cad.split(" ");
		long res[] = new long[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Long.parseLong(read[i]);
		}
		return res;
	}

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

	static void printMatrixInt(int[][] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				if (j > 0)
					System.out.print(" ");
				System.out.print(array[i][j]);
			}
			System.out.println();
		}

	}

	public static int max(int arr[]) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}

	public static int min(int arr[]) {
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		StringBuilder out = new StringBuilder();
		File f = new File("entrada");
		if (f.exists()) {
			in = new BufferedReader(new FileReader(f));
		} else
			in = new BufferedReader(new InputStreamReader(System.in));

		int HPy, ATKy, DEFy, HPm, ATKm, DEFm;
		int priceH, priceA, priceD;
		int d[];
		int temp;
		int minPrice = Integer.MAX_VALUE;
		int hitM;
		int hitY;
		d = readInts(in.readLine());
		HPy = d[0];
		ATKy = d[1];
		DEFy = d[2];

		d = readInts(in.readLine());
		HPm = d[0];
		ATKm = d[1];
		DEFm = d[2];

		d = readInts(in.readLine());
		priceH = d[0];
		priceA = d[1];
		priceD = d[2];
		for (int hp = 0; hp <= 1000; hp++) {
			for (int atk = 0; atk <= 1000; atk++) {
				for (int def = 0; def <= 1000; def++) {
					temp = hp * priceH + atk * priceA + def * priceD;
					if (temp > minPrice)
						break;
					if (temp < minPrice) {
						if (DEFy + def >= ATKm)
							hitM = Integer.MAX_VALUE;
						else
							hitM = (int) Math.ceil((HPy + hp) * 1.0
									/ (ATKm - (DEFy + def)));
						if (DEFm >= ATKy + atk)
							hitY = Integer.MAX_VALUE;
						else
							hitY = (int) Math.ceil(HPm * 1.0
									/ ((ATKy + atk) - DEFm));

						if (hitM > hitY) {
							minPrice = temp;
						}
					}
				}
			}
		}
		System.out.println(minPrice);

	}
}
