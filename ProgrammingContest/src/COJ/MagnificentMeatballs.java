package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MagnificentMeatballs {

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
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if ( line.equals( "0" ) )
				break;
			int arr[] = atoi( line );
			int sumA=0;
			int sumB=0;
			boolean k=false;
			int w=arr.length;
			for ( int i = 1; i < arr.length; i++ ) {
				sumA+=arr[i];
				sumB=0;
				for ( int j = arr.length-1; j >i; j-- ) {
					sumB+=arr[j];
				}
				w--;
				if(sumA==sumB){
					out.append( "Sam stops at position "+ (i) + " and Ella stops at position "+ (i+1)+".\n" );
					k=true;
					break;
				}
			}
			if(!k){
				out.append( "No equal partitioning.\n" );
			}
		}
		
		System.out.print( out );
	}

}
