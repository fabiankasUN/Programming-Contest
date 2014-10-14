package Uva;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Skyline {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static class Node implements Comparable<Node> {
		int l;
		int r;
		int h;

		public Node(int l, int r, int h) {
			super();
			this.l = l;
			this.r = r;
			this.h = h;
		}

		@Override
		public int compareTo(Node o) {
			if (l < o.l)
				return -1;
			else if (l > o.l)
				return 1;
			else if (h > o.h)
				return -1;
			else if (h < o.h)
				return 1;

			return 0;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		ArrayList<Node> lista = new ArrayList<Node>();
		Point maxY=new Point(-1,-1);
		while ((line = in.readLine()) != null && line.length() != 0) {
			String linex[] = line.split(" ");
			lista.add(new Node(Integer.parseInt(linex[0]), Integer
					.parseInt(linex[2]), Integer.parseInt(linex[1])));
			if(lista.get(lista.size()-1).r>maxY.y)
				maxY=new Point(lista.get(lista.size()-1).r,lista.get(lista.size()-1).r);
		}
		Collections.sort(lista);
		int x = 0;
		int y = 0;
		int fin = Integer.MAX_VALUE;
		int x2 = 0;
		int y2 = 0;
		int fin2 = 0;
		
		int temp=0;
		boolean k = false;
		boolean b= false;
		boolean c=false;
		boolean d=false;
		while(!d){
		//for (int i = 0; i < lista.size(); i++) {
			k=false;
			b=false;
			c=false;
			for (int j = 0; j < lista.size(); j++) {
				if (lista.get(j).l > x && lista.get(j).h > y && lista.get(j).l < fin) {
					out.append(lista.get(j).l + " " + lista.get(j).h + " ");
					x = lista.get(j).l;
					y = lista.get(j).h;
					fin = lista.get(j).r;
					k=true;
					break;
				} else if (lista.get(j).r > fin && lista.get(j).l<fin && (!b || (b && lista.get(j).h>y2))) {
					//
					x2 = fin;
					y2 = lista.get(j).h;
					fin2 = lista.get(j).r;
					temp=j;
					b=true;
				} else if (lista.get(j).l > fin) {
					if(!b){
						x2 = fin;
						y2 = 0;
						fin2 = lista.get(j).r;
						temp=j;
						c=true;
						break;
					}
				}
			}
			if(!k && b){
				out.append(fin + " " + lista.get(temp).h + " ");
				x = fin;
				y = lista.get(temp).h;
				fin = lista.get(temp).r;
			}else if(!k && !b && c){
				out.append(fin + " " + 0 + " ");
				x = fin;
				y = 0;
				fin = Integer.MAX_VALUE;
			}else if(!k && !b && !c){
				out.append(maxY.x + " " + 0);
				d=true;
			}
		}
		
		
		
		System.out.println(out);
	}
}
