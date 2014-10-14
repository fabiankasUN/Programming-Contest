package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TanningSalon {

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
		int r[] = new int[ 'Z' - 'A' + 1 ];
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0" ) )
				break;
			int n = parseo( line, 0 );
			char arr[] = line.split( " " )[ 1 ].toCharArray( );
			int cont = 0;
			int sum = 0;
			for ( int i = 0; i < arr.length; i++ ) {
				if ( r[ arr[ i ] - 'A' ] == 0 ) {
					if ( cont < n ) {
						r[ arr[ i ] - 'A' ]++;
						cont++;
					} else {
						r[ arr[ i ] - 'A' ] = 2;
						sum++;
					}
				} else {
					if ( r[ arr[ i ] - 'A' ] == 1 ) {
						cont--;
						r[ arr[ i ] - 'A' ]--;
					} else {
						r[ arr[ i ] - 'A' ] = 0;
					}

				}
			}
			if ( sum == 0 ) {
				out.append( "All customers tanned successfully.\n" );
			} else {
				out.append( sum + " customer(s) walked away.\n" );
			}

		}
		System.out.print( out );
	}

}
