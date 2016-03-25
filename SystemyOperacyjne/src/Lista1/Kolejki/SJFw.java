package Lista1.Kolejki;
import java.util.ArrayList;

public class SJFw implements Queue{
	ArrayList <Proces> sjfw; //wszystkie
	ArrayList <Proces> oczekujace = new ArrayList <Proces>();
	int czasProcesora = 0;
	int i;
	
	public SJFw(ArrayList <Proces> kolejka){
		sjfw = kolejka;
	}
	
	public void start(Proces p){
		p.czasZakonczenia = p.czasZgloszenia + p.czasTrwania;		
	}
	
	public void wykonajProces(Proces p){	
		p.czasOczekiwania = czasProcesora - p.czasZgloszenia;
		czasProcesora+=p.czasTrwania;	
		p.wykonany = true;
	}
	
	
	public void wykonaj(){
		i = 0;
		while(i<sjfw.size()){
			Proces p = sjfw.get(i);		
			if(i==0){	//if p.czasOczekiwania == 0;
				start(p);
			}
			p.czasZakonczenia = p.czasZgloszenia + p.czasTrwania;
			for(int j=p.czasZgloszenia;j<p.czasZakonczenia;j++){
				if(i+1<sjfw.size()){
					Proces p2 = sjfw.get(i+1);			
					if((p2.czasZgloszenia == j) && (p2.czasTrwania < p.czasPozostaly)){
						czasProcesora = j;
						wykonajProces(p2);
						p.czasZakonczenia+=p2.czasTrwania;
						p.czasOczekiwania +=+p2.czasTrwania;
						i++;
					}
					else{
						p.czasPozostaly -= 1;
					}
				}
			}
			p.wykonany = true;
			czasProcesora = p.czasZakonczenia;
			i++;
		}
	}
	
	public void drukuj() {
		for (Proces p : sjfw) {
			System.out.print(p);
			System.out.println(" czas oczekiwania: " + p.czasOczekiwania);
		}
	}
	
	public double sredniCzasOczekiwania(){
		int czas=0;
		for(Proces p : sjfw){
			czas+=p.czasOczekiwania;
		}
		double srednia = czas;///(sjfw.size());
		return srednia;		
	}
	
}
