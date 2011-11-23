import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public abstract class Graphe {

	ArrayList<Noeud>	liste_noeud;
	ArrayList<Arc>		liste_arc;
	int 				tableauCouleur[];
	int 				tableauParent[];
	int 				tableauDebut[];
	int 				tableauFin[];
	int					tableauDistance[];
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

	public void ajouterArc(Arc a) {
		
		liste_arc.add(a);
		
	}
	
	public void supprimerArc(Arc a) {
		
		liste_arc.remove( a.getId() );
		
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
		
		return new Arc(-1,"",-1, null, null);
		
	}
	
	public int getNbNoeuds() {
		return liste_noeud.size();
	}
	
	public int getNbArcs() {
		return liste_arc.size();
	}
	
	public void parcoursProfondeur(Noeud nStart, Boolean parcoursTot) {
		tableauCouleur = new int[this.getNbNoeuds()];
		tableauParent = new int[this.getNbNoeuds()];
		tableauDebut = new int[this.getNbNoeuds()];
		tableauFin = new int[this.getNbNoeuds()];
		
		System.out.println("Parcours en profondeur depuis le noeud " + nStart.getLabel());
		
		for(int i=0;i<getNbNoeuds();i++){
			tableauCouleur[i]=0;
			tableauParent[i]=-1;
		}
		temp=0;
		tableauParent[nStart.getId()]=nStart.getId();
		visiterProfondeur(nStart);
		
		if(parcoursTot){
			for(int i=0;i<getNbNoeuds();i++){
				if(tableauCouleur[i] == 0){
					visiterProfondeur(this.getNoeudFromId(i));
				}
			}
		}
	}
	
	public void visiterProfondeur(Noeud n) {
						
		tableauCouleur[n.getId()]=1;
		tableauDebut[n.getId()]=temp;
		temp++;
		
		System.out.println("entrée : " + n.getLabel());
		
		Iterator<Noeud> it = getVoisins(n).iterator();
		
		while ( it.hasNext() ) {
			
			Noeud nTemp = it.next();
			if(tableauCouleur[ nTemp.getId() ] == 0){
				tableauParent[ nTemp.getId() ] = n.getId();
				visiterProfondeur(nTemp);
			}
		}
		tableauCouleur[ n.getId() ]=2;
		System.out.println("sortie : " + n.getLabel());
		tableauFin[ n.getId() ]= temp;
		temp++;
	}
	
	public void parcoursLargeur(Noeud nStart) {
		tableauDistance = new int[this.getNbNoeuds()];
		tableauCouleur = new int[this.getNbNoeuds()];
		tableauParent = new int[this.getNbNoeuds()];
		tableauDebut = new int[this.getNbNoeuds()];
		tableauFin = new int[this.getNbNoeuds()];
		
		
		LinkedList<Noeud> file = new LinkedList<Noeud>();
		
		System.out.println("Parcours en largeur depuis le noeud " + nStart.getLabel());
		
		for(int i=0;i<getNbNoeuds();i++){
			tableauCouleur[i]=0;
			tableauParent[i]=-1;
			tableauDistance[i]=-1;
		}
		temp=0;
		tableauCouleur[nStart.getId()]=1;
		tableauParent[nStart.getId()]=nStart.getId();
		tableauDistance[nStart.getId()]=0;
		
		file.addFirst(nStart);
		Noeud u;
		
		while(!file.isEmpty()){
			u=file.pollFirst();
			tableauDebut[u.getId()]=temp;
			temp++;
			
			System.out.println("entrée : " + u.getLabel());
			
			Iterator<Noeud> it = getVoisins(u).iterator();
			
			while ( it.hasNext() ) {
				
				Noeud v = it.next();
				if(tableauCouleur[ v.getId() ] == 0){
					tableauCouleur[ v.getId() ] = 1;
					tableauParent[ v.getId() ] = u.getId();
					tableauDistance[ v.getId() ] = (tableauDistance[ u.getId() ] + 1);
					file.addLast(v);
				}
			}
			tableauCouleur[ u.getId() ]=2;
			System.out.println("sortie : " + u.getLabel());
			tableauFin[ u.getId() ]=temp;
			temp++;
		}
	}

	public Set<Noeud> getVoisins(Noeud n){
		return null;
	}

	
	@Override
	public String toString() {
		
		return liste_noeud.toString() + "\n" + liste_arc.toString();
				
	}
	
}
