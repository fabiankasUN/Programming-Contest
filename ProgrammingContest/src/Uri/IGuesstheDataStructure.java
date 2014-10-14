package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class IGuesstheDataStructure {

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

		int d1 = 0, d2 = 0, d3 = 0;
		boolean p, c, pc;
		int cont = 0;
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			Stack<Integer> pila = new Stack<Integer>( );
			Queue<Integer> cola = new LinkedList<Integer>( );
			PriorityQueue<Integer> priority = new PriorityQueue<Integer>( );
			int n = Integer.parseInt( line );
			p = pc = c = true;
			cont = 0;
			for ( int i = 0; i < n; i++ ) {
				int arr[] = atoi( in.readLine( ) );
				if ( arr[ 0 ] == 1 ) {
					pila.add( arr[ 1 ] );
					cola.add( arr[ 1 ] );
					priority.add( -arr[ 1 ] );
				} else {
					if ( pila.isEmpty( ) )
						p = false;
					else
						d1 = pila.pop( );
					if ( cola.isEmpty( ) )
						c = false;
					else
						d2 = cola.poll( );
					if ( priority.isEmpty( ) )
						pc = false;
					else
						d3 = priority.poll( );

					if ( d1 != arr[ 1 ] )
						p = false;
					if ( d2 != arr[ 1 ] )
						c = false;
					if ( d3 != -arr[ 1 ] )
						pc = false;
				}
			}
			if ( p )
				cont++;
			if ( c )
				cont++;
			if ( pc )
				cont++;
			if ( cont > 1 ) {
				out.append( "not sure\n" );
			} else if ( cont == 0 ) {
				out.append( "impossible" + "\n" );
			} else {
				if ( p )
					out.append( "stack\n" );
				if ( c )
					out.append( "queue\n" );
				if ( pc )
					out.append( "priority queue\n" );
			}
			out.append( "\n2" );

		}
		System.out.print( out );
	}

}
