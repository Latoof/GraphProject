import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public abstract class Graphe {

	ArrayList<Noeud>	liste_noeud;
	ArrayList<Arc>		liste_arc;
	int 				tableauCouleur[];
	int 				tableauParent[];
	int 				tableauDebut[];
	int 				tableauFin[];
	int 				temp;
	
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
	
	public void parcoursProfondeur(Noeud nStart) {
		tableauCouleur = new int[this.getNbNoeuds()];
		tableauParent = new int[this.getNbNoeuds()];
		tableauDebut = new int[this.getNbNoeuds()];
		tableauFin = new int[this.getNbNoeuds()];
		
		for(int i=0;i<getNbNoeuds();i++){
			tableauCouleur[i]=0;
			tableauParent[i]=-1;
		}
		temp=0;
		tableauParent[nStart.getId()]=nStart.getId();
		visiterProfondeur(nStart);
	}
	
	public void visiterProfondeur(Noeud n) {
		tableauCouleur[n.getId()]=1;
		tableauDebut[n.getId()]=temp;
		temp++;
		
		Iterator<Noeud> it = getVoisins(n).iterator();
		
		while ( it.hasNext() ) {
			
			if(tableauCouleur[it.next().getId()] == 0){
				tableauParent[it.next().getId()]=n.getId();
				visiterProfondeur(it.next());
			}
			tableauCouleur[n.getId()]=2;
			tableauFin[n.getId()]=temp;
			temp++;
		}
	}

	abstract Set<Noeud> getVoisins(Noeud n);

	@Override
	public String toString() {
		
		return liste_noeud.toString() + "\n" + liste_arc.toString();
				
	}
	
}
