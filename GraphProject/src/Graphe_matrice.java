import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class Graphe_matrice extends Graphe {

	
ArrayList<ArrayList<HashSet<Integer>>> matrice_adjacence;
HashSet<Integer> defaut;

	
	public Graphe_matrice() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new ArrayList<ArrayList<HashSet<Integer>>>();
		
		defaut = new HashSet<Integer>();
		defaut.add(-1);
	}
	
	public void ajouterSommet (Noeud n) {
		liste_noeud.add(n);
		
		ArrayList<HashSet<Integer>> colonne_Matrice = new ArrayList<HashSet<Integer>>();
		HashSet<Integer> cellule_Matrice = new HashSet<Integer>();
		
		while(matrice_adjacence.size() < n.getId())
		{
			//On rajoute des lignes à notre 1er array (les lignes).
			matrice_adjacence.add(colonne_Matrice);
			
			//On verifie que nos colonnes fraichement créées existe aussi.
			while(matrice_adjacence.get(matrice_adjacence.size() - 1).size() < n.getId())
			{
				//On boucle en ajoutant les colonnes une a une, en considérant la derniere créée
				matrice_adjacence.get(matrice_adjacence.size() - 1).add(cellule_Matrice);
			}
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
		return "Graphe_matrice \nliste_arc=" + liste_arc + "\nliste_noeud="
				+ liste_noeud + "\nmatrice_adjacence=" + matrice_adjacence;
	}
	
}
