import java.io.IOException;
import java.util.LinkedList;

public class TestsTempsProcesseur {

	Carte carte;
	
	public TestsTempsProcesseur() {
		
	}
	
	public void chargerCarte ( Carte carte ) {
		this.carte = carte;
	}
	
	public void genererCarte ( int nbNoeuds, float densite ) throws NumberFormatException, IOException {
		this.carte = new Carte();
		
		CreationGraphe cg = new CreationGraphe(nbNoeuds, densite, "");
		String strIn = cg.toString(85);
		System.out.println("Loading :\n"+strIn);
		carte.loadFromString(strIn);
		
	}
	
	public TestsTempsProcesseur( Carte carte ) {
		this.chargerCarte(carte);
	}
	
	public TestsTempsProcesseur( int nbNoeuds, float densite ) throws NumberFormatException, IOException {
		this.genererCarte( nbNoeuds, densite);
	}
	
	public long testDetourBorne( double coeff ) {
		
		int startID = 0;
		int destID = 0;
		while ( startID == destID ) {
			startID = randomInt( 0, carte.getNbNoeuds()-1 );
			destID = randomInt( 0, carte.getNbNoeuds()-1 );
		}
		
		Chrono chrono = new Chrono();
		
		chrono.start();
		LinkedList<Route> res = carte.genererItineraireDetourBorne( carte.getVilleFromId(startID), carte.getVilleFromId(destID), coeff);
		chrono.stop();
		
		return ( res != null  ? chrono.getTime() : -1);
		// Si on a un resultat, on renvoie le temps sinon "-1"
		
	}
	
	public long testCheminPlusCourt( ) {
		
		Chrono chrono = new Chrono();

		chrono.start();
		boolean res = carte.plusCourtDijkstra( carte.getVilleFromId(0) );
		chrono.stop();

		return ( res ? chrono.getTime() : -1);
		// Si on a un resultat, on renvoie le temps sinon "-1"
	}
	
	
	public int randomInt(int min, int max) {
		return (int)(Math.random() * (max-min)) + min;
	}
	
}
