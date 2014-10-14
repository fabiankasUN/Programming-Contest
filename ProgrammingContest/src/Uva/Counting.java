package Uva;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.Set;
 
public class Counting {
    public static int bfs(int s, int t, int[][] res, int[] parent,
            LinkedList<Integer>[] adlist) {
        Queue<Integer> Q = new LinkedList<Integer>();
        Q.add(s);
        Q.add(Integer.MAX_VALUE);
        while (!Q.isEmpty()) {
            int n = Q.poll();
            int flow = Q.poll();
            if (n == t)
                return flow;
            for (int j : adlist[n]) {
                if (res[n][j] > 0 && parent[j] == -1) {
                    Q.add(j);
                    Q.add(Math.min(flow, res[n][j]));
                    parent[j] = n;
                }
            }
        }
        return 0;
    }
 
    public static void augmentPath(int s, int t, int flow, int[] parent,
            int[][] res) {
        int cur = t;
        while (cur != s) {
            res[parent[cur]][cur] -= flow;
            res[cur][parent[cur]] += flow;
            cur = parent[cur];
        }
    }
 
    public static int maxFlow(int s, int t, int[][] res) {
        LinkedList<Integer>[] adlist = new LinkedList[res.length];
        for (int i = 0; i < adlist.length; i++)
            adlist[i] = new LinkedList<Integer>();
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                if (res[i][j] > 0) {
                    adlist[i].add(j);
                    if (res[j][i] == 0)
                        adlist[j].add(i);
                }
            }
        }
        int[] parent = new int[res.length];
        Arrays.fill(parent, -1);
        int flow, maxflow = 0;
        while ((flow = bfs(s, t, res, parent, adlist)) != 0) {
            maxflow += flow;
            augmentPath(s, t, flow, parent, res);
            Arrays.fill(parent, -1);
        }
        return maxflow;
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(in.readLine());
        in.readLine();// empty line
        while (tc-- > 0) {
            String s;
            LinkedList<String> input = new LinkedList<String>();
            HashMap<String, Integer> person = new HashMap<String, Integer>();
            HashMap<String, Integer> club = new HashMap<String, Integer>();
            HashMap<String, Integer> party = new HashMap<String, Integer>();
            int top = 1;
            while ((s = in.readLine()) != null && !s.equals("")
                    && !s.equals(" ")) {
                input.add(s);
                String[] arr = s.split(" ");
                person.put(arr[0], top++);
                if (arr.length > 1) {
                    if (!party.containsKey(arr[1]))
                        party.put(arr[1], top++);
                }
                for (int i = 2; i < arr.length; i++) {
                    if (!club.containsKey(arr[i]))
                        club.put(arr[i], top++);
                }
            }
            int[][] res = new int[top + 1][top + 1];
            for (String i : input) {
                String[] arr = i.split(" ");
                if (arr.length > 1) {
                    res[person.get(arr[0])][party.get(arr[1])] = 1;
                    for (int j = 2; j < arr.length; j++) {
                        res[club.get(arr[j])][person.get(arr[0])] = 1;
                    }
                }
            }
            int seats = (club.size() - 1) / 2;
            Set<Entry<String, Integer>> S = club.entrySet();
            for (Entry<String, Integer> E : S) {
                res[0][E.getValue()] = 1;
            }
            S = party.entrySet();
            for (Entry<String, Integer> E : S) {
                res[E.getValue()][top] = seats;
            }
            HashMap<Integer, String> mapBack = new HashMap<Integer, String>();
            S = person.entrySet();
            for (Entry<String, Integer> E : S) {
                mapBack.put(E.getValue(), E.getKey());
            }
            int flow = maxFlow(0, top, res);
            System.out.println(club.size());
            if (flow != club.size()) {
                System.out.println("Impossible.");
            } else {
                S = club.entrySet();
                for (Entry<String, Integer> E : S) {
                    int cclub = E.getValue();
                    for (int i = 0; i < top; i++) {
                        if (res[i][cclub] == 1) {
                            System.out.println(mapBack.get(i) + " "
                                    + E.getKey());
                        }
                    }
                }
            }
            if (tc != 0)
                System.out.println();
        }
    }
}