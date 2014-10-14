package Timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HowMany0s {

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
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			int t[]=atoi( line );
			System.out.println(rec(t[0]));
		}
		System.out.print( out );
	}
	
	public static  int  rec(int n){
		if(n==1){
			return 	2;
		}else if(n==0){
			return 0;
			
		}else{
			return rec(n/10) * 10;
		}
		
		
	}

}
