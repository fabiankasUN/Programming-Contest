package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Tobi2 {

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

	public static int dfs( int adj[][], boolean arr[], int ini ) {
		int cont = 0;
		Stack<Integer> pila = new Stack<Integer>( );
		pila.add( ini );
		while ( !pila.isEmpty( ) ) {
			int actual = pila.pop( );
			for ( int i = 0; i < adj.length; i++ ) {
				if ( adj[ actual ][ i ] == 1 ) {
					pila.add( i );
					if ( arr[ i ] ) {
						cont++;
					}
				}
			}
		}

		if ( arr[ ini ] )
			cont++;
		return cont;
	}

	public static void dfs2( int adj[][], boolean arr[], int ini ) {
		int cont = 0;
		Stack<Integer> pila = new Stack<Integer>( );
		pila.add( ini );
		while ( !pila.isEmpty( ) ) {
			int actual = pila.pop( );
			for ( int i = 0; i < adj.length; i++ ) {
				if ( adj[ actual ][ i ] == 1 ) {
					pila.add( i );
					arr[ i ] = !arr[ i ];
				}
			}
		}
		arr[ ini ] = !arr[ ini ];
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			int n = parseo( line, 0 );
			int m = parseo( line, 1 );
			boolean arr[] = new boolean[ n ];
			int adj[][] = new int[ n ][ n ];
			for ( int i = 0; i < n - 1; i++ ) {
				int c[] = atoi( in.readLine( ) );
				adj[ c[ 0 ] ][ c[ 1 ] ] = 1;
			}
			int w[];
			for ( int i = 0; i < m; i++ ) {
				w = atoi( in.readLine( ) );
				if ( w[ 0 ] == 0 ) {
					dfs2( adj, arr, w[ 1 ] );
				} else {
					out.append( dfs( adj, arr, w[ 1 ] ) + "\n" );
				}
			}
		}
		System.out.print( out );
	}

}
