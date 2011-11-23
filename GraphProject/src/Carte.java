import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;


public class Carte extends Graphe_matrice {

	double	distanceMax;
	int		interetMax;

	public Carte() {
		super();
	}
	
	public Ville getVilleFromId(int id) {			
			for (int i=0; i<liste_noeud.size(); i++) {				
				if (liste_noeud.get(i).getId() == id) {
					return (Ville)liste_noeud.get(i);
				}
			}			
			return new Ville(-1,"", -1);
	}

	public Route getRouteFromId(int id) {		
		for (int i=0; i<liste_arc.size(); i++) {			
			if (liste_arc.get(i).getId() == id) {
				return (Route)liste_arc.get(i);
			}
		}
		return new Route(-1,"",-1, -1, null, null);
	}
	
	public void genererItineraireAgregation (Ville vStart) {
		distanceMax = 0;
		interetMax = 0;
		
		for(int i=0; i < this.getNbNoeuds(); i++){
			if(interetMax < this.getVilleFromId(i).getInteret()){
				this.interetMax = this.getVilleFromId(i).getInteret();
			}
		}
		for(int j=0; j < this.getNbArcs(); j++){
			if(distanceMax < this.getRouteFromId(j).getPonderation()){
				this.distanceMax = this.getRouteFromId(j).getPonderation();
			}
			if(interetMax < this.getRouteFromId(j).getInteret()){
				this.interetMax = this.getRouteFromId(j).getInteret();
			}
		}
		
		tableauParent = new int[this.getNbNoeuds()];
		tableauDistance = new int[this.getNbNoeuds()];
		
		
		LinkedList<Ville> file = new LinkedList<Ville>();
		
		System.out.println("Parcours en largeur depuis le noeud " + vStart.getLabel());
		
		for(int i=0;i<getNbNoeuds();i++){
			tableauDistance[i]=-1;
			tableauParent[i]=-1;
			file.add(this.getVilleFromId(i));
		}
		tableauDistance[vStart.getId()]=0;
		tableauParent[vStart.getId()]=vStart.getId();
		
		Ville u;
		
		while(!file.isEmpty()){
			u=file.pollFirst();
			
			Iterator<Arc> it = getArcsSortants(u).iterator();
			
			while ( it.hasNext() ) {
				
				Route r = (Route)it.next();
				if(tableauDistance[r.getNoeudCible().getId()] > (tableauDistance[r.getNoeudSource().getId()] + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, 0.5))){
					tableauDistance[r.getNoeudCible().getId()] = (int)(tableauDistance[r.getNoeudSource().getId()] + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, 0.5));
					tableauParent[r.getNoeudCible().getId()] = r.getNoeudSource().getId();
				}
			}
		}
	}
	
	public double ponderationAgregation (Ville vStart, Ville vCible, Route route, double coeff) {
		double result = 0;
		result = coeff * route.getPonderation() / (this.distanceMax);
		result -= (1 - coeff) * (route.getInteret() + vCible.getInteret()) / (2 * interetMax );
		return result;
	}

	public void writeDotFile(){
		
		File file = new File("./graph.dot");
		FileWriter fw;		
		
		String dotString = "digraph {\n";
		
		try {
			//Création de l'objet
			fw = new FileWriter(file);
			for(int i=0; i<liste_noeud.size();i++){
				Ville v = (Ville)liste_noeud.get(i);
				dotString += v.toDotLine();
			}
			for(int i=0;i<liste_arc.size();i++){
				Route r = (Route)liste_arc.get(i);
				dotString += r.toDotLine();
			}

			dotString += "}";
			//On écrit la chaîne
			fw.write(dotString);
			//On ferme le flux
			fw.close();
			//affichage
			//System.out.println(dotString);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
