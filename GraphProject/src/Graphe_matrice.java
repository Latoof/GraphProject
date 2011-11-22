import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class Graphe_matrice extends Graphe {

	
Matrice_Perso<HashSet<Integer>> 	matrice_adjacence;
Hashtable<Integer, Boolean>		 	noeud_actifs;

	
	public Graphe_matrice() {
		liste_noeud = new ArrayList<Noeud>();
		noeud_actifs = new Hashtable<Integer, Boolean>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new Matrice_Perso<HashSet<Integer>>();		
	}
	
	public void ajouterNoeud (Noeud n) {
		liste_noeud.add(n);
		noeud_actifs.put(n.getId(), true);
		
		matrice_adjacence.Add(new HashSet<Integer>(), n.getId());
		this.resizeMatrice();
	}
	
	public void supprimerNoeud (Noeud n) {

		liste_noeud.remove((Noeud)n);
		noeud_actifs.put(n.getId(), false);
		/*
		for(int i=0; i < (matrice_adjacence.getNumRows() - 1); i++){
			matrice_adjacence.set(n.getId(), i, new HashSet<Integer>());
			matrice_adjacence.set(i, n.getId(), new HashSet<Integer>());
		}
		*/
	}
	
	public void resizeMatrice (){

		int size = (matrice_adjacence.getNumRows() - 1);
		
		for (int i=0; i < (size + 1); i++){
						
			while (matrice_adjacence.getNumCols(i) <= size ){
				matrice_adjacence.Add(new HashSet<Integer>(), i);
			}
			
			if (noeud_actifs.get(i) == null){
				noeud_actifs.put(i, false);
			}
		}
	}
	
	public void ajouterArc (Arc a) {
		
		liste_arc.add(a);
		matrice_adjacence.get(a.getNoeudSource().getId(), a.getNoeudCible().getId()).add(a.getId());
		
	}
	
	public void supprimerArc (Arc a) {
		
		liste_arc.remove((Arc)a);
		matrice_adjacence.get(a.getNoeudSource().getId(), a.getNoeudCible().getId()).remove(a.getId());
		
	}
	
	public Set<Noeud> getSuccesseurs (Noeud n) {
		
		Set<Noeud> rList = new TreeSet<Noeud>();
		
		for (int i=0; i<this.matrice_adjacence.getNumCols(n.getId()); i++) {
			if(noeud_actifs.get(i)){
				Set<Integer> sTemp = matrice_adjacence.get(n.getId(), i);
				
				if (!sTemp.isEmpty()) {
					rList.add(getNoeudFromId(i));
				}
			}
		}
		return rList;
	}
	
public Set<Noeud> getPredecesseurs (Noeud n) {
		
		Set<Noeud> rList = new TreeSet<Noeud>();
		
		for (int i=0; i<this.matrice_adjacence.getNumCols(n.getId()); i++) {
			if(noeud_actifs.get(i)){
				Set<Integer> sTemp = matrice_adjacence.get(i, n.getId());
				
				if (!sTemp.isEmpty()) {
					rList.add(getNoeudFromId(i));
				}
			}
		}

		return rList;
		
	}

public Set<Noeud> getVoisins(Noeud n) {
	
	Set<Noeud> rList = new TreeSet<Noeud>();

	rList.addAll( getPredecesseurs(n) );
	rList.addAll( getSuccesseurs(n) );
	
	return rList;
	
}
	
	public Set<Arc> getArcsSortants(Noeud n) {

		Set<Arc> rList = new TreeSet<Arc>();
	
		for (int i=0; i<this.matrice_adjacence.getNumCols(n.getId()); i++) {
			if(noeud_actifs.get(i)){
				Set<Integer> sTemp = matrice_adjacence.get(n.getId(), i);
				
				Iterator<Integer> it = sTemp.iterator();
				while (it.hasNext()) {
					rList.add( getArcFromId( it.next() ) );
				}
			}
		}
		
		return rList;
		
	}
	
	public Set<Arc> getArcsEntrants(Noeud n) {

		Set<Arc> rList = new TreeSet<Arc>();
	
		for (int i=0; i<this.matrice_adjacence.getNumCols(n.getId()); i++) {
			if(noeud_actifs.get(i)){
				Set<Integer> sTemp = matrice_adjacence.get(i, n.getId());
				
				Iterator<Integer> it = sTemp.iterator();
				while (it.hasNext()) {
					rList.add( getArcFromId( it.next() ) );
				}
			}
		}
		
		return rList;
		
	}
	
	@Override
	public String toString() {
		return "Graphe_matrice [\nmatrice_adjacence=" + matrice_adjacence
				+ "noeud_actifs=" + noeud_actifs + "\nliste_arc=\n" + liste_arc + "\nliste_noeud=\n" + liste_noeud
				+ "]";
	}
	
}
