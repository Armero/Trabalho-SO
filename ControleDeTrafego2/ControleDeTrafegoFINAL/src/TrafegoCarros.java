
public class TrafegoCarros extends Thread{

	public static double peso = 0.0;
	public void run ()
	{
		while (true)
		{
			int tempCarros1, tempCarros2, tempCarros3;
			
			tempCarros1 = (int) (Math.random()*ControlePrincipal.FLUXO_AV_1); //av1
			tempCarros2 = (int) (Math.random()*ControlePrincipal.FLUXO_AV_2); //av1
			tempCarros3 = (int) (Math.random()*ControlePrincipal.FLUXO_AV_3); //av1
			
			ControlePrincipal.carrosVindo[0]+= tempCarros1;
			ControlePrincipal.carrosVindo[1]+= tempCarros2;    
			ControlePrincipal.carrosVindo[2]+= tempCarros3;

		  
		    if (ControlePrincipal.carrosVindo[1] > ControlePrincipal.carrosVindo[2])
		    {
		    	peso = (double) (ControlePrincipal.carrosVindo[0]);
			    peso /= (double) (ControlePrincipal.carrosVindo[0] + ControlePrincipal.carrosVindo[1]);
		    }
		    else
		    {
			    peso = (double) ControlePrincipal.carrosVindo[0];
			    peso /= (double)(ControlePrincipal.carrosVindo[2] + ControlePrincipal.carrosVindo[0]);
		    }
		    
		    System.out.println("Av1+= " + tempCarros1 +" Av2+= " + tempCarros2 + " Av3+= " + tempCarros3);
		    
		    try{
		    	Thread.sleep((int)(Math.random() * ControlePrincipal.INSERIR_TRAFEGO));
		    }
			catch (Exception e)
			{
		         e.printStackTrace();
			}
		}	
	}
}
