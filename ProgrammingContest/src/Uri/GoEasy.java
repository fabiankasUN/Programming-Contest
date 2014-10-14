package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class GoEasy {
	public static class nodo implements Comparable<nodo> {
		int vecino;// etiqueta del nodo
		int peso;// etiqueta del peso
		int bus;

		public nodo ( int x, int peso, int bus ) {// constructor
			this.vecino = x;
			this.peso = peso;
			this.bus = bus;

		}

		/**
		 * este metodo sirve para poder usar la priority queue con nodos y que
		 * estos se ordenen del menor al mayor
		 */
		@Override
		public int compareTo( nodo o ) {// comparable para la priority queue
			if ( o.peso > this.peso )
				return -1;
			else if ( o.peso < this.peso )
				return 1;
			return 0;
		}

		public String toString( ) {
			return vecino + " " + peso+ " "+bus;
		}

	}

	// variables
	/**
	 * esta variable, guarda todos los vertices y enlaces del grafo cada vertice
	 * tiene su lista de adyacentes para saber hacia donde se puede mover, cada
	 * nodo tiene peso para saber caminos cortos con el algoritmo de djstra
	 */
	public static ArrayList<nodo> adj[];// grafo representado como listas
										// enlazadas de
	// nodos

	public static int distancia[];// lleva las distancias minimas a cada nodo
	public static boolean visitado[];// lleva los visitados, para no repetirlos
	public static int previo[];// caminos pra poder devolverse y conocer el
								// camino
	public static PriorityQueue<nodo> Q;// cola de prioridad
	public static int V;// num vertices

	public static void inint( ) {// inicializacion de vertices
		Q = new PriorityQueue<nodo>( );// cola de prioridad
		adj = new ArrayList[ V + 1 ];// listas de adyacencia del tamaño de
										// numero de
										// vertices
		distancia = new int[ V + 1 ];// lleva las distancias minimas a cada nodo
		visitado = new boolean[ V + 1 ];// lleva si un nodo es visitado o no
		previo = new int[ V+1 ];// sirve para volver de cualquier nodo al inicio

		for ( int i = 0; i < V + 1; i++ ) {
			distancia[ i ] = Integer.MAX_VALUE; // inicializamos todas las
												// distancias con
			// valor de infinito para poder comparar
			adj[i]=new ArrayList<nodo>( );
			visitado[ i ] = false; // inicializamos todos los vértices como no
			previo[ i ] = -1; // inicializamos el previo del vértice i con -1

		}
	}

	/**
	 * @param inicial
	 *            : manda el nodo desde donde se quiere comenzar la busqueda al
	 *            resto de nodos este algoritmo encuentra los caminos minimos de
	 *            un nodo u a todo el resto de nodos v
	 */
	public static void dijkstra( nodo inicial ) {// manda el nodo inicial para
													// marcarlo
		// y ponerlo pon peso 0
		Q.add( inicial );// metemos nodo icicial a la cola
		distancia[ inicial.vecino ] = 0;// la distancia de el a el mismo es 0

		while ( !Q.isEmpty( ) ) {
			nodo ac = Q.poll( );
			if ( visitado[ ac.vecino ] )
				continue;// para que no revisite vertices
			visitado[ ac.vecino ] = true;// marca el vertice actual
			/**
			 * el ciclo avanza por todos los vertices adyacentes al nodo actual,
			 * usando la lista de nodos
			 */
			for ( int i = 0; i < adj[ ac.vecino ].size( ); i++ ) {
				nodo adyacente = adj[ ac.vecino ].get( i );
				if ( !visitado[ adyacente.vecino ] ) {// en caso que no esté
														// visitado ya
					minPeso( ac.vecino, adyacente.vecino, adyacente.peso, adyacente.bus );
				}
			}
		}
	}

	public static void minPeso( int actual, int adyacente, int peso, int bus ) {
		/*
		 * Si la distancia del origen al vertice actual + peso de su arista es
		 * menor a la distancia del origen al vertice adyacente
		 */
		if ( distancia[ actual ] + peso < distancia[ adyacente ] ) {
			distancia[ adyacente ] = distancia[ actual ] + peso; // cambiamos
			// la distancia del vertice al inicio
			previo[ adyacente ] = actual;// a su vez actualizamos el vertice
											// previo
			Q.add( new nodo( adyacente, distancia[ adyacente ], bus ) );//
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

	public static int zona[];

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0 0" ) )
				break;
			int n = parseo( line, 0 );
			int m = parseo( line, 1 );
			zona = new int[ m + 1 ];
			for ( int i = 1; i <= n; i++ ) {
				int p[] = atoi( in.readLine( ) );
				for ( int j = 1; j < p.length; j++ ) {
					zona[ p[ j ] ] = i;
				}
			}
			V = m;
			inint( );
			int w[] = atoi( in.readLine( ) );
			for ( int i = 1; i <= w[ 0 ]; i++ ) {
				int p[] = atoi( in.readLine( ) );
				for ( int j = 2; j < p.length - 1; j++ ) {
					adj[ p[ j - 1 ] ].add( new nodo( p[ j ], (zona[ p[ j ] ] != zona[ p[ j - 1 ] ]) ? 4 : 0, i ) );
					adj[ p[ j ] ].add( new nodo( p[ j - 1 ], (zona[ p[ j ] ] != zona[ p[ j - 1 ] ]) ? 4 : 0, i ) );
				}
			}
			for ( int i = 1; i <= w[ 0 ]; i++ ) {
				int p[] = atoi( in.readLine( ) );
				for ( int j = 2; j < p.length - 1; j++ ) {
					adj[ p[ j - 1 ] ].add( new nodo( p[ j ], 0, -i ) );
					adj[ p[ j ] ].add( new nodo( p[ j - 1 ], 0, -i ) );
				}
			}

			int res[] = atoi( in.readLine( ) );
			int ini = res[ 0 ];
			int fin = res[ 1 ];
		}
		System.out.print( out );
	}

}
