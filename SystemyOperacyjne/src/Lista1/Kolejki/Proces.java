package Lista1.Kolejki;
import java.util.Random;


public class Proces{
	int czasTrwania;
	int czasZgloszenia;
	int czasZakonczenia = 0;
	int czasPozostaly;
	int czasOczekiwania = 0;
	boolean wykonany = false;
	int czasOcz = 0;

//czas oczekiwania = czas zakonczenia - zgloszenia - trwania;
	
	public Proces(int max){
		Random random = new Random();
		
		this.czasTrwania = random.nextInt(max);
		this.czasZgloszenia = random.nextInt(100);
		this.czasPozostaly = this.czasTrwania;
	}

	public Proces(Proces p){
//		int czasZakonczenia = p.czasZakonczenia;
		this.czasTrwania = p.czasTrwania;
		this.czasZgloszenia = p.czasZgloszenia;
		this.czasPozostaly = p.czasTrwania;
	}	
	

	
	
	
	
	public String toString(){
		return "["+czasZgloszenia+","+czasTrwania+"]";
	}
}
