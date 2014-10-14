package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class Array {

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
		String line = in.readLine( );
		double num = Double.parseDouble( line );

		double arr[] = new double[ 101 ];

		arr[ 0 ] = new BigDecimal( num ).setScale( 4, BigDecimal.ROUND_HALF_UP ).doubleValue( );
		System.out.println( arr[ 0 ] );
		for ( int i = 1; i < arr.length; i++ ) {
			arr[ i ] = arr[ i - 1 ] / 2;
		}

		for ( int i = 0; i < arr.length; i++ ) {
			// System.out.format(Locale.UK, "%.4", (arr[ i ]+"").replace( ".",
			// "," ) );
			arr[ i ] = new BigDecimal( arr[ i ] ).setScale( 4, BigDecimal.ROUND_HALF_UP ).doubleValue( );
			System.out.println( "N[" + i + "] = " + arr[ i ] );
		}

	}
}
