package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ICPScoreboard2 {
	public static class Team implements Comparable<Team> {
		int attemps;
		int timeFinish;
		int numberSolved;
		long score;
		long initScore;

		public Team ( ) {
			this.attemps = 0;
			this.timeFinish = 0;
			this.numberSolved = 0;
		}

		public long score( int k ) {
			score = timeFinish + attemps * k;
			return score;
		}

		@Override
		public int compareTo( Team o ) {
			if ( numberSolved > o.numberSolved )
				return -1;
			else if ( numberSolved < o.numberSolved )
				return 1;
			if ( score < o.score ) {
				return -1;
			} else if ( score > o.score )
				return 1;
			return 0;
		}

	}

	public static long lowBound( Team teamA, Team teamB, boolean w ) {
		long param = Math.abs( teamA.attemps - teamB.attemps );
		long p2 = Math.abs( (teamA.timeFinish) - teamB.timeFinish );
		float r = (float) Math.ceil( p2 / param * 1.0 );
		if ( p2 == 0 ) {
			return 50000;
		}
		if ( r == p2 / param * 1.0 ) {
			if ( w )
				return (long) (r + 1);
			else
				return (long) (r - 1);
		}
		if ( w )
			return (long) Math.floor( p2 / param * 1.0 );
		else
			return (long) Math.ceil( p2 / param * 1.0 );
	}

	public static boolean ok( Team teams[], int k ) {
		for ( int i = 1; i < teams.length; i++ ) {
			if ( teams[ i ].numberSolved == teams[ i - 1 ].numberSolved
					&& ((teams[ i ].score( k ) <= teams[ i - 1 ].score( k ) && teams[ i ].initScore > teams[ i - 1 ].initScore)) ) {
				return true;
			}
		}
		return false;
	}

	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );

		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0 0" ) )
				break;

			Team teams[] = new Team[ Integer.parseInt( line.split( " " )[ 0 ] ) ];
			int m = Integer.parseInt( line.split( " " )[ 1 ] );
			String cad[];
			String c[];
			for ( int i = 0; i < teams.length; i++ ) {
				cad = in.readLine( ).split( " " );
				teams[ i ] = new Team( );

				for ( int j = 0; j < m; j++ ) {
					c = cad[ j ].split( "/" );

					if ( !c[ 1 ].equals( "-" ) ) {
						teams[ i ].attemps += Integer.parseInt( c[ 0 ] ) - 1;
						teams[ i ].timeFinish += Integer.parseInt( c[ 1 ] );
						teams[ i ].numberSolved++;
					}
				}
				teams[ i ].initScore = teams[ i ].score( 20 );
			}

			Arrays.sort( teams );
			long a = 0, b = 0;
			if ( !ok( teams, 1 ) ) {
				a = 1;
				long max = -100000;
				for ( int i = 1; i < teams.length; i++ ) {
					if ( teams[ i ].numberSolved == teams[ i - 1 ].numberSolved && teams[ i - 1 ].numberSolved > 0 ) {
						if ( teams[ i ].initScore > teams[ i - 1 ].initScore ) {
							if ( teams[ i ].attemps != teams[ i - 1 ].attemps 
									&& (teams[ i ].attemps <= teams[ i - 1 ].attemps))
								max = Math.max( max, lowBound( teams[ i ], teams[ i - 1 ], false ) );
						} else {
							if ( teams[ i ].attemps != teams[ i - 1 ].attemps ) {
								max = lowBound( teams[ i ], teams[ i - 1 ], false ) + 1;
								a = max;
								break;
							}
						}
					}
				}
				if ( max == -100000 ) {
					a = 1;
					b = 50000;
				} else {
					b = max;
				}
			} else {
				b = 50000;
				long max = -100000;
				for ( int i = 1; i < teams.length; i++ ) {
					if ( teams[ i ].numberSolved == teams[ i - 1 ].numberSolved && teams[ i - 1 ].numberSolved > 0 ) {

						if ( teams[ i ].score > teams[ i - 1 ].score ) {
							if ( teams[ i ].attemps != teams[ i - 1 ].attemps )
								max = Math.max( max, lowBound( teams[ i ], teams[ i - 1 ], true ) );
						} else {
							if ( teams[ i ].attemps != teams[ i - 1 ].attemps ) {
								max = lowBound( teams[ i ], teams[ i - 1 ], true );
								a = max;
								break;
							}
						}
					}
				}
				/*if ( max == -100000 ) {
					a = 1;
					b = 500000;
				} else {
					b = max;
				}*/

			}
			out.append( a + " " + ((b == 50000) ? "*" : b) + "\n" );

		}
		System.out.print( out );
	}

}
