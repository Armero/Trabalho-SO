public class Processo2 extends Thread{

	private static boolean parouNoMeio = false;
	
	public void run ()
	{	
		while (true)
		{
			try 
			{
				Thread.sleep(ControlePrincipal.TEMPO_REINICIO_THREADS);
				if ( ((ControlePrincipal.cruzamento1.getCruzamento("Avenida2", 2) == true) 
						&& (ControlePrincipal.cruzamento2.getCruzamento("Avenida3", 3) == true))
						|| (parouNoMeio == true) )
				{	
					parouNoMeio = true;
					//System.out.println("AQUI2");
					System.out.println("Avenidas 2 e 3 liberadas -> Avenida 1 bloqueada");
					System.out.println("A avenida 2 sera liberada por " + ControlePrincipal.vezes2 + " segundos");
					System.out.println("A avenida 3 sera liberada por " + ControlePrincipal.vezes3 + " segundos\n");
					
					for ( int contador = 0; contador < ControlePrincipal.aux; contador++ )
					{
						if ( (ControlePrincipal.carrosVindo[1] > 0) && (contador < ControlePrincipal.vezes2) )
							ControlePrincipal.carrosVindo[1]--;
						
						if ( (ControlePrincipal.carrosVindo[2] > 0) && (contador < ControlePrincipal.vezes3) )
							ControlePrincipal.carrosVindo[2]--;
						
						System.out.println("Ha: " + ControlePrincipal.carrosVindo[1] +  " carros  na Avenida 2 e " + ControlePrincipal.carrosVindo[2] + " carros na Avenida 3");					
						try {
							Thread.sleep(ControlePrincipal.TEMPO_PASSAGEM_CARROS);
						} 
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					//System.out.println("ANTES2");
					if ( (ControlePrincipal.cruzamento1.liberaSemaforo(2) == true)
							&&	(ControlePrincipal.cruzamento2.liberaSemaforo(3) == true) )
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