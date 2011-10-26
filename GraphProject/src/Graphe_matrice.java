import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class Graphe_matrice extends Graphe {

	
Matrice_Perso<HashSet<Integer>> 	matrice_adjacence;
HashSet<Integer> cellule_Matrice = new HashSet<Integer>();
	
	public Graphe_matrice() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new Matrice_Perso<HashSet<Integer>>();
	}
	
	public void ajouterSommet (Noeud n) {
		liste_noeud.add(n);
		
//		HashSet<Integer> cellule_Matrice = new HashSet<Integer>();
		matrice_adjacence.Add(cellule_Matrice, n.getId());

		int size = liste_noeud.size();
			
		for (int i=0; i < size; i++){ 
			matrice_adjacence.Add(cellule_Matrice, i);
		}
		this.resizeMatrice();

		
	}
	
	public void resizeMatrice (){
		//HashSet<Integer> cellule_Matrice = new HashSet<Integer>();
		int size = liste_noeud.size();
		
		for (int i=0; i < size; i++){
						
			while ((matrice_adjacence.getNumCols(i) - 1) < size ){
				matrice_adjacence.Add(cellule_Matrice, i);
			}
		}
	}
	
	public void supprimerSommet (Noeud n) {
		//HashSet<Integer> cellule_Matrice = new HashSet<Integer>();

		liste_noeud.remove((Noeud)n);
		
		for(int i=0; i < liste_noeud.size(); i++){
			matrice_adjacence.set(n.getId(), i, cellule_Matrice);
		}
	}
	
	public void ajouterArc (Arc a, Noeud nSource, Noeud nCible) {
		liste_arc.add(a);
		
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
