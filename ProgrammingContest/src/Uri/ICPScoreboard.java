package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ICPScoreboard {

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

	public static boolean ok( Team teams[], int k ) {
		for ( int i = 1; i < teams.length; i++ ) {
			if ( teams[ i ].numberSolved == teams[ i - 1 ].numberSolved
					&& ((teams[ i ].score( k ) < teams[ i - 1 ].score( k ) && teams[ i ].initScore >= teams[ i - 1 ].initScore) || (teams[ i ]
							.score( k ) == teams[ i - 1 ].score( k ) && teams[ i ].initScore > teams[ i - 1 ].initScore)) ) {
				return true;
			}
		}
		return false;
	}

	public static boolean ok2( Team teams[], int k ) {
		for ( int i = 1; i < teams.length; i++ ) {
			if ( (teams[ i ].numberSolved == teams[ i - 1 ].numberSolved
					&& (teams[ i ].score( k ) < teams[ i - 1 ].score( k ) && teams[ i ].initScore >= teams[ i - 1 ].initScore) || (teams[ i ]
					.score( k ) != teams[ i - 1 ].score( k ) && teams[ i ].initScore == teams[ i - 1 ].initScore)) ) {
				return true;
			}
		}
		return false;
	}

	public static int binarySearch( Team teams[], int min, int max ) {
		try {
			while ( min < max ) {
				int imid = min + (max - min) / 2;
				boolean x = ok( teams, imid );
				if ( !x )
					min = imid + 1;
				else if ( x )
					max = imid - 1;
			}
		} catch ( Exception e ) {
			return 3001;
		}

		return min;
	}

	public static int binarySearch2( Team teams[], int min, int max ) {
		try {

			while ( min < max ) {
				int imid = min + (max - min) / 2;
				boolean x = ok( teams, imid );
				if ( x )
					min = imid + 1;
				else if ( !x )

					max = imid - 1;
			}
		} catch ( Exception e ) {
			return 1;
		}

		return max;
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
			String res1 = null;
			int r1 = binarySearch( teams, 0, 3001 );
			if ( ok( teams, r1 ) ) {
				while ( ok( teams, r1 ) ) {
					r1--;
				}
				res1 = r1 + "";
			} else {
				if ( r1 == 3001 || r1 == 3001 ) {
					r1 = 3001;
					res1 = "*";
				} else {
					while ( !ok( teams, r1 + 1 ) ) {
						r1++;
					}
					res1 = r1 + "";
				}

			}
			int r2=0;
			if ( r1 != 3001 ) {
				r2=1;
			} else {
				if ( ok2( teams, r1 - 1 ) ) {
					r2 = r1;
				} else {
					r2 = binarySearch2( teams, 1, r1 );
					if ( r2 >= 1 ) {
						if ( ok( teams, r2 ) ) {
							while ( ok( teams, r2 ) ) {
								r2++;
							}
						} else {
							if ( r2 > 1 )
								while ( !ok( teams, r2 - 1 ) ) {
									r2--;
								}

						}
					}
				}
			}

			out.append( ((r2 == 0) ? 1 : r2) + " " + res1 + "\n" );
		}
		System.out.print( out );
	}

}
