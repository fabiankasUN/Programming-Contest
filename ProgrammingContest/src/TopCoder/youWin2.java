package TopCoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class youWin2 {
   
     static final int MAXN = 21;
    static final int[][] dp = new int[1 << MAXN][MAXN];
     static final int[][] rotation = new int[MAXN][MAXN];
   
    static int n;
    static char[] a;
  
    public static void main(String[] args) throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  
      String s;
      while (!"0".equals(s = in.readLine())) {
        n = s.length();
        a = s.toCharArray();
  
        for (int i = 0; i < n; i++)
         for (int j = 0; j <= i; j++)
            rotation[i][j] = rotation[j][i] = Math.min(Math.abs(a[i] - a[j]), 26 - Math.abs(a[i] - a[j]));
  
        for (int j = 0; j < 1 << n; j++)
          for (int i = 0; i < n; i++)
            dp[j][i] = 0;
        for (int i = 0; i < n; i++)
          dp[1 << i][i] = Math.min(a[i] - 'A', 26 - a[i] + 'A') + 1;
 
      int best = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
          best = Math.min(best, dp(i, (1 << n) - 1));
        System.out.println(best);
      }
    }
 
    static int dp(int cur, int mask) {
      if (dp[mask][cur] > 0)
        return dp[mask][cur];
      if (mask == 0)
        return 0;
  
      int count = 0;
      for (int i = 0; i <= cur; i++)
        if ((mask & (1 << i)) > 0)
          count++;
  
      int best = Integer.MAX_VALUE;
      int newmask = mask & ~(1 << cur);
      for (int i = 0, j = 0; i < n; i++)
        if ((mask & (1 << i)) > 0) {
          j++;
          if (i == cur)
            continue;
          int translation = j > count ? j - count : count - j - 1;
         best = Math.min(best, dp(i, newmask) + translation + rotation[i][cur] + 1);
         // best = Math.min(best, dp(i, newmask) + translate(newmask, i, cur) + rotation[i][cur] + 1);
      }
  
      return dp[mask][cur] = best;
    }
}