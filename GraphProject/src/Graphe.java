import java.util.ArrayList;


public class Graphe {

	ArrayList<Noeud> liste_noeud;
	ArrayList<Arc>	liste_arc;
	
	public Graphe() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();		
	}
	
	public void ajouterNoeud (Noeud n) {
		
		liste_noeud.add(n);

	}
	
	public void supprimerNoeud (Noeud n) {
		
		liste_noeud.remove(n);
	}

	
	public Noeud getNoeudFromId(int id) {
		
		for (int i=0; i<liste_noeud.size(); i++) {
			
			if (liste_noeud.get(i).getId() == id) {
				return liste_noeud.get(i);
			}
			
		}
		
		return new Noeud(-1,"");
	}
	
	public Arc getArcFromId(int id) {
		
		for (int i=0; i<liste_arc.size(); i++) {
			
			if (liste_arc.get(i).getId() == id) {
				return liste_arc.get(i);
			}
			
		}
		
		return new Arc(-1,"",-1);
		
	}
	

	
	public void ajouterArc(Arc a, Noeud n1, Noeud n2) {
		
		liste_arc.add(a);
		
	}
	
	/** A OPTIMISER si les arcs connaissent un jour leurs noms provenance/destination (car la on parcourt tout) **/
	public void supprimerArc(Arc a) {
		
		liste_arc.remove( a.getId() );
		
	}
	
	public int getNbNoeuds() {
		return liste_noeud.size();
	}
	
	public int getNbArcs() {
		return liste_arc.size();
	}
	
	@Override
	public String toString() {
		
		return liste_noeud.toString() + "\n" + liste_arc.toString();
				
	}
	
}
