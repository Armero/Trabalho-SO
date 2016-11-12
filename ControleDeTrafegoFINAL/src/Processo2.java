public class Processo2 extends Thread{

	private static boolean parouNoMeio = false;
	private int vezes;
	
	public void run ()
	{	
		while (true)
		{
			try 
			{
				if ( ((ControlePrincipal.cruzamento1.getCruzamento("Avenida2", 2) == true) 
						&& (ControlePrincipal.cruzamento2.getCruzamento("Avenida3", 2) == true))
						|| (parouNoMeio == true) )
				{	
					parouNoMeio = true;
					System.out.println("Avenidas 2 e 3 liberadas -> Avenida 1 bloqueada");
					System.out.println("A avenida 2 sera liberada por " + ControlePrincipal.vezes2 + " segundos");
					System.out.println("A avenida 3 sera liberada por " + ControlePrincipal.vezes3 + " segundos\n");
					
					vezes = (ControlePrincipal.vezes2 >= ControlePrincipal.vezes3)? ControlePrincipal.vezes2 : ControlePrincipal.vezes3;
					
					for ( int contador = 0; contador < vezes; contador++ )
					{
						if ( (ControlePrincipal.carrosVindo[1] > 0) && (contador < ControlePrincipal.vezes2) )
							ControlePrincipal.carrosVindo[1]--;
						
						if ( (ControlePrincipal.carrosVindo[2] > 0) && (contador < ControlePrincipal.vezes3) )
							ControlePrincipal.carrosVindo[2]--;
						
						System.out.println("Ha: " + ControlePrincipal.carrosVindo[1] +  " carros na Avenida 2 e " + ControlePrincipal.carrosVindo[2] + " carros na Avenida 3");					
						try {
							//Thread.sleep((int) (Math.random() * ControlePrincipal.TEMPO_PROCESSO1);
							Thread.sleep(ControlePrincipal.TEMPO_PASSAGEM_CARROS);
						} 
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if ( (ControlePrincipal.cruzamento1.liberaSemaforo(2) == true)
							&&	(ControlePrincipal.cruzamento2.liberaSemaforo(2) == true) )
						parouNoMeio = false;
					
					Thread.sleep((int) (Math.random() * ControlePrincipal.TEMPO_PROCESSO2));
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