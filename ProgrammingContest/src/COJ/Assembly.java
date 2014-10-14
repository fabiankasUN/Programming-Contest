package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Assembly {

	// split de array
	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}

	public static int getLIS( int[ ] a ) {
		int n = a.length;
		int[ ] tail = new int[ n ];
		int[ ] prev = new int[ n ];

		int len = 0;
		for ( int i = 0; i < n; i++ ) {
			int pos = lower_bound( a, tail, len, a[ i ] );
			if ( pos == len ) {
				++len;
			}
			prev[ i ] = pos > 0 ? tail[ pos - 1 ] : -1;
			tail[ pos ] = i;
		}

		int[ ] res = new int[ len ];
		int r = len;
		for ( int i = tail[ len - 1 ]; i >= 0; i = prev[ i ] ) {
			res[ --len ] = a[ i ];
		}
		return r;
	}

	static int lower_bound( int[ ] a, int[ ] tail, int len, int key ) {
		int lo = -1;
		int hi = len;
		while ( hi - lo > 1 ) {
			int mid = (lo + hi) >>> 1;
			if ( a[ tail[ mid ] ] < key ) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		return hi;
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			int n = Integer.parseInt( line );
			int arr[] = atoi( in.readLine( ) );
			int a = getLIS( arr );
			for ( int i = 0; i < arr.length; i++ ) {
				arr[ i ] *= -1;
			}
			int b = getLIS( arr );
			if ( a == b ) {
				out.append( "Caution. I will not intervene.\n" );
			} else {
				out.append( "Don't worry. I must intervene.\n" );
			}
		}
		System.out.print( out );
	}

}
