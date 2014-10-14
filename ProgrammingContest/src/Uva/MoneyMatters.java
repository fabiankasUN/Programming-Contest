package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MoneyMatters {

	// split de array
	public static int[] atoi(String cad) {
		String read[] = cad.split(" ");
		int res[] = new int[read.length];
		for (int i = 0; i < read.length; i++) {
			res[i] = Integer.parseInt(read[i]);
		}
		return res;
	}

	public static ArrayList<Integer> adj[];

	public static int dfs(int j, int w[], boolean visited[]) {
		int cont = 0;

		Queue<Integer> cola = new LinkedList<Integer>();
		cola.add(j);
		visited[j] = true;
		int vecino;
		cont += w[j];
		while (!cola.isEmpty()) {
			int actual = cola.poll();
			for (int i = 0; i < adj[actual].size(); i++) {
				vecino = adj[actual].get(i);
				if (visited[vecino] == false) {
					visited[vecino] = true;
					cont += w[vecino];
					cola.add(vecino);
				}
			}

		}

		return cont;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder out = new StringBuilder();
		int n = Integer.parseInt(in.readLine());
		int w[] = new int[10001];
		boolean visited[] = new boolean[10001];
		adj=new ArrayList[10001];
		
		for (int i = 0; i < n; i++) {
			int date[] = atoi(in.readLine());
			int m = date[1];
			int p =date[0];
			for (int j = 0; j < date[0]; j++) {
				visited[j] = false;
				w[j] = Integer.parseInt(in.readLine());
				adj[j] = new ArrayList<Integer>();
			}

			for (int j = 0; j < m; j++) {
				date = atoi(in.readLine());
				adj[date[0]].add(date[1]);
				adj[date[1]].add(date[0]);
			}
			int total=0;
			boolean cond=true;
			for (int j = 0; j < p; j++) {
				if (visited[j] == false) {
					total=dfs(j,w,visited);
					if(total>0){
						cond=false;
						break;
					}
				}
			}
			if(cond){
				out.append("POSSIBLE\n");
			}else{
				out.append("IMPOSSIBLE\n");
			}

		}
		System.out.print(out);
	}
}
