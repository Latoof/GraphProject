import java.util.ArrayList;
import java.util.List;


public class Graphe_matrice {
	
	List<Noeud> liste_noeud;
	List<Arc>	liste_arc;
	
	ArrayList<Noeud> matrice_adjacence;

	/**
	 * @param listePoint
	 * @param listeArc
	 * @param matriceAdjacence
	 */
	public Graphe_matrice() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new ArrayList<Noeud>();
	}
	
	public void ajouterSommet (Noeud n) {
		liste_noeud.add(n);

	}
	
	public void supprimerSommet (Noeud n) {

		liste_noeud.remove((Noeud)n);
	}
	
	public void ajouterArc (Arc a) {
		liste_arc.add(a);
		
	}
	
	public void supprimerArc (Arc a) {
		liste_arc.remove((Arc)a);
	}

	@Override
	public String toString() {
		return "Graphe_matrice \n[liste_arc=" + liste_arc + "\nliste_noeud="
				+ liste_noeud + "\nmatrice_adjacence=" + matrice_adjacence
				+ "]";
	}
	
	

}
