package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TobiPromoska {

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
		String line = "";
		StringBuilder out = new StringBuilder( );
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			int n = parseo( line, 0 );
			int m = parseo( line, 1 );
			boolean arr[] = new boolean[ n ];
			int adj[][] = new int[ n ][ n ];
			for ( int i = 0; i < n - 1; i++ ) {
				int c[] = atoi( in.readLine( ) );
				adj[ c[ 0 ] ][ c[ 1 ] ] = 1;
			}
			int w[];
			for ( int i = 0; i < m; i++ ) {
				w = atoi( in.readLine( ) );
				if ( w[ 0 ] == 0 ) {
					for ( int j = 0; j < adj.length; j++ ) {
						if ( j != w[ 1 ] ) {
							if ( adj[ w[ 1 ] ][ j ] == 1 ) {
								arr[ j ] = !arr[ j ];
							}
						}
					}
					arr[ w[ 1 ] ] = !arr[ w[ 1 ] ];
				} else {
					int cont = 0;
					for ( int j = 0; j < adj.length; j++ ) {
						if ( j != w[ 1 ] ) {
							if ( adj[ w[ 1 ] ][ j ] == 1 && arr[ j ] ) {
								cont++;
							}
						}
					}
					if ( arr[ w[ 1 ] ] )
						cont++;
					out.append( cont + "\n" );
				}
			}
		}
		System.out.print( out );
	}

}
