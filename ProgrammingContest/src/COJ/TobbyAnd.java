package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TobbyAnd {

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

		int arr[] = new int[ 1000001 ];
		arr[ 1 ] = -1;
		for ( int i = 2; i < arr.length; i++ ) {
			for ( int j = i; j < arr.length; j += i ) {
				arr[ j ]++;
			}

		}
		int dp[] = new int[ 1000001 ];
		
		
		for ( int i = 0; i < n; i++ ) {
			int m = Integer.parseInt( in.readLine( ) );
			int max = 0;
			int index = 1;
			if ( dp[ m ] == 0 ) {
				for ( int j = 1; j <= m; j++ ) {
					if ( arr[ j ] > max ) {
						max = arr[ j ];
						index = j;
					}
				}
				dp[ m ] = index;
			} else {
				index = dp[ m ];
			}
			out.append( index + "\n" );
		}

		System.out.print( out );
	}

}
