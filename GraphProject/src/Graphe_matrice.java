import java.util.ArrayList;
import java.util.HashSet;


public class Graphe_matrice extends Graphe {

	
	ArrayList<ArrayList<HashSet<Integer>>> matrice_adjacence;

	
	public Graphe_matrice() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new ArrayList<ArrayList<HashSet<Integer>>>();
	}
	
	public void ajouterSommet (Noeud n) {
		liste_noeud.add(n);
		
		matrice_adjacence.ensureCapacity(n.getId());
		
		for(int i=0; i < liste_noeud.size(); i++)
		{
			matrice_adjacence.add(new ArrayList<HashSet<Integer>>());
			//System.out.println(i);
		}
		
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
