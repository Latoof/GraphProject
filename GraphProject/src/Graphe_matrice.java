import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Graphe_matrice extends Graphe {

	
Matrice_Perso<HashSet<Integer>> 	matrice_adjacence;
HashMap<Integer, Boolean> 			map_correct_noeud;
Boolean	oriented;

	
	public Graphe_matrice(Boolean oriented) {
		liste_noeud = new ArrayList<Noeud>();
		map_correct_noeud = new HashMap<Integer, Boolean>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new Matrice_Perso<HashSet<Integer>>();
		this.oriented = oriented;
		
	}
	
	public void ajouterSommet (Noeud n) {
		liste_noeud.add(n);
		map_correct_noeud.put(n.getId(), true);
		
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

		liste_noeud.remove((Noeud)n);
		
		for(int i=0; i < liste_noeud.size(); i++){
			matrice_adjacence.set(n.getId(), i, new HashSet<Integer>());
		}
	}
	
	public void ajouterArc (Arc a, Noeud nSource, Noeud nCible) {
		liste_arc.add(a);
		
		matrice_adjacence.get(nSource.getId(), nCible.getId()).add(a.getId());
		if (this.oriented == false)
		{matrice_adjacence.get(nCible.getId(), nSource.getId()).add(a.getId());}

		
	}
	
	public void supprimerArc (Arc a) {
		liste_arc.remove((Arc)a);
		
		for(int i=0; i < liste_noeud.size(); i++){
			for(int j=0; j < liste_noeud.size(); j++){
				if(matrice_adjacence.get(i, j).contains((Integer) a.getId())){
					matrice_adjacence.get(i, j).remove(a.getId());
				}
			}
		}
		
	}
	
	public Set<Noeud> getSuccesseurs (Noeud n) {
		
		Set<Noeud> rList = new HashSet<Noeud>();
		
		for (int i=0; i<this.matrice_adjacence.getNumCols(n.getId()); i++) {
			
			Set<Integer> sTemp = matrice_adjacence.get(n.getId(), i);
			
			if (!sTemp.isEmpty()) {
				rList.add(getNoeudFromId(i));
			}

			
		}

		return rList;
		
	}
	
	public Set<Arc> getArcsSortants(Noeud n) {

		Set<Arc> rList = new HashSet<Arc>();
	
		for (int i=0; i<this.matrice_adjacence.getNumCols(n.getId()); i++) {
			
			Set<Integer> sTemp = matrice_adjacence.get(n.getId(), i);
			
			Iterator<Integer> it = sTemp.iterator();
			while (it.hasNext()) {
				rList.add( getArcFromId( it.next() ) );
			}

			
		}
		
		return rList;
		
	}
	
	
	@Override
	public String toString() {
		return "Graphe_matrice \nliste_arc=" + liste_arc + "\nliste_noeud="
				+ liste_noeud + "\nmatrice_adjacence=" + matrice_adjacence;
	}
	
}
