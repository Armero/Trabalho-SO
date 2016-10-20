import java.util.concurrent.Semaphore;

public class Cruzamento1 {
	private static  Semaphore 		semaforo1		= new Semaphore(1);
	private static boolean[] 		vez				= new boolean[2];
	private static int 				idUltimo 		= 0;
	
	synchronized public boolean getCruzamento( String idAvenida, int idNum )
	{
		//System.out.println("vez: " + idNum + " status: " + vez[idNum - 1]);
		if ( vez[idNum - 1] == true )
		{
			//System.out.println(idAvenida + " Tem que esperar a liberacao do cruzamento 1");
			return (false);
		}
		
		try {
			semaforo1.acquire();
			vez[idNum - 1] = true;
			idUltimo = idNum -1;
			System.out.println(idAvenida + " Travou o cruzamento 1");
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Nao Foi possivel obter o cruzamento 2");
		}
		
		return (true);
			
	}
	
	synchronized public void liberaSemaforo( int idNum )
	{
		System.out.println("idUltimo: " + (idUltimo+1) );
		if ( (idNum - 1) == idUltimo)
		{
			System.out.println("Cruzamento1 liberado pela avenida" + idNum);
			
			System.out.println("Vez 0, 1 " + vez[0] + " " + vez[1]);
			
			if ( (vez[0] == true) && (vez[1] == true) )
			{
				vez[0] = false;	
				vez[1] = false;
			}
			
			semaforo1.release();
		}
	}
}
