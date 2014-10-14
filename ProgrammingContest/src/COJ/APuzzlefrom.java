package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class APuzzlefrom {

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
		int w;
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0 0" ) )
				break;
			int n = parseo( line, 0 );
			int m = parseo( line, 1 );

			int sum=0;
			int k=0;
			for ( int i = 0; i < m; i++ ) {
				
				sum+=((n*n)+k)%10;
				k=((n*n)+k)/10;
			}
			sum+=k;		
			out.append( sum * m + "\n" );
		}
		System.out.print( out );
	}
}
