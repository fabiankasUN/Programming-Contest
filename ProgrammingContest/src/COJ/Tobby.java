package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Tobby {

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
		int  n = Integer.parseInt( in.readLine( ) );
		int arr[];
		int f1,f2;
		for ( int i = 0; i < n; i++ ) {
			arr= atoi( in.readLine( ) );
			boolean x=false;
			w:for ( int a = 0; a < 2; a++ ) {
				for ( int b = 0; b < 2; b++ ) {
					for ( int c = 0; c < 2; c++ ) {
						for ( int d = 0; d < 2; d++ ) {
							for ( int e = 0; e < 2; e++ ) {
								for ( int f = 0; f < 2; f++ ) {
									f1=0;
									f2=0;
									f1+=(a==0)?arr[0]:0;
									f1+=(b==0)?arr[1]:0;
									f1+=(c==0)?arr[2]:0;
									f1+=(d==0)?arr[3]:0;
									f1+=(e==0)?arr[4]:0;
									f1+=(f==0)?arr[5]:0;
									f2+=(a==1)?arr[0]:0;
									f2+=(b==1)?arr[1]:0;
									f2+=(c==1)?arr[2]:0;
									f2+=(d==1)?arr[3]:0;
									f2+=(e==1)?arr[4]:0;
									f2+=(f==1)?arr[5]:0;
									if(f1==f2){
										out.append( "Tobby puede cruzar\n" );
										x=true;
										break w;
									}
										
								}
							}
						}
					}
				}
			}
			if(!x)
				out.append( "Tobby no puede cruzar\n" );
		}
		
		System.out.print( out );
	}

}
