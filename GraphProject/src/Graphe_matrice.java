import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;


public class Graphe_matrice {
	
	List<Noeud> liste_noeud;
	List<Arc>	liste_arc;
	
	Matrice_Perso<HashSet<Integer>> matrice_adjacence;

	/**
	 * @param listePoint
	 * @param listeArc
	 * @param matriceAdjacence
	 */
	public Graphe_matrice() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new Matrice_Perso<HashSet<Integer>>();
	}
	
	public void ajouterSommet (Noeud n) {
		liste_noeud.add(n);

	}
	
	public void supprimerSommet (Noeud n) {

		liste_noeud.remove((Noeud)n);
	}
	
	public void ajouterArc (Arc a, Noeud nSource, Noeud nCible) {
		liste_arc.add(a);
		//matrice_adjacence.
		
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
