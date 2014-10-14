package Timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KbasedNumer {

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
		int num = Integer.parseInt( line );
		int p = Integer.parseInt( in.readLine( ) );

		int ceros[][] = new int[ 11 ][ 17 ];
		int bases[][] = new int[ 11 ][ 17 ];
		for ( int i = 1; i < 11; i++ ) {
			bases[ i ][ 1 ] = i - 1;
			ceros[ i ][ 1 ] = 0;
		}

		for ( int i = 2; i < 11; i++ ) {
			for ( int j = 2; j < 17; j++ ) {
				ceros[ i ][ j ] = bases[ i ][ j - 1 ] - ceros[ i ][ j - 1 ];
				bases[ i ][ j ] = bases[ i ][ j - 1 ] * i - ceros[ i ][ j - 1 ];
			}
		}

		System.out.println( bases[ p ][ num ] );
	}
}
