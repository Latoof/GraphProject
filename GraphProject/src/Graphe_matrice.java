import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Graphe_matrice extends Graphe {

	
Matrice_Perso<HashSet<Integer>> 	matrice_adjacence;
Hashtable<Integer, Boolean>		 					noeud_actifs;
Boolean								oriented;

	
	public Graphe_matrice(Boolean oriented) {
		liste_noeud = new ArrayList<Noeud>();
		noeud_actifs = new Hashtable<Integer, Boolean>();
		liste_arc = new ArrayList<Arc>();
		matrice_adjacence = new Matrice_Perso<HashSet<Integer>>();
		this.oriented = oriented;
		
	}
	
	public void ajouterSommet (Noeud n) {
		liste_noeud.add(n);
		noeud_actifs.put(n.getId(), true);
		
		matrice_adjacence.Add(new HashSet<Integer>(), n.getId());

		this.resizeMatrice();
	}
	
	public void supprimerSommet (Noeud n) {

		liste_noeud.remove((Noeud)n);
		noeud_actifs.put(n.getId(), false);
		
		for(int i=0; i < liste_noeud.get(getNbNoeuds() - 1).getId(); i++){
			matrice_adjacence.set(n.getId(), i, new HashSet<Integer>());
		}
	}
	
	public void resizeMatrice (){

		int size = liste_noeud.get(getNbNoeuds() - 1).getId();
		
		for (int i=0; i < (size + 1); i++){
						
			while (matrice_adjacence.getNumCols(i) < size ){
				matrice_adjacence.Add(new HashSet<Integer>(), i);
			}
			
			if (noeud_actifs.get(i) == null){
				noeud_actifs.put(i, false);
			}
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
		
		for(int i=0; i < getNbNoeuds(); i++){
			for(int j=0; j < getNbNoeuds(); j++){
				if(matrice_adjacence.get(i, j).contains((Integer) a.getId())){
					matrice_adjacence.get(i, j).remove(a.getId());
				}
			}
		}
		
	}
	
	public Set<Noeud> getSuccesseurs (Noeud n) {
		
		Set<Noeud> rList = new HashSet<Noeud>();
		
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
		
		Set<Noeud> rList = new HashSet<Noeud>();
		
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
	
	Set<Noeud> rList = new HashSet<Noeud>();

	rList.addAll( getPredecesseurs(n) );
	rList.addAll( getSuccesseurs(n) );
	
	return rList;
	
}
	
	public Set<Arc> getArcsSortants(Noeud n) {

		Set<Arc> rList = new HashSet<Arc>();
	
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

		Set<Arc> rList = new HashSet<Arc>();
	
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
/*	
public List<Noeud> parcoursProfondeur( Noeud n1, Set<Noeud> liste ) {
		
		List<Noeud> rList = new ArrayList<Noeud>();
		
		if ( liste == null ) {
			liste = new HashSet<Noeud>();
		}
		
		liste.add(n1);
		
		Iterator<Noeud> it = getVoisins(n1).iterator();
		
		rList.add(n1);
		while ( it.hasNext() ) {
			
			Noeud nTemp = it.next();
			if ( !liste.contains(nTemp) ) {
				rList.addAll( parcoursProfondeur( nTemp, liste ) );
			}
						
		}
		
		return rList;
		
	}
*/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Graphe_matrice [\nmatrice_adjacence=" + matrice_adjacence
				+ "noeud_actifs=" + noeud_actifs + "\noriented=" + oriented
				+ "\nliste_arc=\n" + liste_arc + "\nliste_noeud=\n" + liste_noeud
				+ "]";
	}
	
}
