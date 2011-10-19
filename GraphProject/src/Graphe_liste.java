import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Graphe_liste extends Graphe {
	
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
		
		/** Suppression des Noeuds voisins posterieurs **/
		for (int i=0; i<liste_adjacence.size(); i++) {
			
			if (liste_adjacence.get(i).get(0).get(0) == n.getId() ) 
				liste_adjacence.remove(i);
			
		}
		/************************************/

		
		
		/** Suppression des arcs allant vers ce Noeud **/
		for (int i=0; i<liste_adjacence.size(); i++) {
			
			ListIterator<ArrayList<Integer>> itL = liste_adjacence.get(i).listIterator();
			while ( itL.hasNext() ) {
				
				ArrayList<Integer> aTemp = itL.next();
				
				if ( aTemp.get(0) == n.getId() ) {
					
					for (int j=1; j<aTemp.size(); j++) {
						liste_arc.remove( getArcFromId( aTemp.get(j) ) );
						itL.remove();
						
					}
				}
				
			}

		}
		/************************************/

		
		liste_noeud.remove(n);
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
	
	/** A OPTIMISER si les arcs connaissent un jour leurs noms provenance/destination (car la on parcourt tout) **/
	public void supprimerArc(Arc a) {
		
		for (int i=0; i<liste_adjacence.size(); i++) {
			
			ListIterator<ArrayList<Integer>> itL = liste_adjacence.get(i).listIterator();
			
			if ( itL.hasNext() ) {
				itL.next();
			}
			
			while ( itL.hasNext() ) {
				
				ArrayList<Integer> aTemp = itL.next();
				
				for (int j=1; j<aTemp.size(); j++) {
					
					if ( aTemp.get(j) == a.getId() ) {
						
							aTemp.remove(j);
							
					}
				}
				if ( aTemp.size() <= 1 ) {
					itL.remove();
				}
				
			}
				
		}

		liste_arc.remove( a.getId() );
		
	}
	
	public ArrayList<Noeud> getSuccesseurs(Noeud n) {
		
		ArrayList<Noeud> rList = new ArrayList<Noeud>();
		
		for (int i=0; i<liste_adjacence.size(); i++) {
			
			if ( liste_adjacence.get(i).get(0).get(0) == n.getId() ) {
				
				ListIterator<ArrayList<Integer>> it = liste_adjacence.get(i).listIterator();
				
				if ( it.hasNext() ) {
					it.next();
				}
				
				while ( it.hasNext() ) {
					rList.add( 
							getNoeudFromId( it.next().get(0) ) 
							);
				}
				
				
				i = liste_adjacence.size(); // Pour stopper la boucle
			}
			
		}
		
		
		return rList;
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
		
		return liste_noeud.toString() + "\n" + liste_arc.toString() + "\nTransitions : \n" + strAdj;

	}

}