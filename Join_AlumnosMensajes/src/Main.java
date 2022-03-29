import java.util.Date;

public class Main {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		Thread teapots=new Thread(new Grupo("Teapots"));
		Thread venThread=new Thread(new Grupo("VenThread"));
		Thread giA=new Thread(new Grupo("GiA"));
		
		teapots.start();
		teapots.join();
		//En la linea anterior (.join()) detiene el thread Main hasta que
		//teapots termina y luego sigue ejecutandose secuencialmente.
		
		venThread.start();
		venThread.join();
		
		giA.start();
		giA.join();
		
				
		//teapots.join();
		//venThread.join();
		//giA.join();
		
		
		System.out.printf("Main: Se finalizo la impresion de mensajes %s\n", new Date());
		
	
		
	}

}
