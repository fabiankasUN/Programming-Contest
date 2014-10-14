package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HanoiTowerTroublesAgain {

	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}
	public static int sol( int n ) {
		int arr[] = new int[ n ];
		int cont = 1;
		boolean cond = true;
		while ( cond ) {
			cond = false;
			for ( int i = 0; i < arr.length; i++ ) {
				if ( arr[ i ] == 0 ) {
					arr[ i ] = cont++;
					cond = true;
					break;
				} else {
					if ( Math.sqrt( (arr[ i ] + cont) ) == (int) Math.sqrt( (arr[ i ] + cont) ) ) {
						arr[ i ] = cont++;
						cond = true;
						break;
					}
				}
			}
		}

		return cont-1;
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		StringBuilder out = new StringBuilder( );
		int n = Integer.parseInt( in.readLine( ) );
		for ( int i = 0; i < n; i++ ) {
			int m = Integer.parseInt( in.readLine( ) );
			out.append( sol( m ) + "\n" );
		}

		System.out.print( out );
	}
}
