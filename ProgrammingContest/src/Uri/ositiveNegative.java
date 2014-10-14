package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ositiveNegative {

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
		int par = 0;
		int impar = 0;
		int p = 0;
		int c = 0;
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			int num = Integer.parseInt( line );
			if ( num > 0 ) {
				p++;
			}
			if ( num == 0 )
				c++;
			if ( num % 2 == 0 )
				par++;
			else
				impar++;
		}
		System.out.println( par + " valor(es) par(es)" );
		System.out.println( impar + " valor(es) impar(es)" );
		System.out.println( p + " valor(es) positivo(s)" );
		System.out.println( 5 - p - c + " valor(es) negativo(s)" );
	}

}
