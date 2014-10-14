package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SquareFormations {

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
		int arr[] = new int[ 2000 ];
		for ( int i = 0; i < 2000; i++ ) {
			arr[ i ] = i * i;
		}

		int times = 1;
		int n = Integer.parseInt( in.readLine( ) );
		for ( int jj = 0; jj < n; jj++ ) {

			int k = Integer.parseInt( in.readLine( ) );
			int num = 0;
			double r;
			for ( int i = 1; i < arr.length; i++ ) {
				num = arr[ i ] - k;
				if ( num > 0 && num%2==0) {
					r = Math.sqrt( (num/2) + k );
					if ( Math.ceil( r ) == r ) {
						out.append( "Case " + times++ + ": " + num + "\n" );
						break;
					}
				}
			}
		}
		System.out.print( out );
	}

}
