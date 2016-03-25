package Lista1.Kolejki;
import java.util.ArrayList;


public class FIFO{
	ArrayList <Proces> fifo;
	int czasProcesora = 0;
	//czas oczekiwania = czas zakonczenia - zgloszenia - trwania;
	
	public FIFO (ArrayList<Proces> kolejka){
		fifo = kolejka;
	}

	public void wykonaj(){
		int i = 0;
		while(i<fifo.size()){
			Proces p = fifo.get(i);		
			if(i==0){	
				p.czasOczekiwania = 0;
				czasProcesora = p.czasZgloszenia + p.czasTrwania;
			}
			else{
				if(czasProcesora>p.czasZgloszenia){
					p.czasOczekiwania = czasProcesora - p.czasZgloszenia;
					czasProcesora+=p.czasTrwania;
				}
				else{
					czasProcesora = p.czasZgloszenia;
					p.czasOczekiwania = 0;
				}
			}
			i++;
//			System.out.println("");				
//			System.out.print(p);
//			System.out.println(" WYKONANO PROCES. CzasOczekiwania = "+p.czasOczekiwania);	
//			System.out.println("Czas procesora: "+czasProcesora);		
		}
	}
	
	public double sredniCzasOczekiwania(){
		int czas=0;
		for(Proces p : fifo){
			czas+=p.czasOczekiwania;
		}
		double srednia = czas/fifo.size();
		return srednia;		
	}
	
}
