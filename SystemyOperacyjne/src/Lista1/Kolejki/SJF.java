package Lista1.Kolejki;
import java.util.ArrayList;

public class SJF implements Queue{
	ArrayList <Proces> sjf; //wszystkie
	ArrayList <Proces> oczekujace = new ArrayList <Proces>();
	int czasProcesora = 0;
	//czas oczekiwania = czas zakonczenia - zgloszenia - trwania;
	boolean saOczekujace;
	int i;
	
	public SJF (ArrayList<Proces> kolejka){
		sjf = kolejka;
	}
	
	
	public void wykonajProces(Proces p){ 
		p.czasOczekiwania = czasProcesora - p.czasZgloszenia;
		czasProcesora+=p.czasTrwania;	
		p.wykonany = true;		
	}
	
	public void wykonajProces2(Proces p){ //pierwszy lub ostatni
		p.czasOczekiwania = 0;
		czasProcesora = p.czasZgloszenia + p.czasTrwania;
		p.wykonany = true;
	}

	public void wykonaj(){
		i = 0;
		while(i<sjf.size()){
			Proces p = sjf.get(i);
			if(i==0){	
				wykonajProces2(p);
			}	
			saOczekujace = true;
			
			while(saOczekujace){    //gdy jest kolejka oczekujacych
				i = aktualizujListeOczekujacych(i);
//				drukujOczekujace();
				
				if(oczekujace.size()==0){
					saOczekujace = false;
					break;
				}
				
				oczekujace = sortuj(oczekujace);
				Proces min = oczekujace.get(0);
				wykonajProces(min);
				oczekujace.remove(0);	
			}
			
			if(!saOczekujace && i+1<sjf.size()){
				i++;
				p = sjf.get(i);
				wykonajProces2(p);				
			}
			i++;
		}
	}
	
	public void drukujOczekujace(){
		System.out.print("Oczekujace: ");
		for(Proces p: oczekujace)
			System.out.print(p);
		System.out.println("");
	}

	public int aktualizujListeOczekujacych(int i){ // i - indeks wlasnie wykonanego procesu
		boolean koniec = false;	
		while(!koniec && i+1<sjf.size()){         //lece przez liste i sprawdzam czy sa oczekujace
			i++;
			Proces pr = sjf.get(i);					
			
			if(!pr.wykonany && pr.czasZgloszenia<=czasProcesora){
				oczekujace.add(pr);
			}
			
			else{
				koniec = true;
				i--;
			}
		}	
		return i;
	}
	
	public void drukuj() {
		for (Proces p : sjf) {
			System.out.print(p);
			System.out.println(" czas oczekiwania: " + p.czasOczekiwania);
		}
	}
	
	public double sredniCzasOczekiwania(){
		int czas=0;
		for(Proces p : sjf){
			czas+=p.czasOczekiwania;
		}
		double srednia = czas/sjf.size();
		return srednia;		
	}
	
	public ArrayList <Proces> sortuj (ArrayList<Proces> kolejka){ //wg czasu trwania
        int size = kolejka.size();

        for (int pass = 1; pass < size; ++pass) {
            for (int left = 0; left < (size - pass); ++left) {
                int right = left + 1;
                if (kolejka.get(left).czasTrwania > kolejka.get(right).czasTrwania)
                   swap(kolejka, left, right);
            }
        }
		return kolejka;
	}

    private void swap(ArrayList<Proces> kolejka, int left, int right) {
        Proces temp = kolejka.get(left);
        kolejka.set(left, kolejka.get(right));
        kolejka.set(right, temp);
    }
}
