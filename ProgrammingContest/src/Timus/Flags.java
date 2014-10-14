package Timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Flags {

	// split de array
	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}

	public static int tam;

	public static int Fase( int i, int ant, int ant2 ) {
		if ( i == tam ) {
			return 1;
		}
		int max = 0;
		for ( int j = 0; j < 3; j++ ) {
			if ( ant == 2 && j != ant2 && j != 2 ) {
				max += Fase( i + 1, j, ant );
			}
			if ( ant != 2 && j != ant ) {
				max += Fase( i + 1, j, ant );
			}
			if ( ant != 2 && i + 1 != tam ) {
				max += Fase( i + 1, 2, ant );
			}
		}
		return max;
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = in.readLine( );
		tam = Integer.parseInt( line );
		long arr[] = new long[ 46 ];
		arr[ 0 ] = 0;
		arr[ 1 ] = 2;
		arr[ 2 ] = 2;
		for ( int i = 2; i < arr.length; i++ ) {
			arr[ i ] = arr[ i - 1 ] + arr[ i - 2 ];
		}
		System.out.println( arr[ tam ] );

		// f , ant, ant2

	}
}
