package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TobyTank {

	// split de array
	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}

	public static int[ ] atoi2( String cad, int dp[] ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
			if ( i > 0 )
				dp[ i ] = dp[ i - 1 ] + res[ i ];
			else
				dp[ 0 ] = 1;
		}
		return res;
	}

	public static int parseo( String cad, int index ) {
		return Integer.parseInt( cad.split( " " )[ index ] );
	}

	public static int upper_bound( int[ ] arr, int key ) {
		int lower = 0, upper = arr.length - 1, ans = -1;
		while ( lower <= upper ) {
			int mid = (lower + upper) / 2;
			if ( key >= arr[ mid ] )
				lower = mid + 1;
			else {
				upper = mid - 1;
				ans = mid;
			}
		}
		if ( ans == -1 )
			return arr.length;
		return ans;
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line ="";
		StringBuilder out = new StringBuilder( );
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			int q = parseo( line, 1 );
			int n = parseo( line, 0 );
			int dp[] = new int[ n ];
			int arr[] = atoi2( in.readLine( ), dp );
			int qr[] = atoi( in.readLine( ) );
			
			
			for ( int i = 0; i < qr.length; i++ ) {
				out.append( upper_bound( dp, qr[ i ] ) + " " );
			}
			out.deleteCharAt( out.length( ) - 1 );
			out.append( "\n" );
			
		}
		System.out.print( out );

	}

}
