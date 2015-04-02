package codeforces;

import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class CCCCC {
public static void main(String[] args) throws IOException {
    BufferedReader in;
    StringBuilder out = new StringBuilder();
    File file = new File("entrada");
    if (file.exists())
        in = new BufferedReader(new FileReader(file));
    else
        in = new BufferedReader(new InputStreamReader(System.in));
    String line, lines[];
    int n = Integer.parseInt(in.readLine());
    int arrA[] = new int[n];
    int arrX[] = new int[n];
    HashSet<Integer> list = new HashSet<Integer>();
    int edge = 0;
    for ( int i = 0; i< n; i++ )
    {
        lines = in.readLine().split(" ");
        arrA[i] = Integer.parseInt(lines[0]);
        arrX[i] = Integer.parseInt(lines[1]);
        if ( arrA[i] == 1 )
        {
            list.add(i);
        }
        edge += arrA[i];
    }
    int v, t;
    edge /= 2;
    out.append(edge+"\n");
    for(int i : list  )
    {
        int x = i;
        while(arrA[x] == 1) {
            out.append(x + " " + arrX[x]+"\n");
            int y = arrX[x];
            arrA[y]--;
            arrX[y]^=x;
            x=y;
        }
    }
    System.out.print(out);
}
}