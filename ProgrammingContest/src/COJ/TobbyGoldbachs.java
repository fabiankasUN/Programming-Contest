package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TobbyGoldbachs {

	// split de array
	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );
		int n = Integer.parseInt( in.readLine( ) );
		boolean arr[] =new boolean[401];
		int dp[]=new int[401];
		arr[0]=true;
		arr[1]=true;
		arr[2]=false;
		for ( int i = 2; i < arr.length; i++ ) {
			for ( int j = i*i; j < arr.length; j+=i ) {
				arr[j] = true;
			}
		}
		arr[2]=false;
		for ( int i = 0; i < arr.length; i++ ) {
			for ( int j =i; j < arr.length;j++ ) {
				if(!arr[j] && !arr[i] && i+j<=400){
					dp[i+j]++;
				}
			}
		}
		
		for ( int i = 0; i < n; i++ ) {
			int m = Integer.parseInt( in.readLine( ) );
			out.append( dp[m]+"\n" );
		}
		
		System.out.print( out );
	}

}
