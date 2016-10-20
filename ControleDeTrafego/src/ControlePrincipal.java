public class ControlePrincipal 
{
	private static Cruzamento1 		cruzamento1 = new Cruzamento1();
	private static Cruzamento2 		cruzamento2 = new Cruzamento2();

	private static int []carrosVindo = new int[3];
	
	//CONSTANTES
	//Fluxo de carros nas avenidas
	private static final int FLUXO_AV_1 = 50;
	private static final int FLUXO_AV_2 = 30;
	private static final int FLUXO_AV_3 = 20;
	
	//Fator multiplicador para a distribuicao de tempo
	private static final int FATOR1 = 60;
	private static final int FATOR2 = 30;
	private static final int FATOR3 = 10;
	
	//controles dos tempos de exibicao do programa
	private static final int TEMPO_PASSAGEM_CARROS = 200; //em milisegundos
	private static final int TEMPO_REINICIO = 2000; //em milisegundos
	
	
	private static void controladorDeTrafego ()
	{
	  //boolean temCarros = false;	  
	  double peso = 0.0;
	  
	  //while (temCarros == false)
	 // {    
	   carrosVindo[0]+= (int) (Math.random()*FLUXO_AV_1); //av1
	   carrosVindo[1]+= (int) (Math.random()*FLUXO_AV_2); //rua2    
	   carrosVindo[2]+= (int) (Math.random()*FLUXO_AV_3); //rua3

//		   if((carrosVindo[0] != 0) && (carrosVindo[1] != 0) && (carrosVindo[2] != 0))
//			   temCarros = true;

	  
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
		
		int vezes1 = (int) Math.round(v1 * FATOR1);
		int vezes2 = (int) Math.round( Math.abs((v2 * FATOR2)) );
		int vezes3 = (int) Math.round( Math.abs((v3 * FATOR3)) );
		
		int aux = (vezes2 >= vezes3)? vezes2: vezes3;
		try 
		{
			System.out.println("\nAv1: " + carrosVindo[0] + " Av2: " + carrosVindo[1] + " Av3: " + carrosVindo[2] +"\n");
			
			
			if ( obtemCruzamentoAV1("avenida1", cruzamento1, cruzamento2) )
			{

				System.out.println("Avenida 1 liberada -> Avenidas 2 e 3 bloqueadas");
				System.out.println("A avenida 1 sera liberada por " + vezes1 + " segundos\n");
				for ( int contador = 0; contador < vezes1; contador++ )
				{
					if (carrosVindo[0] > 0)
						carrosVindo[0]--;
					System.out.println("Ha: " + carrosVindo[0] + " carros na Avenida 1");
					Thread.sleep(TEMPO_PASSAGEM_CARROS);			
				}
			}
			
			if ( obtemCruzamentoAV2("avenida2", cruzamento1) && obtemCruzamentoAV3("avenida3", cruzamento2) )
			{
//				System.out.println("\n\n\n");
				System.out.println("Avenidas 2 e 3 liberadas -> Avenida 1 bloqueada");
				System.out.println("A avenida 2 sera liberada por " + vezes2 + " segundos");
				System.out.println("A avenida 3 sera liberada por " + vezes3 + " segundos\n");
				
				for ( int contador = 0; contador < aux; contador++ )
				{
					if ( (carrosVindo[1] > 0) && (contador < vezes2) )
						carrosVindo[1]--;
					
					if ( (carrosVindo[2] > 0) && (contador < vezes3) )
						carrosVindo[2]--;
					
					System.out.println("Ha: " + carrosVindo[1] + " na Avenida 2 e " + carrosVindo[2] + " na Avenida3");					
					Thread.sleep(TEMPO_PASSAGEM_CARROS);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	//reserva o cruzamento1 e o 2 para a avenida1
	public static boolean obtemCruzamentoAV1 ( String idAvenida, Cruzamento1 cruzamento1, Cruzamento2 cruzamento2)
	{
		if ( !cruzamento1.getCruzamento() )
		{
			System.out.println("Nao foi possivel utilizar o cruzamento 1");
			return (false);
		}
		
		if ( !cruzamento2.getCruzamento() )
		{
			System.out.println("Nao foi possivel utilizar o cruzamento 2");
			return (false);
		}
		
		System.out.println("Os cruzamentos 1 e 2 foram reservados para " + idAvenida);
		return (true);
	}
	
	//reserva o cruzamento1 para a avenida2
	public static boolean obtemCruzamentoAV2 ( String idAvenida, Cruzamento1 cruzamento1)
	{
		if ( !cruzamento1.getCruzamento() )
		{
			System.out.println("Nao foi possivel utilizar o cruzamento 1");
			return (false);
		}

		System.out.println("O cruzamento 1 foi reservado para " + idAvenida);
		return (true);
	}
	
	//reserva o cruzamento2  para a avenida3
	public static boolean obtemCruzamentoAV3 ( String idAvenida, Cruzamento2 cruzamento2)
	{
		if ( !cruzamento2.getCruzamento() )
		{
			System.out.println("Nao foi possivel utilizar o cruzamento 2");
			return (false);
		}

		System.out.println("O cruzamento 2 foi reservado para " + idAvenida);
		return (true);
	}
	
	public static void main ( String[] args )
	{
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
