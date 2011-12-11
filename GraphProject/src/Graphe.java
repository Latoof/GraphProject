import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public abstract class Graphe {

	/**
	 * @uml.property  name="compteurNoeuds"
	 */
	int compteurNoeuds = 0;
	/**
	 * @uml.property  name="compteurArcs"
	 */
	int compteurArcs = 0;
	/**
	 * @uml.property  name="liste_noeud"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="Ville"
	 */
	ArrayList<Noeud>			liste_noeud;
	/**
	 * @uml.property  name="liste_arc"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="Route"
	 */
	ArrayList<Arc>				liste_arc;
	/**
	 * @uml.property  name="tableauCouleur"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer java.lang.Integer"
	 */
	Hashtable<Integer, Integer> tableauCouleur;
	/**
	 * @uml.property  name="tableauParent"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer java.lang.Integer"
	 */
	Hashtable<Integer, Integer>	tableauParent;
	/**
	 * @uml.property  name="tableauDebut"
	 */
	Hashtable<Integer, Integer> tableauDebut;
	/**
	 * @uml.property  name="tableauFin"
	 */
	Hashtable<Integer, Integer> tableauFin;
	/**
	 * @uml.property  name="tableauDistance"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer java.lang.Integer"
	 */
	Hashtable<Integer, Integer>	tableauDistance;
	/**
	 * @uml.property  name="temp"
	 */
	int 						temp;
	
	public Graphe() {
		liste_noeud = new ArrayList<Noeud>();
		liste_arc = new ArrayList<Arc>();		
	}
	
	
	public int ajouterNoeud (Noeud n) {
		
		if ( n.getId() == -1 ) {
			n.setId(compteurNoeuds++);
			liste_noeud.add(n);
			
			return n.getId();
		}
		
		System.out.println("Erreur lors de l'ajout du Noeud "+n.toString()+" -> Noeud deja utilise ! (id "+n.getId()+")");
		
		return -1;
	}
	
	public int supprimerNoeud (Noeud n) {
		
		if ( liste_noeud.contains(n) ) {
			
			int prevID = n.getId();

			liste_noeud.remove(n);
			n.setId(-1);
			
			return prevID;
		
		}
		
		return -1;
	}

	public abstract void ajouterArc(Arc a) ;
	
	public abstract void supprimerArc(Arc a) ;
	
	public Noeud getNoeudFromId(int id) {
		
		for (int i=0; i<liste_noeud.size(); i++) {
			
			if (liste_noeud.get(i).getId() == id) {
				return liste_noeud.get(i);
			}
			
		}
		
		return null;
	}
	
	public Arc getArcFromId(int id) {
		
		for (int i=0; i<liste_arc.size(); i++) {
			
			if (liste_arc.get(i).getId() == id) {
				return liste_arc.get(i);
			}
			
		}
		
		return new Arc(-1,-1, null, null);
		
	}
	
	public int getNbNoeuds() {
		return liste_noeud.size();
	}
	
	public int getNbArcs() {
		return liste_arc.size();
	}
	
	public void parcoursProfondeur(Noeud nStart, Boolean parcoursTot) {
		tableauCouleur = new Hashtable<Integer, Integer>();
		tableauParent = new Hashtable<Integer, Integer>();
		tableauDebut = new Hashtable<Integer, Integer>();
		tableauFin = new Hashtable<Integer, Integer>();
		
		System.out.println("Parcours en profondeur depuis le noeud " + nStart.getId());
		
		for(int i=0;(i<getNbNoeuds()+1);i++){
			tableauCouleur.put(i, 0);
			tableauParent.put(i, -1);
		}
		temp=0;
		tableauParent.put(nStart.getId(), nStart.getId());
		visiterProfondeur(nStart);
		
		if(parcoursTot){
			for(int i=0;i<getNbNoeuds();i++){
				if(tableauCouleur.get(i) == 0){
					visiterProfondeur(this.getNoeudFromId(i));
				}
			}
		}
	}
	
	public void visiterProfondeur(Noeud n) {
						
		tableauCouleur.put(n.getId(), 1);
		tableauDebut.put(n.getId(), temp);
		temp++;
		
		System.out.println("entrée : " + n.getId());
		
		Iterator<Noeud> it = getVoisins(n).iterator();
		
		while ( it.hasNext() ) {
			
			Noeud nTemp = it.next();
			if(tableauCouleur.get(nTemp.getId()) == 0){
				tableauParent.put(nTemp.getId(), n.getId());
				visiterProfondeur(nTemp);
			}
		}

		tableauCouleur.put(n.getId(), 2);
		System.out.println("sortie : " + n.getId());
		tableauFin.put(n.getId(), temp);
		temp++;
	}
	
	public void parcoursLargeur(Noeud nStart) {
		tableauDistance = new Hashtable<Integer, Integer>();
		tableauCouleur = new Hashtable<Integer, Integer>();
		tableauParent = new Hashtable<Integer, Integer>();
		tableauDebut = new Hashtable<Integer, Integer>();
		tableauFin = new Hashtable<Integer, Integer>();
		
		
		LinkedList<Noeud> file = new LinkedList<Noeud>();
		
		System.out.println("Parcours en largeur depuis le noeud " + nStart.getId());
		
		for(int i=0;i<(getNbNoeuds()+1);i++){
			tableauCouleur.put(i, 0);
			tableauParent.put(i, -1);
			tableauDistance.put(i, -1);
		}
		temp=0;
		tableauCouleur.put(nStart.getId(), 1);
		tableauParent.put(nStart.getId(), nStart.getId());
		tableauDistance.put(nStart.getId(), 0);
		
		file.addFirst(nStart);
		Noeud u;
		
		while(!file.isEmpty()){
			u=file.pollFirst();
			tableauDebut.put(u.getId(), temp);
			temp++;
			
			System.out.println("entrée : " + u.getId());
			
			Iterator<Noeud> it = getVoisins(u).iterator();
			
			while ( it.hasNext() ) {
				
				Noeud v = it.next();
				if(tableauCouleur.get(v.getId()) == 0){
					tableauCouleur.put(v.getId(), 1);
					tableauParent.put(v.getId(), u.getId());
					tableauDistance.put(v.getId(), (tableauDistance.get(u.getId()) + 1));
					file.addLast(v);
				}
			}

			tableauCouleur.put(u.getId(), 2);
			System.out.println("sortie : " + u.getId());
			tableauFin.put(u.getId(), temp);
			temp++;
		}
	}

	public abstract Set<Noeud> getVoisins(Noeud n);

	
	@Override
	public String toString() {
		
		return liste_noeud.toString() + "\n" + liste_arc.toString();
				
	}
	
}
