package Lista1.Kolejki;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	static ArrayList<Proces> kolejka = new ArrayList<Proces>();
	static double sredniCzasFIFO;
	static double sredniCzasSJF;
	static double sredniCzasSJFw;
	static double sredniCzasROT;
	
	static double CzasyFIFO = 0;
	static double CzasySJF = 0;
	static double CzasySJFw = 0;
	static double CzasyROT = 0;	
	
	public static void main(String[]args){
		Main main = new Main();

	    Scanner in = new Scanner(System.in);
	    System.out.println("Podaj liczbe procesow w kolejce");
	    int liczba = in.nextInt();
	    System.out.println("Podaj liczbe kolejek do wykonania");
	    int n = in.nextInt();	    
	    
	    System.out.println("Podaj max czas trwania");
	    int max = in.nextInt();	    
	    
	    System.out.println("Podaj kwant czasu (np 3) ");	   
	    int kwant = in.nextInt();
	    in.close();
	    
		for(int i = 0; i<n; i++){
			kolejka = Main.losujProcesy(liczba, max);
			kolejka = main.sortuj(kolejka);
			
			FIFO fifo = new FIFO(cloneList(kolejka));
			fifo.wykonaj();
			CzasyFIFO+=fifo.sredniCzasOczekiwania();
				
			SJF sjf = new SJF(cloneList(kolejka));
			sjf.wykonaj();
			CzasySJF+=sjf.sredniCzasOczekiwania();			
			
			SJFw sjfw = new SJFw(cloneList(kolejka));
			sjfw.wykonaj();			
			CzasySJFw+=sjfw.sredniCzasOczekiwania();	
						
			ROT rot = new ROT(cloneList(kolejka), kwant);
			rot.wykonaj();		
			CzasyROT+=rot.sredniCzasOczekiwania();		
		}
		
		sredniCzasFIFO = CzasyFIFO/n;
		sredniCzasSJF = CzasySJF/n;
		sredniCzasSJFw = CzasySJFw/n;
		sredniCzasROT = CzasyROT/n;
		
		System.out.println("sredniCzasFIFO "+sredniCzasFIFO);
		System.out.println("sredniCzasSJF "+ sredniCzasSJF);
		System.out.println("sredniCzasSJFw "+sredniCzasSJFw);
		System.out.println("sredniCzasROT "+sredniCzasROT);

	}

	
	public static ArrayList <Proces> losujProcesy(int n, int max){
		ArrayList<Proces> kolejka = new ArrayList<Proces>();
		for(int i = 0; i < n; i++){
			kolejka.add(new Proces(max));
		}
		return kolejka;
	}
	
	public static ArrayList <Proces> cloneList(ArrayList <Proces> kolejka) {
		ArrayList <Proces> clonedList = new ArrayList <Proces>(kolejka.size());
		   for (Proces p : kolejka) {
		       clonedList.add(new Proces(p));
		   }
		 
		   return clonedList;
	}
	
	public ArrayList<Proces> sortuj (ArrayList<Proces> kolejka){ //wg czasu zgloszenia
        int size = kolejka.size();

        for (int pass = 1; pass < size; ++pass) {
            for (int left = 0; left < (size - pass); ++left) {
                int right = left + 1;
                if (kolejka.get(left).czasZgloszenia > kolejka.get(right).czasZgloszenia)
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
