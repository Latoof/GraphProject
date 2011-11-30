import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;


public class Carte extends Graphe_matrice {

	double	distanceMax;
	int		interetMax;
	Hashtable<Integer, Double>	tableauDistanceKilo;

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
	
	public void genererItineraireAgregation(Ville vStart, double coeff){		
		if(this.methodeAgregation(vStart, coeff)){
			for(int i=0; i < getNbNoeuds()+1 ; i++){
				if(getVilleFromId(i).getId() != -1){
					System.out.println("Ville : " + getVilleFromId(i).getLabel());
					System.out.println("Parent : " + getVilleFromId(tableauParent.get(i)).getLabel());
					System.out.println("Rapport Distance/Interet depuis le point de départ : " + tableauDistanceKilo.get(i) + "\n");
				}
			}
		}
		else{
			System.out.println("Resultats non concluants :\n\t- Verifier la présence d'un circuit absorbant\n\t- Verifier que interet_max ou distance_max > 0\n");
		}
		
	}
	
	public boolean methodeAgregation (Ville vStart, double coeff) {
		distanceMax = 0;
		interetMax = 0;
		
		for(int i=0; i < this.getNbNoeuds(); i++){
			Ville v = (Ville)this.liste_noeud.get(i);
			if(interetMax < v.getInteret()){
				this.interetMax = v.getInteret();
			}
		}
		for(int j=0; j < this.getNbArcs(); j++){
			Route r = (Route)this.liste_arc.get(j);
			if(distanceMax < r.getPonderation()){
				this.distanceMax = r.getPonderation();
			}
			if(interetMax < r.getInteret()){
				this.interetMax = r.getInteret();
			}
		}
		
		System.out.println("interet max : " + interetMax);
		System.out.println("distance max : " + distanceMax);
		
		if(interetMax == 0 || distanceMax == 0){
			return false;
		}
		
		tableauParent = new Hashtable<Integer, Integer>();
		tableauDistanceKilo = new Hashtable<Integer, Double>();
		
		System.out.println("Generation d'un itineraire depuis la ville " + vStart.getLabel() + "\n");
		
		for(int i=0;i<getNbNoeuds()+1;i++){
			tableauDistanceKilo.put(i, Double.MAX_VALUE);
			tableauParent.put(i, -1);
		}
		
		tableauDistanceKilo.put(vStart.getId(), 0.0);
		tableauParent.put(vStart.getId(), vStart.getId());
		
		
		for(int i=0; i < (this.getNbNoeuds() - 1); i++){
			Iterator<Arc> it = this.liste_arc.iterator();
			while(it.hasNext()){
				Route r = (Route)it.next();
				if(tableauDistanceKilo.get(r.getNoeudCible().getId()) > (tableauDistanceKilo.get(r.getNoeudSource().getId()) + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, coeff))){
					tableauDistanceKilo.put(r.getNoeudCible().getId(), (tableauDistanceKilo.get(r.getNoeudSource().getId()) + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, coeff)));
					tableauParent.put(r.getNoeudCible().getId(), r.getNoeudSource().getId());
				}
			}
		}
		Iterator<Arc> iterat = this.liste_arc.iterator();
		while(iterat.hasNext()){
			Route r = (Route)iterat.next();
			if(tableauDistanceKilo.get(r.getNoeudCible().getId()) > (tableauDistanceKilo.get(r.getNoeudSource().getId()) + ponderationAgregation((Ville)r.getNoeudSource(), (Ville)r.getNoeudCible(), r, coeff))){
				return false;
			}
		}
		return true;
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
		String dotString = "digraph {";
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
