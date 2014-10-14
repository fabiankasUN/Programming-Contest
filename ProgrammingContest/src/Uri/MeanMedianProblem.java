package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MeanMedianProblem {

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
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0 0" ) )
				break;
			int arr[] = atoi( line );
			Arrays.sort( arr );
			int dif = arr[ 1 ] - arr[ 0 ];
			out.append( arr[ 0 ] - dif ).append( "\n" );
		}
		System.out.print( out );
	}

}
