import java.io.IOException;
import java.util.LinkedList;

public class TestsTempsProcesseur {

	public Carte_m carte_m;
	public Carte_l carte_l;

	
	public TestsTempsProcesseur() {
		
	}
	
	public void chargerCarte ( Carte_m carte ) {
		this.carte_m = carte;
	}
	
	public void chargerCarte ( Carte_l carte ) {
		this.carte_l = carte;
	}
	
	public void genererCarte ( int nbNoeuds, float densite ) throws NumberFormatException, IOException {
		this.carte_m = new Carte_m();
		this.carte_l = new Carte_l();

		
		CreationGraphe cg = new CreationGraphe(nbNoeuds, densite, "");
		String strIn = cg.toString(85);
		System.out.println("Loading :\n"+strIn);
		//carte_m.loadFromString(strIn);
		carte_l.loadFromString(strIn);
		
	}
	
	public TestsTempsProcesseur( Carte_m carte ) {
		this.chargerCarte(carte);
	}
	
	public TestsTempsProcesseur( int nbNoeuds, float densite ) throws NumberFormatException, IOException {
		this.genererCarte( nbNoeuds, densite);
	}
	


	public long serieTestDetourBorne( int series, int type_imp, double coeff,  int nbNoeuds, float densite ) throws NumberFormatException, IOException {
		
		long temps_cumule = 0;
		int nb_succes = 0;
		long moyenne;
		double moyenne_cum = 0;
		
		for (int i=0; i<series; i++) {
			
			this.genererCarte( nbNoeuds, densite );
			
			long tps = this.testDetourBorne( type_imp, coeff );
			
			if ( tps != -1 ) {
				nb_succes++;
			//	moyenne_cum = (double) ( moyenne_cum * (float) (((nb_succes - 1) / nb_succes)) + tps * (float) (( 1  / nb_succes )) );
			//	System.out.println("MC### "+moyenne_cum+ " ( "+moyenne_cum * (float) (((nb_succes - 1) / nb_succes))+" + "+tps * (float) (( 1  / nb_succes )));
				temps_cumule += tps;
				
			}
		}
		
		
		moyenne = nb_succes > 0 ? temps_cumule / nb_succes : -1;
		System.out.println("total pour "+ nb_succes+ "resultats : "+temps_cumule+"("+moyenne);
	//	System.out.println("total test "+ nb_succes+ "resultats : "+moyenne_cum);

		
		return moyenne;
		
	}
	
	public long testDetourBorne( int type_imp, double coeff ) {
		
		if ( type_imp == AppliTest.MATRICE ) {
			int startID = 0;
			int destID = 0;
			while ( startID == destID ) {
				startID = randomInt( 0, carte_m.getNbNoeuds()-1 );
				destID = randomInt( 0, carte_m.getNbNoeuds()-1 );
			}
			
			Chrono chrono = new Chrono();
			
			chrono.start();
			LinkedList<Route> res = carte_m.genererItineraireDetourBorne( carte_m.getVilleFromId(startID), carte_m.getVilleFromId(destID), coeff);
			chrono.stop();
		
			return ( res != null  ? chrono.getTime() : -1);
			// Si on a un resultat, on renvoie le temps sinon "-1"
		}
		else {
			
			int startID = 0;
			int destID = 0;
			while ( startID == destID ) {
				startID = randomInt( 0, carte_l.getNbNoeuds()-1 );
				destID = randomInt( 0, carte_l.getNbNoeuds()-1 );
			}
			
			Chrono chrono = new Chrono();
			
			chrono.start();
			LinkedList<Route> res = carte_l.genererItineraireDetourBorne( carte_l.getVilleFromId(startID), carte_l.getVilleFromId(destID), coeff);
			chrono.stop();
		
			return ( res != null  ? chrono.getTime() : -1);
			// Si on a un resultat, on renvoie le temps sinon "-1"
			
		}
		
	}
	
	public long testCheminPlusCourt( int type_imp ) {
		
		if ( type_imp == AppliTest.MATRICE ) {
			Chrono chrono = new Chrono();
	
			chrono.start();
			boolean res = carte_m.plusCourtDijkstra( carte_m.getVilleFromId(0) );
			chrono.stop();
	
			return ( res ? chrono.getTime() : -1);
			// Si on a un resultat, on renvoie le temps sinon "-1"
		}
		else {
			
			Chrono chrono = new Chrono();
			
			chrono.start();
			boolean res = carte_l.plusCourtDijkstra( carte_l.getVilleFromId(0) );
			chrono.stop();
	
			return ( res ? chrono.getTime() : -1);
			
		}
	}
	
	
	public int randomInt(int min, int max) {
		return (int)(Math.random() * (max-min)) + min;
	}
	
}
