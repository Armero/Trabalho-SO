public class Processo1 extends Thread{
//	private boolean controle1 = false;
//	private boolean controle2 = false;
	private static boolean parouNoMeio = false;
	
	public void run ()
	{
		while (true)
		{
			
//			while( !(controle1 = ControlePrincipal.cruzamento1.getCruzamento("Avenida1", 1)) );
//			while( !(controle2 = ControlePrincipal.cruzamento2.getCruzamento("Avenida1", 1)) );
			try 
			{
				Thread.sleep(ControlePrincipal.TEMPO_REINICIO_THREADS);
				if  ( ((ControlePrincipal.cruzamento1.getCruzamento("Avenida1", 1) == true) 
						&& (ControlePrincipal.cruzamento2.getCruzamento("Avenida1", 1) == true)) 
					   || (parouNoMeio == true ) )
				{
					parouNoMeio = true;
					System.out.println("Avenida 1 liberada -> Avenidas 2 e 3 bloqueadas");
					System.out.println("A avenida 1 sera liberada por " + ControlePrincipal.vezes1 + " segundos\n");
					for ( int contador = 0; contador < ControlePrincipal.vezes1; contador++ )
					{
						if (ControlePrincipal.carrosVindo[0] > 0)
							ControlePrincipal.carrosVindo[0]--;
						
						System.out.println("Ha: " + ControlePrincipal.carrosVindo[0] + " carros na Avenida 1");
						
						try
						{
							Thread.sleep(ControlePrincipal.TEMPO_PASSAGEM_CARROS);
						}
						catch (Exception e)
						{
					         e.printStackTrace();
						}
					}
					
					if ( (ControlePrincipal.cruzamento1.liberaSemaforo(1) == true)
						&&	(ControlePrincipal.cruzamento2.liberaSemaforo(1) == true) )
							parouNoMeio = false;

				}
			}
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}
	
}