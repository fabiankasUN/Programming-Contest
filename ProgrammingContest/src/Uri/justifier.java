package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class justifier {

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
		String c = "";
		for ( int i = 0; i < 100; i++ ) {
			c += " ";
		}
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0" ) )
				break;
			if ( k > 0 )
				out.append( "\n" );
			k++;

			String cad[] = new String[ Integer.parseInt( line ) ];
			int max = -1;
			for ( int i = 0; i < cad.length; i++ ) {
				cad[ i ] = in.readLine( );
				max = Math.max( cad[ i ].length( ), max );
			}
			for ( int i = 0; i < cad.length; i++ ) {
				out.append( c.substring( 0, max - cad[ i ].length( ) ) + cad[ i ] ).append( "\n" );
			}

		}
		System.out.print( out );
	}
}
