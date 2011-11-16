import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

public class Graphe_liste extends Graphe {
	
	ArrayList<
		LinkedList< 
			ArrayList<
				Integer
			> 
		>
	> liste_adjacence;
	
	
	public Graphe_liste() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();
		
		liste_adjacence = new ArrayList< 
								LinkedList<
									ArrayList<
										Integer
										>
									> 
								>();
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
	
	public Set<Noeud> getPredecesseurs(Noeud n) {
		
		Set<Noeud> rList = new HashSet<Noeud>();
		
		for (int i=0; i<liste_adjacence.size(); i++) {
			
			ListIterator<ArrayList<Integer>> itL = liste_adjacence.get(i).listIterator();
			
			if ( itL.hasNext() ) {
			//	itL.next();
			}
			
			while ( itL.hasNext() ) {
				
				if ( itL.next().get(0) == n.getId() ) {
					rList.add( getNoeudFromId(liste_adjacence.get(i).getFirst().get(0)) ); // On prend le Noeud de provenance
					break;
				}
				
			}
				
		}

		return rList;
	}
	
	public Set<Noeud> getSuccesseurs(Noeud n) {
		
		Set<Noeud> rList = new HashSet<Noeud>();
		
		for (int i=0; i<liste_adjacence.size(); i++) {
			
			if ( liste_adjacence.get(i).get(0).get(0) == n.getId() ) {
				
				ListIterator<ArrayList<Integer>> it = liste_adjacence.get(i).listIterator();
				
				if ( it.hasNext() ) {
				//	it.next();
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
	
	public Set<Noeud> getVoisins( Noeud n ) {
		
		Set<Noeud> rList = new HashSet<Noeud>();

		rList.addAll( getPredecesseurs(n) );
		rList.addAll( getSuccesseurs(n) );
		
		return rList;
		
	}
/*
	public List<Noeud> parcoursProfondeur( Noeud n1, Set<Noeud> liste ) {
		
		List<Noeud> rList = new LinkedList<Noeud>();
		
		if ( liste == null ) {
			liste = new HashSet<Noeud>();
		}
		
		liste.add( n1 );
		
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
	/*
	public void ajouterArcOld(Arc a, Noeud n1, Noeud n2) {
		
		
		liste_arc.add(a);
		
		ListIterator<ArrayList<Integer>> it = liste_adjacence.get( n1.getId() ).listIterator();
		
		boolean alreadyPresent = false;
		ArrayList<Integer> ltemp;
		while ( it.hasNext() && !alreadyPresent ) {
			
			ltemp = it.next();
			
			// Cas ou une transition du noeud n1 vers le noeud n2 n'existe deja
			if ( ltemp.get(0) == n2.getId() ) {
				alreadyPresent = true;
				ltemp.add( a.getId() );
			}
			

		}
		
		if ( !alreadyPresent ) {
			// Cas ou aucune transition du noeud n1 vers le noeud n2 n'existe encore
			ArrayList<Integer> transList = new ArrayList<Integer>();
			transList.add( n2.getId() );
			transList.add( a.getId() );
			
			liste_adjacence.get( n1.getId() ).add(transList);
		}
		
		
	}*/
	
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
		
		a.setNoeudSource(n1);
		a.setNoeudCible(n2);
		
		
	}
	
	/** A OPTIMISER si les arcs connaissent un jour leurs noms provenance/destination (car la on parcourt tout) **/
	public void supprimerArcOld(Arc a) {
		
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
	
	public void supprimerArc(Arc a) {

		Noeud nSource = a.getNoeudSource();
		
		ListIterator<ArrayList<Integer>> itL = liste_adjacence.get(nSource.getId()).listIterator();
		
		if ( itL.hasNext() ) {
			itL.next();
		}
		
		while ( itL.hasNext() ) {
			
			ArrayList<Integer> aTemp = itL.next();
			
			for (int j=1; j<aTemp.size(); j++) {
				
				if ( aTemp.get(j) == a.getId() ) {
						aTemp.remove(j);
						j = aTemp.size(); // Sortie de boucle
				}
			}
			if ( aTemp.size() <= 1 ) {
				itL.remove();
			}
			
		}
			
		liste_arc.remove( a.getId() );
		
	}
	
	public ArrayList<Arc> getArcsEntrants(Noeud n) {
		
		ArrayList<Arc> rList = new ArrayList<Arc>();
		
		for (int i=0; i<liste_adjacence.size(); i++) {
			
			ListIterator<ArrayList<Integer>> itL = liste_adjacence.get(i).listIterator();
			
			if ( itL.hasNext() ) {
			//	itL.next();
			}
			
			while ( itL.hasNext() ) {
				
				ArrayList<Integer> lTemp = itL.next();
				
				if ( lTemp.get(0) == n.getId() ) {
					
					for (int j=1; j<lTemp.size(); j++) {
						rList.add( getArcFromId( lTemp.get(j)) );
					}
				}
				
			}
				
		}

		return rList;
	}
	
	public Set<Arc> getArcsSortants(Noeud n) {
		
		Set<Arc> rList = new HashSet<Arc>();
		
		for (int i=0; i<liste_adjacence.size(); i++) {
			
			if ( liste_adjacence.get(i).get(0).get(0) == n.getId() ) {
				
				ListIterator<ArrayList<Integer>> it = liste_adjacence.get(i).listIterator();
				
				if ( it.hasNext() ) {
				//	it.next();
				}
				
				while ( it.hasNext() ) {
					
					ArrayList<Integer> lTemp = it.next();
					for (int j=1; j<lTemp.size(); j++) {
						rList.add( getArcFromId(lTemp.get(j)) );
					}

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
