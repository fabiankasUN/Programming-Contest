package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Recycling {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}
	

	public static class Date implements Comparable<Date> {
		char bean;
		char w;

		public Date(char bean, char w) {
			this.bean = bean;
			this.w = w;
		}

		@Override
		public int compareTo(Date o) {
			return w - o.w;
		}

	}

	public static class City {
		Date dates[];

		public City(Date[] dates) {
			this.dates = dates;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null && line.length() != 0) {
			if (line.equals("#")) {
				break;
			}
			ArrayList<City> list = new ArrayList<City>();
			Date dates[] = new Date[5];
			do {
				dates = new Date[5];
				String l[] = line.split(",");
				for (int i = 0; i < l.length; i++) {
					dates[i] = new Date(l[i].charAt(0), l[i].charAt(2));
					
				}
				Arrays.sort(dates);
				list.add(new City(dates));
				line = in.readLine();
			} while ((line).charAt(0) != 'e' && !line.equals("#"));

			int min = Integer.MAX_VALUE;
			int index = -1;
			for (int i = 0; i < list.size(); i++) {
				int cont = 0;
				for (int j = 0; j < list.size(); j++) {
					if (i != j) {
						for (int k = 0; k < list.get(i).dates.length; k++) {
							if (list.get(i).dates[k].bean != list.get(j).dates[k].bean) {
								cont++;
							}
						}
					}
				}

				if (cont < min) {
					min = cont;
					index = i + 1;
				}
			}
			out.append(index+"\n");

			if (line.equals("#"))
				break;
		}
		System.out.print(out);
	}
	
}