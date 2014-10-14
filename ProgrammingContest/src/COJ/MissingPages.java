package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MissingPages {

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
		
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if(line.equals( "0" ))
				break;
			int  n = parseo( line, 0 );
			int m = parseo( line, 1 );
			int max=n;
			int min=1;
			boolean flag=false;
			while(max!=m && min!=m){
				max--;
				min++;
				flag=!flag;
			}
			if(!flag){
				if(max==m){
					System.out.println(min + " "+(min+1)+" "+ (max-1)+"");
				}else{
					System.out.println(min+1 + " "+(max-1)+" "+ (max)+"");
				}
			}else{
				if(max==m){
					System.out.println((min-1) + " "+(min)+" "+ (max+1)+"");
				}else{
					System.out.println(min-1 + " "+(max)+" "+ (max+1)+"");
				}
			}
		}
		//System.out.print( out );
	}

}
