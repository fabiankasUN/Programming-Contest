package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InBraille {
	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );
		char matriz[][] = {
				{ '.', '*', '*', '.', '*', '.', '*', '*', '*', '*', '*', '.', '*', '*', '*', '*', '*', '.', '.', '*' },
				{ '*', '*', '.', '.', '*', '.', '.', '.', '.', '*', '.', '*', '*', '.', '*', '*', '*', '*', '*', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };

		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0" ) )
				break;
			int num = Integer.parseInt( line );
			char l = in.readLine( ).charAt( 0 );
			if ( l == 'S' ) {
				char cad[] = in.readLine( ).toCharArray( );
				String a = "";
				String b = "";
				String c = "";
				for ( int i = 0; i < cad.length; i++ ) {
					a += matriz[ 0 ][ (cad[ i ] - '0') * 2 ];
					a += matriz[ 0 ][ ((cad[ i ] - '0') * 2) + 1 ];
					b += matriz[ 1 ][ (cad[ i ] - '0') * 2 ];
					b += matriz[ 1 ][ ((cad[ i ] - '0') * 2) + 1 ];
					c += matriz[ 2 ][ (cad[ i ] - '0') * 2 ];
					c += matriz[ 2 ][ ((cad[ i ] - '0') * 2) + 1 ];
					if ( i != cad.length - 1 ) {
						a += " ";
						b += " ";
						c += " ";
					}
				}
				System.out.println( a );
				System.out.println( b );
				System.out.println( c );

			} else {
				char cad[] = in.readLine( ).toCharArray( );
				char cad2[] = in.readLine( ).toCharArray( );
				char cad3[] = in.readLine( ).toCharArray( );
				int k = 0;
				String res = "";
				for ( int i = 0; i < num; i++ ) {
					for ( int j = 0; j < matriz[ 0 ].length; j += 2 ) {
						if ( matriz[ 0 ][ j ] == cad[ k ] && matriz[ 0 ][ j + 1 ] == cad[ k + 1 ] && matriz[ 1 ][ j ] == cad2[ k ]
								&& matriz[ 1 ][ j + 1 ] == cad2[ k + 1 ] && matriz[ 2 ][ j ] == cad3[ k ]
								&& matriz[ 2 ][ j + 1 ] == cad3[ k + 1 ] ) {
							res += ((j + 1) / 2) + "";
							break;
						}

					}
					k += 3;
				}
				System.out.println( res );
			}

		}
	}
}
