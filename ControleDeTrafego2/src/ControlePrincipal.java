public class ControlePrincipal 
{
	public static Cruzamento1 		cruzamento1 = new Cruzamento1();
	public static Cruzamento2 		cruzamento2 = new Cruzamento2();

	public static int vezes1 = 0;
	public static int vezes2 = 0;
	public static int vezes3 = 0;
	public static int aux = 0;

	public static int []carrosVindo = new int[3];
	
	//CONSTANTES
	//Fluxo de carros nas avenidas
	private static final int FLUXO_AV_1 = 10;
	private static final int FLUXO_AV_2 = 10;
	private static final int FLUXO_AV_3 = 10;
	
	//Fator multiplicador para a distribuicao de tempo
	private static final int FATOR1 = 10;
	private static final int FATOR2 = 10;
	private static final int FATOR3 = 10;
	
	//controles dos tempos de exibicao do programa
	public static final int TEMPO_PASSAGEM_CARROS = 200; //em milisegundos
	public static final int TEMPO_REINICIO = 2000; //em milisegundos
	public static final int TEMPO_REINICIO_THREADS = 1000;
	
	public static Processo1 p1 = new Processo1 ();
	public static Processo2 p2 = new Processo2 ();

	
	
	private static void controladorDeTrafego ()
	{
	  double peso = 0.0;
	    
	   carrosVindo[0]+= (int) (Math.random()*FLUXO_AV_1); //av1
	   carrosVindo[1]+= (int) (Math.random()*FLUXO_AV_2); //rua2    
	   carrosVindo[2]+= (int) (Math.random()*FLUXO_AV_3); //rua3

	  
	  if (carrosVindo[1] > carrosVindo[2])
	  {
		    peso = (double) (carrosVindo[0]);
		    peso /= (double) (carrosVindo[0] + carrosVindo[1]);
	  }
	  else
	  {
		    peso = (double) carrosVindo[0];
		    peso /= (double)(carrosVindo[2] + carrosVindo[0]);
	  }	
	  
	  printStatus ( (1 - peso), (peso), (peso));
	}
	
	private static void printStatus (double v1, double v2, double v3)
	{
		
		vezes1 = (int) Math.round(v1 * FATOR1);
		vezes2 = (int) Math.round( Math.abs((v2 * FATOR2)) );
		vezes3 = (int) Math.round( Math.abs((v3 * FATOR3)) );
		
		aux = (vezes2 >= vezes3)? vezes2 : vezes3;
		
		System.out.println("\nAv1: " + carrosVindo[0] + " Av2: " + carrosVindo[1] + " Av3: " + carrosVindo[2] +"\n");
	}
	
	
	public static void main ( String[] args )
	{
		p1.start();
		p2.start();
		
		while (true)
		{			
			try
			{
				//controla o trafego a cada segundo

				controladorDeTrafego ();
				Thread.sleep(TEMPO_REINICIO);
			}
			catch (Exception e)
			{
		         e.printStackTrace();
			}
		}
	}

}
