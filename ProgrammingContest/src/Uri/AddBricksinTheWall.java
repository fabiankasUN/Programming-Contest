package Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddBricksinTheWall {

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
		int t = Integer.parseInt( in.readLine( ) );
		for ( int i = 0; i < t; i++ ) {
			
		}
	}

}
