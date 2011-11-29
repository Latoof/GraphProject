import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;


public class Carte extends Graphe_matrice {

	double	distanceMax;
	int		interetMax;
	double	tableauDistanceKilo[];

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
	
	public boolean genererItineraireAgregation (Ville vStart, double coeff) {
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
		tableauDistanceKilo = new double[this.getNbNoeuds()];
		
		System.out.println("Generation d'un itineraire depuis la ville " + vStart.getLabel() + "\n");
		
		for(int i=0;i<getNbNoeuds();i++){
			tableauDistanceKilo[i]=1000;
			tableauParent[i]=-1;
		}
		tableauDistanceKilo[vStart.getId()]=0;
		tableauParent[vStart.getId()]=vStart.getId();
		
		for(int i=1; i < this.getNbNoeuds(); i++){
			Iterator<Arc> it = this.liste_arc.iterator();
			while(it.hasNext()){
				Route r = (Route)it.next();
				if(tableauDistanceKilo[r.getNoeudCible().getId()] > (tableauDistanceKilo[r.getNoeudSource().getId()] + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, coeff))){
					tableauDistanceKilo[r.getNoeudCible().getId()] = (tableauDistanceKilo[r.getNoeudSource().getId()] + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, coeff));
					tableauParent[r.getNoeudCible().getId()] = r.getNoeudSource().getId();
				}
			}
		}
		
		for(int i=0; i < getNbNoeuds(); i++){
			System.out.println("Ville : " + getVilleFromId(i).getLabel());
			System.out.println("Parent : " + getVilleFromId(tableauParent[i]).getLabel());
			System.out.println("Distance depuis le point de départ : " + tableauDistanceKilo[i] + "\n");
		}
		
		Iterator<Arc> iterat = this.liste_arc.iterator();
		while(iterat.hasNext()){
			Route r = (Route)iterat.next();
			if(tableauDistanceKilo[r.getNoeudCible().getId()] > (tableauDistanceKilo[r.getNoeudSource().getId()] + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, coeff))){
				return false;
			}
		}
		
		return true;
		
		/*
		Ville u;
		
		while(!file.isEmpty()){
			u=file.pollFirst();
			
			Iterator<Arc> it = getArcsSortants(u).iterator();
			
			while ( it.hasNext() ) {
				
				Route r = (Route)it.next();
				
				if(tableauDistanceKilo[r.getNoeudCible().getId()] > (tableauDistanceKilo[r.getNoeudSource().getId()] + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, coeff))){
					tableauDistanceKilo[r.getNoeudCible().getId()] = (tableauDistanceKilo[r.getNoeudSource().getId()] + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, coeff));
					tableauParent[r.getNoeudCible().getId()] = r.getNoeudSource().getId();
				}
			}
		}
		
		for(int i=0; i < getNbNoeuds(); i++){
			System.out.println("Ville : " + getVilleFromId(i).getLabel());
			System.out.println("Parent : " + getVilleFromId(tableauParent[i]).getLabel());
			System.out.println("Distance depuis le point de départ : " + tableauDistanceKilo[i] + "\n");
		}
		
		System.out.println("END Bellman-Ford");
		*/
	}
	
	public double ponderationAgregation (Ville vStart, Ville vCible, Route route, double coeff) {
		double result = 0;
		
		result = coeff * route.getPonderation() / (this.distanceMax);
		result -= (1 - coeff) * (route.getInteret() + vCible.getInteret()) / (2 * interetMax );
		
//		result = route.getPonderation();
			
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
					
					else if ( !ligne.contains("->")) {
						
						String label_infos = ligne.split("label=\"")[1].split("\"")[0];
						System.out.println("Label-info : "+label_infos);
						
						// Infos dans le label ou separation des ce point la ? Dans tous les cas, les infos doivent etre identifiees des m
						// maintenant.
						
						String label = label_infos.split("\\(")[0];
						int interet = ( label_infos.split("\\(")[1].split("\\)").length > 0) ? label_infos.split("\\(")[1].split("\\)")[0].length() : 0;
						//System.out.println("Label --> "+label+" ;  Interet"+interet);

						Ville nouvelle_ville = new Ville( node_counter, label, interet);
						this.ajouterNoeud( nouvelle_ville );
						// Noeud
						
						System.out.println("adding ''"+label+"''");
						table_correspondance.put(label, nouvelle_ville);
						node_counter++;
					}
					else {
						
						String identifiant_noeuds = ligne.split("label=\"")[0].split(" \\[")[0];
							System.out.println("Idn : "+identifiant_noeuds);
							String identifiant_noeud_source = identifiant_noeuds.split("->")[0];
							String identifiant_noeud_cible = identifiant_noeuds.split("->")[1];
							
							
							System.out.println("''"+identifiant_noeud_source+"''->''"+identifiant_noeud_cible+"''");
						String label_infos = ligne.split("label=\"")[1].split("\"")[0];
						
						// Infos dans le label ou separation des ce point la ? Dans tous les cas, les infos doivent etre identifiees des m
						// maintenant.
							
							String label = label_infos.split("\\(")[0];
							
							String infos = label_infos.split("\\(")[1].split("\\)")[0];
							int interet = (infos.split(";").length > 1 ) ? infos.split(";")[1].length() : 0;
							float distance = Float.valueOf(infos.split(";")[0]);
						
							System.out.println("Ajout de "+ label + " avec interet "+interet+" et distance "+ distance);
							System.out.println(table_correspondance.get(identifiant_noeud_source).getId()+" ^^ ");
							System.out.println(" ^^ "+table_correspondance.get(identifiant_noeud_cible).getId());
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