package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class TobyPRimox {

	public static Point root;

	public static class Point implements Comparable<Point> {

		int x;
		int y;

		public Point ( int x, int y ) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo( Point p ) {
			if ( x != p.x ) {
				return x < p.x ? -1 : x == p.x ? 0 : 1;
			}
			return y < p.y ? -1 : y == p.y ? 0 : 1;
		}
	}


	// split de array
	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}

	public static int parseo( String cad, int index ) {
		return Integer.parseInt( cad.split( " " )[ index ] );
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			int n = parseo( line, 0 );
			Point p[] = new Point[ n ];
			for ( int i = 0; i < p.length; i++ ) {
				line = in.readLine( );
				p[ i ] = new Point( parseo( line, 0 ), parseo( line, 1 ) );
			}
			Arrays.sort( p );
			out.append( n + "\n" );
			for ( int i = 0; i < p.length; i++ ) {
				out.append( p[ i ].x + " " + p[ i ].y + "\n" );

			}
		}
		System.out.print( out );
	}

}
