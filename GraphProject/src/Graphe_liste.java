import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Graphe_liste {

	List<Noeud> liste_noeud;
	List<Arc>	liste_arc;
	
	ArrayList<
		LinkedList< 
			ArrayList<
				Integer
			> 
		>
	> liste_adjacence;
	
	/**
	 * @param listePoint
	 * @param listeArc
	 * @param matriceAdjacence
	 */
	public Graphe_liste() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();
		//liste_adjacence = new ArrayList();
		
		liste_adjacence = new ArrayList< LinkedList<ArrayList<Integer>> >();
	}
	
	public void ajouterNoeud (Noeud n) {
		liste_noeud.add(n);
		
		LinkedList< ArrayList<Integer> > graphList = new LinkedList< ArrayList<Integer> >();
		graphList.get(0).add(n.id);
		
		liste_adjacence.add( graphList );
	}
	
	public void supprimerNoeud (Noeud n) {
		liste_noeud.remove(n);
	}
	
	public void ajouterArc(Arc a, Noeud n1, Noeud n2) {
		liste_arc.add(a);
		
		ArrayList<Integer> transList = new ArrayList<Integer>();
		transList.add(n2.id);
		transList.add(a.id);
		
		liste_adjacence.get(n1.id).add(transList);
	}

	@Override
	public String toString() {
		return "Graphe_matrice \n[liste_arc=" + liste_arc + "\nliste_noeud="
				+ liste_noeud + "\nmatrice_adjacence=" + liste_adjacence
				+ "]";
	}
	
	
}
