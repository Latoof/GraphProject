import java.util.ArrayList;
import java.util.HashSet;


public class Graphe_matrice extends Graphe {

	
Matrice_Perso<HashSet<Integer>> 	matrice_adjacence;


	
	public Graphe_matrice() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new Matrice_Perso<HashSet<Integer>>();
	}
	
	public void ajouterSommet (Noeud n) {
		liste_noeud.add(n);
		
		matrice_adjacence.Add(new HashSet<Integer>(), n.getId());

		this.resizeMatrice();

		
	}
	
	public void resizeMatrice (){

		int size = liste_noeud.size();
		
		for (int i=0; i < size; i++){
						
			while (matrice_adjacence.getNumCols(i) < size ){
				matrice_adjacence.Add(new HashSet<Integer>(), i);
			}
		}
	}
	
	public void supprimerSommet (Noeud n) {
		HashSet<Integer> cellule_Matrice = new HashSet<Integer>();

		liste_noeud.remove((Noeud)n);
		
		for(int i=0; i < liste_noeud.size(); i++){
			matrice_adjacence.set(n.getId(), i, cellule_Matrice);
		}
	}
	
	public void ajouterArc (Arc a, Noeud nSource, Noeud nCible) {
		liste_arc.add(a);
		
		matrice_adjacence.get(nSource.getId(), nCible.getId()).add(a.getId());
		matrice_adjacence.get(nCible.getId(), nSource.getId()).add(a.getId());

		
	}
	
	public void supprimerArc (Arc a) {
		liste_arc.remove((Arc)a);
	}
	
	@Override
	public String toString() {
		return "Graphe_matrice \nliste_arc=" + liste_arc + "\nliste_noeud="
				+ liste_noeud + "\nmatrice_adjacence=" + matrice_adjacence;
	}
	
}
