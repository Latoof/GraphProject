import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class Graphe_matrice extends Graphe {

	
ArrayList<ArrayList<HashSet<Integer>>> matrice_adjacence;

	
	public Graphe_matrice() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new ArrayList<ArrayList<HashSet<Integer>>>();
	}
	
	public void ajouterSommet (Noeud n) {
		liste_noeud.add(n);
		
		ArrayList<HashSet<Integer>> colonne_Matrice = new ArrayList<HashSet<Integer>>(10);
		HashSet<Integer> cellule_Matrice = new HashSet<Integer>();
		
		
		matrice_adjacence.set(n.getId(), colonne_Matrice);
//		colonne_Matrice.set(n.getId(), cellule_Matrice);
//		cellule_Matrice.add(null);
	}
	
	public void supprimerSommet (Noeud n) {

		liste_noeud.remove((Noeud)n);
	}
	
	public void ajouterArc (Arc a, Noeud nSource, Noeud nCible) {
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
