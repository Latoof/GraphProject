import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
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

	public void writeDotFile( String file ) throws IOException {
				
		//Création de l'objet
		String dotString = "";
		FileWriter fw = null;
		try {
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
}

public int loadFromDotFile(String cheminFichierDot) throws IOException {
	
	this.liste_noeud = new ArrayList<Noeud>();
	this.liste_arc = new ArrayList<Arc>();
	
	HashMap<String, Ville> table_correspondance = new HashMap<String, Ville>();
	
	
	String contenuGraphe = "";
	
	InputStream ips = null;
	try {
		ips = new FileInputStream(cheminFichierDot);
	} catch (FileNotFoundException e) {}
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		
		String ligne = "";
		boolean openB = false, closeB = false;
		
		while ( (ligne = br.readLine()) != null && openB == false ) {
			
			if ( ligne.contains("{") ) {
				openB = true;
			}
		
		int node_counter = 0, arc_counter = 0;
		if ( openB ) {
			
			while ( (ligne = br.readLine()) != null && closeB == false ) 
			
				if ( !ligne.isEmpty() ) {
					
					if ( ligne.contains("}") ) {
						closeB = true;
					}
					
					if ( !ligne.contains("->")) {
						
						String label_infos = ligne.split("label=\"")[1].split("\"")[0];
						System.out.println("Label-info : "+label_infos);
						
						// Infos dans le label ou separation des ce point la ? Dans tous les cas, les infos doivent etre identifiees des m
						// maintenant.
						
						String label = label_infos.split("\\(")[0];
						System.out.println("Label --> "+label);
						int interet = label_infos.split("\\(")[1].split("\\)")[0].length();
						
						Ville nouvelle_ville = new Ville( node_counter, label, interet);
						this.ajouterNoeud( nouvelle_ville );
						// Noeud
						
						System.out.println("adding ''"+label+"''");
						table_correspondance.put(label, nouvelle_ville);
						node_counter++;
					}
					else {
						
						String identifiant_noeuds = ligne.split("label=\"")[0].split("\\[")[0];
							System.out.println("Idn : "+identifiant_noeuds);
							String identifiant_noeud_source = identifiant_noeuds.split("->")[0];
							String identifiant_noeud_cible = identifiant_noeuds.split("->")[1];
							
							
							System.out.println(identifiant_noeud_source+"->"+identifiant_noeud_cible);
						String label_infos = ligne.split("label=\"")[1].split("\"")[0];
						
						// Infos dans le label ou separation des ce point la ? Dans tous les cas, les infos doivent etre identifiees des m
						// maintenant.
							
							String label = label_infos.split("\\(")[0];
							
							String infos = label_infos.split("\\(")[1].split("\\)")[0];
							int interet = infos.split(";")[1].length();
							float distance = Float.valueOf(infos.split(";")[0]);
						
							System.out.println("Ajout de "+ label + " avec interet "+interet+" et distance "+ distance);
							System.out.println(table_correspondance.get(identifiant_noeud_source).getId()+" ---  "+table_correspondance.get(identifiant_noeud_cible).getId());
						this.ajouterArc( new Route( arc_counter, label, distance, interet, 
											(Ville) table_correspondance.get(identifiant_noeud_source),
											(Ville) table_correspondance.get(identifiant_noeud_cible)) );
						// Noeud
						
						
						arc_counter++;
						// Transistion
					}
					
					
				}
	
			
		}
		
		br.close(); 
		System.out.println("--"+contenuGraphe+"--");

		if ( closeB ) {
			return 0;
		}
		else {
			return -1;
		}

	}
		return -2;
	
	}

}