import java.util.concurrent.Semaphore;

public class Cruzamento {
	private Semaphore 				semaforo1		= new Semaphore(1);
	private boolean[] 				vez				= new boolean[2];
	private int 					idUltimo 		= 0;
	public  boolean					acabou			= true;
	private String					nomeCruzamento = "Sem cruzamento";
	
	public Cruzamento (String nome)
	{
		nomeCruzamento = nome;
	}
	
	synchronized public boolean getCruzamento( String idAvenida, int cruzNum )
	{
		//System.out.println("vez: " + idNum + " status: " + vez[idNum - 1]);
		if ( vez[cruzNum - 1] == true )
		{
			//System.out.println(idAvenida + " Tem que esperar a liberacao do cruzamento 1");
			return (false);
		}
		
		try {
			semaforo1.acquire();
			vez[cruzNum - 1] = true;
			idUltimo = cruzNum;
			System.out.println(idAvenida + " Travou o " + nomeCruzamento);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Nao Foi possivel obter o " + nomeCruzamento);
		}
		
		return (true);
			
	}
	
	public boolean liberaSemaforo( int cruzNum )
	{
		if ( cruzNum == idUltimo)
		{
			
			if ( (vez[0] == true) && (vez[1] == true) )
			{
				vez[0] = false;	
				vez[1] = false;
				acabou = true;
			}
			
			semaforo1.release();
			return (true);
		}
		return (false);
	}
}
