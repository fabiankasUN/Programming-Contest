package Uri;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class LazyJumpingFrog {

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

	public static class nodo implements Comparable<nodo> {
		Point vecino;
		int peso;// etiqueta del peso

		public nodo ( Point x, int peso ) {// constructor
			this.vecino = x;
			this.peso = peso;

		}

		@Override
		public int compareTo( nodo o ) {// comparable para la priority queue
			if ( o.peso > this.peso )
				return -1;
			else if ( o.peso < this.peso )
				return 1;
			return 0;
		}

		public String toString( ) {
			return "vecino " + vecino + " peso " + peso;
		}

	}

	public static int distancia[][];// lleva las distancias minimas a cada nodo
	public static boolean visitado[][];// lleva los visitados, para no
	public static PriorityQueue<nodo> Q;// cola de prioridad
	public static int V;// num vertices
	public static int vecinos[][] = { { 7, 6, 5, 6, 7 }, { 6, 3, 2, 3, 6 }, { 5, 2, 0, 2, 5 }, { 6, 3, 2, 3, 6 },{ 7, 6, 5, 6, 7 } };
	public static int vx[][] = { { -2, -2, -2, -2, -2 }, { -1, -1, -1, -1, -1 }, { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1 },{ 2, 2, 2, 2, 2 } };
	public static int vy[][] = { { -2, -1, 0, 1, 2 }, { -2, -1, 0, 1, 2 }, { -2, -1, 0, 1, 2 }, { -2, -1, 0, 1, 2 },{ -2, -1, 0, 1, 2 } };

	public static void inint( ) {
		Q = new PriorityQueue<nodo>( );
		distancia = new int[ n + 1 ][ m + 1 ];
		
		for ( int i = 0; i < n + 1; i++ ) {
			for ( int j = 0; j < m + 1; j++ ) {
				distancia[ i ][ j ] = Integer.MAX_VALUE;
			}
		}
	}

	public static boolean es( int x, int y ) {
		if ( x < 1 || y < 1 || x > n || y > m ) {
			return true;
		}
		if ( visitado[ x ][ y ] ) {
			return true;
		}
		return false;
	}

	public static void dijkstra( nodo inicial ) {
		inint( );
		Q.add( inicial );
		distancia[ inicial.vecino.x ][ inicial.vecino.y ] = 0;

		while ( !Q.isEmpty( ) ) {
			nodo ac = Q.poll( );
			if ( es( ac.vecino.x, ac.vecino.y ) )
				continue;// para que no revisite vertices
			
			visitado[ ac.vecino.x ][ ac.vecino.y ] = true;
			for ( int i = 0; i < vecinos.length; i++ ) {
				for ( int j = 0; j < vecinos[ 0 ].length; j++ ) {
					if ( !es( ac.vecino.x + vx[ i ][ j ], ac.vecino.y + vy[ i ][ j ] ) ) {
						minPeso( ac.vecino, new Point( ac.vecino.x + vx[ i ][ j ], ac.vecino.y + vy[ i ][ j ] ), vecinos[ i ][ j ] );
					}
				}
			}
		}
	}

	public static void minPeso( Point actual, Point adyacente, int peso ) {
		if ( distancia[ actual.x ][ actual.y ] + peso < distancia[ adyacente.x ][ adyacente.y ] ) {
			distancia[ adyacente.x ][ adyacente.y ] = distancia[ actual.x ][ actual.y ] + peso; // cambiamos
			Q.add( new nodo( adyacente, distancia[ adyacente.x ][ adyacente.y ] ) );//
		}
	}

	public static int grafo[][];
	public static ArrayList<Point> lagunas[];
	public static int n, m;
	public static int xFrog;
	public static int yFrog;
	public static int xToad;
	public static int yToad;

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		while ( (line = in.readLine( )) != null ) {
			if ( line.equals( "0 0" ) )
				break;
			n = parseo( line, 0 );
			m = parseo( line, 1 );
			int cad[] = atoi( in.readLine( ) );
			xFrog = cad[ 0 ];
			yFrog = cad[ 1 ];
			xToad = cad[ 2 ];
			yToad = cad[ 3 ];
			int k = Integer.parseInt( in.readLine( ) );

			grafo = new int[ n + 1 ][ m + 1 ];
			lagunas = new ArrayList[ n + 1 ];
			visitado = new boolean[ n + 1 ][ m + 1 ];
			
			for ( int i = 0; i < k; i++ ) {
				int c[] = atoi( in.readLine( ) );
				for ( int p = c[ 0 ]; p <= c[ 2 ]; p++ ) {
					for ( int w = c[ 1 ]; w <= c[ 3 ]; w++ ) {
						visitado[ p ][ w ] = true;
					}
				}
			}

			dijkstra( new nodo( new Point( xFrog, yFrog ), 0 ) );
			if ( distancia[ xToad ][ yToad ] == Integer.MAX_VALUE ) {
				System.out.println( "impossible" );
			} else {
				System.out.println( distancia[ xToad ][ yToad ] );
			}

		}

		/*
		 * for ( int i = 0; i < distancia.length; i++ ) { for ( int j = 0; j <
		 * distancia[0].length; j++ ) { System.out.print( distancia[ i ][ j ] +
		 * " " ); } System.out.println( ); }
		 */
		// System.out.println( distancia[ xToad ][ yToad ] );
	}
}
