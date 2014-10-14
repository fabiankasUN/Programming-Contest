package Timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaximumSum {

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
		String line = in.readLine( );
		int t = Integer.parseInt( line );

		int arr[][] = new int[ t + 1 ][ t + 1 ];
		int dp[][] = new int[ t + 1 ][ t + 1 ];
		for ( int i = 0; i < t; i++ ) {
			int cad[] = atoi( in.readLine( ) );
			for ( int j = 0; j < cad.length; j++ ) {
				arr[ i + 1 ][ j + 1 ] = cad[ j ];
			}
		}

		for ( int i = 0; i < arr.length; i++ ) {
			arr[ 0 ][ i ] = 0;
			arr[ i ][ 0 ] = 0;
		}
		int max = Integer.MIN_VALUE;

		for ( int i = 1; i < arr.length; i++ ) {
			for ( int j = 1; j < arr.length; j++ ) {
				for ( int k = i; k < arr.length; k++ ) {
					for ( int l = j; l < arr.length; l++ ) {
						dp[ k ][ l ] = dp[ k - 1 ][ l ] + dp[ k ][ l - 1 ] - dp[ k - 1 ][ l - 1 ] + arr[ k ][ l ];
						if ( max < dp[ k ][ l ] ) {
							max = dp[ k ][ l ];
						}
					}
				}
			}
		}
		System.out.println( max );
	}
}
