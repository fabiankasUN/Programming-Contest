package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CombatingCancer {

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
		ArrayList<Integer> arr;
		int index;

		public node ( int index ) {
			arr = new ArrayList<Integer>( );
			this.index = index;
		}

		@Override
		public int compareTo( node o ) {
			if ( index == 0 || o.index == 0 )
				return 0;
			if ( arr.size( ) < o.arr.size( ) ) {
				return -1;
			} else if ( arr.size( ) > o.arr.size( ) ) {
				return 1;
			}
			for ( int i = 0; i < arr.size( ); i++ ) {
				if ( arr.get( i ) != o.arr.get( i ) ) {
					if ( arr.get( i ) <= o.arr.get( i ) ) {
						return -1;
					} else if ( arr.get( i ) > o.arr.get( i ) ) {
						return 1;
					}
				}
			}
			return 0;
		}

	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		while ( (line = in.readLine( )) != null ) {
			int n = Integer.parseInt( line );
			ArrayList<Integer> GG1[] = new ArrayList[ n + 1 ];
			ArrayList<Integer> GG2[] = new ArrayList[ n + 1 ];
			node W1[] = new node[ n + 1 ];
			node W2[] = new node[ n + 1 ];
			for ( int i = 0; i < GG1.length; i++ ) {
				GG1[ i ] = new ArrayList<Integer>( );
				GG2[ i ] = new ArrayList<Integer>( );
				W1[ i ] = new node( i );
				W2[ i ] = new node( i );
			}
			for ( int i = 0; i < n - 1; i++ ) {
				int p[] = atoi( in.readLine( ) );
				GG1[ p[ 0 ] ].add( p[ 1 ] );
				GG1[ p[ 1 ] ].add( p[ 0 ] );
			}
			for ( int i = 0; i < n - 1; i++ ) {
				int p[] = atoi( in.readLine( ) );
				GG2[ p[ 0 ] ].add( p[ 1 ] );
				GG2[ p[ 1 ] ].add( p[ 0 ] );
			}

			for ( int i = 1; i < GG1.length; i++ ) {
				for ( int j =0; j < GG1[ i ].size( ); j++ ) {
					W1[ i ].arr.add( GG1[ GG1[ i ].get( j ) ].size( ) );
				}
			}
			for ( int i = 1; i < GG2.length; i++ ) {
				for ( int j = 0; j < GG2[ i ].size( ); j++ ) {
					W2[ i ].arr.add( GG2[ GG2[ i ].get( j ) ].size( ) );
				}
			}
			for ( int i = 1; i < W1.length; i++ ) {
				Collections.sort( W1[ i ].arr );
			}
			for ( int i = 1; i < W2.length; i++ ) {
				Collections.sort( W2[ i ].arr );
			}

			Arrays.sort( W1 );
			Arrays.sort( W2 );
			if ( W1.length != W2.length ) {
				System.out.println( "N" );
			} else {
				boolean x = false;
				for ( int i = 1; i < W1.length; i++ ) {
					if ( W1[ i ].arr.size( ) != W2[ i ].arr.size( ) ) {
						x = true;
						break;
					}

					for ( int j = 1; j < W1[ i ].arr.size( ); j++ ) {
						if ( W1[ i ].arr.get( j ) != W2[ i ].arr.get( j ) ) {
							x = true;
							break;
						}
					}
				}
				if ( x )
					System.out.println( "N" );
				else
					System.out.println( "S" );
			}

		}

	}
}
