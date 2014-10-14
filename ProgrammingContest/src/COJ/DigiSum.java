package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DigiSum {

	// split de array
	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length - 1 ];
		for ( int i = 1; i < read.length; i++ ) {
			res[ i - 1 ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0" ) )
				break;
			
			int arr[] = atoi( line );
			Arrays.sort( arr );
			String a = "";
			String b = "";
			boolean visited[] = new boolean[ arr.length ];
			for ( int i = 0; i < visited.length; i++ ) {
				if ( arr[ i ] > 0 && !visited[ i ] ) {
					a += arr[ i ] + "";
					visited[ i ] = true;
					break;
				}
			}
			for ( int i = 0; i < visited.length; i++ ) {
				if ( arr[ i ] > 0 && !visited[ i ] ) {
					b += arr[ i ] + "";
					visited[ i ] = true;
					break;
				}
			}
			boolean k = false;
			for ( int i = 0; i < visited.length; i++ ) {
				if ( visited[ i ] == false ) {
					if ( !k ) {
						a += arr[ i ] + "";
					} else
						b += arr[ i ] + "";
					k = !k;
				}
			}

			out.append( (Integer.parseInt( a ) + Integer.parseInt( b )) + "\n" );
		}
		System.out.print( out );
	}

}
