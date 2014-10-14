package Timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Metro {

	// split de array
	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}

	public static int parseo( String cad, int index ) {
		return Integer.parseInt( cad.split( " " )[ index ] );
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = in.readLine( );
		int arr[] = atoi( line );
		int k = Integer.parseInt( in.readLine( ) );
		double raiz = Math.sqrt( 20000 );
		double dp[][] = new double[ arr[ 1 ] + 1 ][ arr[ 0 ] + 1 ];
		boolean p[][] = new boolean[ arr[ 1 ] + 1 ][ arr[ 0 ] + 1 ];
		for ( int i = 0; i < k; i++ ) {
			int c[] = atoi( in.readLine( ) );
			p[ c[ 1 ] ][ c[ 0 ] ] = true;
		}

		dp[ 0 ][ 0 ] = 0;

		for ( int i = dp.length - 1; i >= 0; i-- ) {
			dp[ i ][ 0 ] = (dp.length - i - 1) * 100;
		}
		for ( int i = 0; i < dp[ 0 ].length; i++ ) {
			dp[ dp.length - 1 ][ i ] = i * 100;
		}
		
		for ( int i = dp.length - 2; i >= 0; i-- ) {
			for ( int j = 1; j < dp[ 0 ].length; j++ ) {
				dp[ i ][ j ] = Math.min( 100 + dp[ i ][ j - 1 ], 100 + dp[ i + 1 ][ j ] );
				if ( p[ dp.length - i - 1 ][ j ] == true ) {
					dp[ i ][ j ] = Math.min( dp[ i ][ j ], raiz + dp[ i + 1 ][ j - 1 ] );
				}
			}
		}
		System.out.println( Math.round( dp[ 0 ][ dp[ 0 ].length - 1 ] ) );

	}
}
