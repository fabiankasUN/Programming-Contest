package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class AllYouNeedIsLove {

	// split de array
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
		int x = Integer.parseInt( in.readLine( ) );
		int cont=1;
		for ( int i = 0; i < x; i++ ) {
			BigInteger a = new BigInteger( in.readLine( ), 2 );
			BigInteger b = new BigInteger( in.readLine( ), 2 );
			BigInteger c=a.gcd( b );
			if(c.equals( new BigInteger( "1" ) ))
				System.out.println("Pair #"+cont+++": Love is not all you need!");
			else{
				System.out.println("Pair #"+cont+++": All you need is love!");
			}
		}

		//System.out.print( out );
	}
}
