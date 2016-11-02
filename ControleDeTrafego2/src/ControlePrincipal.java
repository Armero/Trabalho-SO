public class ControlePrincipal 
{
	public static Cruzamento 		cruzamento1 = new Cruzamento("cruzamento 1");
	public static Cruzamento 		cruzamento2 = new Cruzamento("cruzamento 2");
	private static TrafegoCarros	carros		= new TrafegoCarros();
	
	public static int vezes1 = 0;
	public static int vezes2 = 0;
	public static int vezes3 = 0;
	public static int aux = 0;

	public static int []carrosVindo = new int[3];
	
	//CONSTANTES
	//Fluxo de carros nas avenidas
	public static final int FLUXO_AV_1 = 30;
	public static final int FLUXO_AV_2 = 10;
	public static final int FLUXO_AV_3 = 5;
	
	//Fator multiplicador para a distribuicao de tempo
	private static final int FATOR1 = 50;
	private static final int FATOR2 = 20;
	private static final int FATOR3 = 15;
	
	//controles dos tempos de exibicao do programa
	public static final int TEMPO_PASSAGEM_CARROS = 100; //em milisegundos
	public static final int TEMPO_REINICIO_CONTROLE = 2000; //em milisegundos
	public static final int TEMPO_PROCESSO1 = 1000;
	public static final int TEMPO_PROCESSO2 = 1000;
	public static final int INSERIR_TRAFEGO = 5000;
	
	public static Processo1 p1 = new Processo1 ();
	public static Processo2 p2 = new Processo2 ();

	
	
	private static void controladorDeTrafego ()
	{  
		if (cruzamento1.acabou && cruzamento2.acabou)
		{
		    printStatus ( (1 - TrafegoCarros.peso), (TrafegoCarros.peso), (TrafegoCarros.peso));
		    cruzamento1.acabou = false;
		    cruzamento2.acabou = false;
		}
	}
	
	private static void printStatus (double v1, double v2, double v3)
	{
		
		vezes1 = (int) Math.round(v1 * FATOR1);
		vezes2 = (int) Math.round( Math.abs((v2 * FATOR2)) );
		vezes3 = (int) Math.round( Math.abs((v3 * FATOR3)) );
		
		System.out.println("\nAv1: " + carrosVindo[0] + " Av2: " + carrosVindo[1] + " Av3: " + carrosVindo[2] +"\n");
	}
	
	
	public static void main ( String[] args )
	{
		carros.start();
		p1.start();
		p2.start();
		
		while (true)
		{			
			try
			{
				//controla o trafego a cada segundo
				controladorDeTrafego ();
				Thread.sleep(TEMPO_REINICIO_CONTROLE);
			}
			catch (Exception e)
			{
		         e.printStackTrace();
			}
		}
	}

}
