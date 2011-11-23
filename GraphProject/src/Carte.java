import java.io.*;
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
		
		//this.ponderationAgregation(vStart, vCible, route, coeff)
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
