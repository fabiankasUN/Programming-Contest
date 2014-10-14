package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TobbySequence {

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

			long min = 1;
			long max = (long) (Math.pow( 10, 9 ) - 1);
			int n = Integer.parseInt( in.readLine( ) );
			boolean x = true;
			
			for ( int i = 0; i < n; i++ ) {
				if ( x )
					out.append( max-- + " " );
				else
					out.append( min++ + " " );
				x = !x;
			}
			out.deleteCharAt( out.length( ) - 1 );
			out.append( "\n" );
		System.out.print( out );
	}

}
