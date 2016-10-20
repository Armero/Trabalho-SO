import java.util.concurrent.Semaphore;

public class Cruzamento2 {
	private static  Semaphore 		semaforo2 		= new Semaphore(2);
	private static boolean[] 		vez				= new boolean[3];
	private static int				idUltimo 		= 0;
	
	synchronized public boolean getCruzamento( String idAvenida, int idNum )
	{	
		if ( vez[idNum - 1] )
		{
			//System.out.println(idAvenida + " Tem que esperar a liberacao do cruzamento 2");
			return (false);
		}
		
		try {
			vez[idNum - 1] = true;
			idUltimo = idNum - 1;
			semaforo2.acquire();
			System.out.println(idAvenida + " Travou o cruzamento 2");
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Nao Foi possivel obter o cruzamento 2");
			//e.printStackTrace();
		}
		
		return (true);
	}
	
	synchronized public void liberaSemaforo( int idNum )
	{
		if ( (idNum - 1) == idUltimo)
		{
			System.out.println("Cruzamento2 liberado pela avenida" + idNum);
			semaforo2.release();
			
			if ( (vez[0] == true) &&  (vez[2] == true) )
			{
				vez[0] = false;
				vez[2] = false;
			}
			
			semaforo2.release();
		}
	}
}
