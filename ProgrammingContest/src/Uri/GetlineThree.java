package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetlineThree {

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
		int k = 0;
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( k > 0 )
				out.append( "\n" );

			int num = Integer.parseInt( line );
			String cad[] = in.readLine( ).split( " " );
			out.append( "Caso " + ++k + ":" ).append( "\n" );
			int f = 0, m = 0;
			for ( int i = 0; i < cad.length; i += 2 ) {
				if ( Integer.parseInt( cad[ i ] ) == num ) {
					if ( cad[ i + 1 ].trim( ).equals( "F" ) ) {
						f++;
					} else {
						m++;
					}
				}
			}
			out.append( "Pares Iguais: " + (f + m) ).append( "\n" );
			out.append( "F: " + f ).append( "\n" );
			out.append( "M: " + m ).append( "\n" );
		}
		System.out.print( out );
	}
}
