package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GrandpaRubik {

	public static int movs[][][] = {
			{ { 9, 11 }, { 10, 23 }, { 11, 35 }, { 23, 34 }, { 35, 33 }, { 34, 21 }, { 33, 9 }, { 21, 10 }, { 22, 22 },
					{ 0, 12 }, { 3, 24 }, { 6, 36 }, { 12, 45 }, { 24, 48 }, { 36, 51 }, { 45, 44 }, { 48, 32 }, { 51, 20 },
					{ 44, 0 }, { 32, 3 }, { 20, 6 } },
			{ { 12, 14 }, { 13, 26 }, { 14, 38 }, { 26, 37 }, { 38, 36 }, { 37, 24 }, { 36, 12 }, { 24, 13 }, { 25, 25 },
					{ 6, 15 }, { 7, 27 }, { 8, 39 }, { 15, 47 }, { 27, 46 }, { 39, 45 }, { 47, 35 }, { 46, 23 }, { 45, 11 },
					{ 35, 6 }, { 23, 7 }, { 11, 8 } },
			{ { 15, 17 }, { 16, 29 }, { 17, 41 }, { 29, 40 }, { 41, 39 }, { 40, 27 }, { 39, 15 }, { 27, 16 }, { 28, 28 },
					{ 8, 18 }, { 5, 30 }, { 2, 42 }, { 18, 53 }, { 30, 50 }, { 42, 47 }, { 53, 38 }, { 50, 26 }, { 47, 14 },
					{ 38, 8 }, { 26, 5 }, { 14, 2 } },
			{ { 18, 20 }, { 19, 32 }, { 20, 44 }, { 32, 43 }, { 44, 42 }, { 43, 30 }, { 42, 18 }, { 30, 19 }, { 31, 31 },
					{ 2, 9 }, { 1, 21 }, { 0, 33 }, { 9, 51 }, { 21, 52 }, { 33, 53 }, { 51, 41 }, { 52, 29 }, { 53, 17 },
					{ 41, 2 }, { 29, 1 }, { 17, 0 } },
			{ { 0, 2 }, { 1, 5 }, { 2, 8 }, { 5, 7 }, { 8, 6 }, { 7, 3 }, { 6, 0 }, { 3, 1 }, { 4, 4 }, { 20, 17 }, { 19, 16 },
					{ 18, 15 }, { 17, 14 }, { 16, 13 }, { 15, 12 }, { 14, 11 }, { 13, 10 }, { 12, 9 }, { 11, 20 }, { 10, 19 },
					{ 9, 18 } },
			{ { 45, 47 }, { 46, 50 }, { 47, 53 }, { 50, 52 }, { 53, 51 }, { 52, 48 }, { 51, 45 }, { 48, 46 }, { 49, 49 },
					{ 36, 39 }, { 37, 40 }, { 38, 41 }, { 39, 42 }, { 40, 43 }, { 41, 44 }, { 42, 33 }, { 43, 34 }, { 44, 35 },
					{ 33, 36 }, { 34, 37 }, { 35, 38 } } };

	public static void rotate( char cube[], int mov, int s ) {
		char temp[] = Arrays.copyOf( cube, 55 );
		int r = Math.abs( 1 - s );
		for ( int i = 0; i < movs[ mov ].length; i++ ) {
			cube[ movs[ mov ][ i ][ r ] ] = temp[ movs[ mov ][ i ][ s ] ];
		}
	}

	public static char cube[];

	public static boolean win( ) {
		char pivote = cube[ 0 ];
		boolean win = true;
		for ( int i = 1; i < 9; i++ ) {
			if ( cube[ i ] != pivote ) {
				return false;
			}
		}
		for ( int i = 9; i < 32; i++ ) {
			if ( cube[ i ] != cube[ i + 12 ] ) {
				return false;
			}

		}

		return true;
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );

		int n = Integer.parseInt( in.readLine( ) );

		for ( int p = 0; p < n; p++ ) {
			int index = 0;
			cube = new char[ 54 ];
			for ( int i = 0; i < 9; i++ ) {
				line = in.readLine( );
				char arr[] = line.toCharArray( );
				for ( int j = 0; j < arr.length; j++ ) {
					if ( arr[ j ] != ' ' )
						cube[ index++ ] = arr[ j ];
				}
			}

			String cad[] = in.readLine( ).split( " " );
			for ( int i = 0; i < cad.length; i++ ) {
				if ( cad[ i ].equals( "0" ) )
					break;
				if ( Integer.parseInt( cad[ i ] ) > 0 ) {
					rotate( cube, Integer.parseInt( cad[ i ] ) - 1, 0 );
				} else {
					rotate( cube, (Integer.parseInt( cad[ i ] ) * -1) - 1, 1 );
				}
			}

			if ( win( ) ) {
				System.out.println( "Yes, grandpa!" );
			} else {
				System.out.println( "No, you are wrong!" );
			}

		}

	}
}
