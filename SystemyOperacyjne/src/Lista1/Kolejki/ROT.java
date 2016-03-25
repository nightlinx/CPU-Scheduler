package Lista1.Kolejki;
import java.util.ArrayList;
import java.util.Collections;

public class ROT implements Queue{
	ArrayList<Proces> oczekujace = new ArrayList<Proces>(); // wykonywane
	ArrayList<Proces> rot = new ArrayList<Proces>();
	ArrayList<Proces> wykonane = new ArrayList<Proces>();

	int czasProcesora = 0;
	int kwant;
	int size;
	int wykonywany = 0;
	
	public ROT(ArrayList<Proces> kolejka, int kwant) {
		size = kolejka.size();
		rot = kolejka;
		this.kwant = kwant;
	}

	public void wykonajProces(Proces p) {
		if (p.czasPozostaly > kwant) {
			p.czasPozostaly -= kwant;
			wykonywany = kwant;
		} else {
			wykonywany = p.czasPozostaly;
			p.czasPozostaly = 0;
		}
	}
	
	
	public void dodajDoOczekujacych() {
		for (int i = 0; i < rot.size(); i++) {
			Proces p = rot.get(i);
			if (p.czasZgloszenia <= czasProcesora) {
				oczekujace.add(p);
				rot.remove(i);
				p.czasOczekiwania = czasProcesora - p.czasZgloszenia;
				i--;
			}
		}
	}

	public void wykonaj() {
		czasProcesora = 0;
		dodajDoOczekujacych();

		while (!oczekujace.isEmpty() || !rot.isEmpty()) {
			oczekujace.removeAll(Collections.singleton(null)); 
			Proces wykonywanyProces = null;
			wykonywany = 0;
			if (!oczekujace.isEmpty()) {
				wykonywanyProces = oczekujace.get(0); 
				wykonajProces(wykonywanyProces);
				oczekujace.remove(wykonywanyProces);
				for (int i = 0; i < oczekujace.size(); i++) {
					oczekujace.get(i).czasOczekiwania += wykonywany;
				}

			}
			if(wykonywanyProces == null)
				czasProcesora+=kwant;
			else{
				czasProcesora+=wykonywany;
			}
			dodajDoOczekujacych();
			
			if (wykonywanyProces != null && wykonywanyProces.czasPozostaly == 0) {
				wykonane.add(wykonywanyProces);
			} else {
				oczekujace.add(wykonywanyProces); // p idze na koniec kolejki
			}
		}
	}

	public void drukuj() {
		for (Proces p : wykonane) {
			System.out.print(p);
			System.out.println(" czas oczekiwania: " + p.czasOczekiwania);
		}
	}
	
	public double sredniCzasOczekiwania(){
		int czas=0;
		for(Proces p : wykonane){
			czas+=p.czasOczekiwania;
		}
		double srednia = czas/size;
		return srednia;		
	}

}
