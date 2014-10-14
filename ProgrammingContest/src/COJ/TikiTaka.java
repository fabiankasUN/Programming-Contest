package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TikiTaka {

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
		int n = Integer.parseInt( in.readLine( ) );
		for ( int i = 0; i < n; i++ ) {
			if ( i != n - 1 ) {
				in.readLine( );
			}
			line = in.readLine( );
			int a = parseo( line, 0 );
			int b = parseo( line, 1 );
			for ( int j = 0; j < b; j++ ) {
				
			}

		}

		System.out.print( out );
	}

}
