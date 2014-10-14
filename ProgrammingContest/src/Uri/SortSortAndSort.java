package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SortSortAndSort {

	// split de array
	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}

	public static class node implements Comparable<node> {
		int value;
		int mod;

		public node ( int value, int mod ) {
			this.value = value;
			this.mod = mod;
		}

		@Override
		public int compareTo( node o ) {
			if ( mod > o.mod )
				return 1;
			else if ( mod < o.mod )
				return -1;
			else {
				if ( value % 2 != 0 && o.value % 2 == 0 ) {
					return -1;
				} else if ( value % 2 == 0 && o.value % 2 != 0 ) {
					return 1;

				} else if ( value % 2 != 0 && o.value % 2 != 0 ) {
					if ( value > o.value )
						return -1;
					else
						return 1;
				} else if ( value % 2 == 0 && o.value % 2 == 0 ) {
					if ( value > o.value )
						return 1;
					else
						return -1;
				}
			}
			return 0;
		}

	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0 0" ) ) {
				out.append( "0 0" ).append( "\n" );
				break;
			}
			int cad[] = atoi( line );
			node arr[] = new node[ cad[ 0 ] ];
			for ( int i = 0; i < cad[ 0 ]; i++ ) {
				int num = Integer.parseInt( in.readLine( ) );
				arr[ i ] = new node( num, num % cad[ 1 ] );
			}
			Arrays.sort( arr );
			out.append( cad[ 0 ] + " " + cad[ 1 ] ).append( "\n" );
			for ( int i = 0; i < arr.length; i++ ) {
				out.append( arr[ i ].value ).append( "\n" );
			}
		}
		System.out.print( out );
	}

}
