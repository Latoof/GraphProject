import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


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
		graphList.add(new ArrayList<Integer>());
		graphList.get(0).add( n.getId() );
		
		liste_adjacence.add( graphList );
	}
	
	public void supprimerNoeud (Noeud n) {
		liste_noeud.remove(n);
	}
	
	public Noeud getNoeudFromId(int id) {
		ListIterator<Noeud> it = this.liste_noeud.listIterator();
	
		Noeud ntemp;
		while ( it.hasNext() ) {
			
			ntemp = it.next();
			if ( ntemp.getId() == id ) {
				return ntemp;
			}
			
		}
		
		return new Noeud(-1,"");
	}
	
	public Arc getArcFromId(int id) {
		
		ListIterator<Arc> it = this.liste_arc.listIterator();
		
		Arc atemp;
		while ( it.hasNext() ) {
			
			atemp = it.next();
			if ( atemp.getId() == id ) {
				return atemp;
			}
			
		}
		
		return new Arc(-1,"",-1);
		
		
	}
	
	public void ajouterArc(Arc a, Noeud n1, Noeud n2) {
		
		liste_arc.add(a);
		
		ListIterator<ArrayList<Integer>> it = liste_adjacence.get( n1.getId() ).listIterator();
		
		boolean alreadyPresent = false;
		ArrayList<Integer> ltemp;
		while ( it.hasNext() && !alreadyPresent ) {
			
			ltemp = it.next();
			
			/** Cas ou une transition du noeud n1 vers le noeud n2 n'existe deja */
			if ( ltemp.get(0) == n2.getId() ) {
				alreadyPresent = true;
				ltemp.add( a.getId() );
			}
			/******************************/

		}
		
		if ( !alreadyPresent ) {
			/** Cas ou aucune transition du noeud n1 vers le noeud n2 n'existe encore */
			ArrayList<Integer> transList = new ArrayList<Integer>();
			transList.add( n2.getId() );
			transList.add( a.getId() );
			
			liste_adjacence.get( n1.getId() ).add(transList);
			/******************************/
		}
	}
	
	@Override
	public String toString() {
		
		String strAdj = "";
		for (int i=0; i<liste_adjacence.size(); i++) {
			
			ListIterator<ArrayList<Integer>> itL = liste_adjacence.get(i).listIterator();
			while ( itL.hasNext() ) {
				ArrayList<Integer> aTemp = itL.next();
				
				
				strAdj += "[N" + aTemp.get(0) + "|";
				
				for (int j=1; j<aTemp.size(); j++) {
					strAdj += "a" + aTemp.get(j);
				}
				
				strAdj += "]-->";
				
			}
			strAdj += "\n";

		}
		
		return liste_noeud.toString() + "\n" + liste_arc.toString() + "\nTransitions : \n"+strAdj;
				
	}
	
	
}
