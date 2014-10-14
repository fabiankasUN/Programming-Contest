package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Banknotes {

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
		int m = Integer.parseInt( in.readLine( ) );
		int arr[] = { 100, 50, 20, 10, 5, 2, 1 };
		int res[] = new int[ 7 ];
		System.out.println( m );
		for ( int i = 0; i < res.length; i++ ) {
			res[ i ] = m / arr[ i ];
			m %= arr[ i ];
			System.out.println( res[ i ] + " nota(s) de R$ " + arr[ i ] + ",00" );
		}
	}
}
