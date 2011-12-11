import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Matthieu Lenogue - Maxime Ouairy
 */

public class Carte_m extends Graphe_matrice {

	/**
	 * @uml.property  name="distanceMax"
	 */
	double	distanceMax;
	/**
	 * @uml.property  name="interetMax"
	 */
	int		interetMax;
	/**
	 * @uml.property  name="tableauDistanceKilo"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer java.lang.Double"
	 */
	Hashtable<Integer, Double>	tableauDistanceKilo;
	/**
	 * @uml.property  name="itineraire"
	 */
	LinkedList<Route> 	itineraire;
	
	// A revoir. Beaucoup de choses a optimiser
	/**
	 * @uml.property  name="cheminLePlusCourtProfondeur"
	 */
	LinkedList<Route> cheminLePlusCourtProfondeur;
	/**
	 * @uml.property  name="interetCheminLePlusCourtProfondeur"
	 */
	int interetCheminLePlusCourtProfondeur;
	//

	public Carte_m() {
		super();
	}
	
	/**
	 * Retourne la ville correspondante Ã  l'id passÃ© en paramÃ¨tre
	 * @param l'identifiant de la ville
	 * @return la ville correspondant Ã  l'id
	 */
	public Ville getVilleFromId(int id) {			
		for (int i=0; i<liste_noeud.size(); i++) {				
			if (liste_noeud.get(i).getId() == id) {
				return (Ville)liste_noeud.get(i);
			}
		}			
		return null;
	}

	public Route getRouteFromId(int id) {		
		for (int i=0; i<liste_arc.size(); i++) {			
			if (liste_arc.get(i).getId() == id) {
				return (Route)liste_arc.get(i);
			}
		}
		return null;
	}
	
	public boolean plusCourtDijkstra(Ville vStart){
		tableauParent = new Hashtable<Integer, Integer>();
		tableauDistanceKilo = new Hashtable<Integer, Double>();

		LinkedList<Ville> file = new LinkedList<Ville>();

		for(int i=0;i<getNbNoeuds();i++){
			tableauDistanceKilo.put(i, Double.MAX_VALUE);
			tableauParent.put(i, -1);
			file.add(this.getVilleFromId(i));
		}
		tableauDistanceKilo.put(vStart.getId(), 0.0);
		tableauParent.put(vStart.getId(), vStart.getId());

		Ville u;
		while(!file.isEmpty()){
			u=file.pollFirst();

			Iterator<Arc> it = getArcsSortants(u).iterator();

			while ( it.hasNext() ) {

				Route r = (Route)it.next();

				if(tableauDistanceKilo.get(r.getNoeudCible().getId()) > (tableauDistanceKilo.get(r.getNoeudSource().getId()) + r.getPonderation())){
					tableauDistanceKilo.put(r.getNoeudCible().getId(), (tableauDistanceKilo.get(r.getNoeudSource().getId()) + r.getPonderation()));
					tableauParent.put(r.getNoeudCible().getId(), r.getNoeudSource().getId());
				}
			}
		}
		
		return true;
	}
	
	public LinkedList<Route> genererItineraireDetourBorne(Ville vStart, Ville vDest, double coeff){
		this.plusCourtDijkstra(vStart);
		double distanceDest = tableauDistanceKilo.get(vDest.getId());
		
		if(distanceDest == Double.MAX_VALUE){
			System.out.println("Itinéraire impossible à déterminer entre "+vStart.getNomVille()+" et "+vDest.getNomVille());
			return null;
		}
		System.out.println("\nMethode Detour Borné\nDistance jusqu'a " + vDest.getNomVille() + " depuis " + vStart.getNomVille() + " = " + distanceDest);
		double borneMax = distanceDest * coeff;
		System.out.println("Borne Max considÃ©rÃ©e (PondÃ©ration aggregation Ã  1) : " + borneMax);
		
		LinkedList<Route> itineraire = new LinkedList<Route>();
		itineraire = this.parcoursProfondeurVille(vStart, vDest, false, borneMax);

		if ( itineraire != null ) {
			Route r;
			double distanceTot = 0;
			while(itineraire.size() != 0){
				r = (Route)itineraire.removeFirst();
				distanceTot += r.getPonderation();
				System.out.println("On passe par la route : "+r.getIdentifiant()+" vers "+((Ville)r.getNoeudCible()).getNomVille());
				}
			System.out.println("Distance parcourue : "+distanceTot+" km");
			return itineraire;
		
		}
		else {
			System.out.println("Itinéraire impossible à déterminer, en tenant compte des paramètres enregistrés.");
			return null;
		}
	}

	public LinkedList<Route> parcoursProfondeurVille(Ville nStart, Ville nDest, Boolean parcoursTot, double borneMax) {
		tableauCouleur = new Hashtable<Integer, Integer>();
		tableauParent = new Hashtable<Integer, Integer>();
		LinkedList<Route> tableauParcours = new LinkedList<Route>();
				
		
		for( int i=0;(i<getNbNoeuds()+1);i++ ){
			tableauCouleur.put(i, 0);
			tableauParent.put(i, -1);
		}
		temp=0;
		tableauParent.put(nStart.getId(), nStart.getId());
		
		
		cheminLePlusCourtProfondeur = null; // On vide l'eventuel resultat precedent
		visiterProfondeur(nStart, nDest, borneMax, 0.0, 0, tableauParcours);
		
		if(parcoursTot){
			for(int i=0;i<getNbNoeuds();i++){
				if(tableauCouleur.get(i) == 0){
					visiterProfondeur(this.getVilleFromId(i));
				}
			}
		}
		
		return cheminLePlusCourtProfondeur;
	}
	
	public boolean visiterProfondeur(Ville n, Ville nDest, double borneMax, double distanceParcourue, int interet_total, LinkedList<Route> tabParcours) {
		
		tableauCouleur.put(n.getId(), 1);
		
		Set<Arc> set = getArcsSortants(n);
		Set<Route> setR = (Set)set;
		
		Iterator<Route> it = setR.iterator();
		while ( it.hasNext() ) {
			
			Route rTemp = it.next();
			
			if(tableauCouleur.get(rTemp.getNoeudCible().getId()) == 0 && (distanceParcourue + rTemp.getPonderation() <= borneMax)) {
				if(rTemp.getNoeudCible() != nDest){
					tableauCouleur.put(rTemp.getNoeudCible().getId(), 1);
					distanceParcourue += rTemp.getPonderation();
					interet_total += rTemp.getInteret() + ((Ville)rTemp.getNoeudCible()).getInteret();
					tabParcours.addLast( rTemp );
					visiterProfondeur((Ville)rTemp.getNoeudCible(), nDest, borneMax, distanceParcourue, interet_total, tabParcours);
				}
				else{
					tableauCouleur.put(rTemp.getNoeudCible().getId(), n.getId());
					distanceParcourue += rTemp.getPonderation();
					interet_total += rTemp.getInteret() + ((Ville)rTemp.getNoeudCible()).getInteret();
					tabParcours.addLast( rTemp ); // Derniere route, donc.

					// On a trouve un chemin acceptable
					
					// Si aucun chemin n'a ete trouve precedemment OU que le chemin precedent presente un interet moindre ...
					if ( cheminLePlusCourtProfondeur == null || interet_total > interetCheminLePlusCourtProfondeur  ) {
						// On ajoute celui en cours
						cheminLePlusCourtProfondeur = new LinkedList<Route>(tabParcours);
						interetCheminLePlusCourtProfondeur = interet_total;
					}
					
					System.out.println("\nDest atteinte\n");
					return true;
					// Stockage du meilleur chemin
					
				}
			}
		}

		tableauCouleur.put(n.getId(), 0);
		if ( !tabParcours.isEmpty() )
			tabParcours.removeLast();
		
		return false;
	}
	
	public void genererItineraireAgregation(Ville vStart, Ville vDest, double coeff){		
		if(this.methodeAgregation(vStart, coeff)){
			
			System.out.println("\nMethode Agregation");
			LinkedList<Integer> itineraire = new LinkedList<Integer>();
			int u = vDest.getId();
			while(u != vStart.getId()){
				itineraire.addFirst(tableauParent.get(u));
				u = tableauParent.get(u);
			}
			System.out.println("itineraire depuis "+vStart.getNomVille()+" vers "+vDest.getNomVille());
			int v;
			while(itineraire.size() != 0){
				v = itineraire.removeFirst();
				System.out.println("On passe par "+getVilleFromId(v).getNomVille());
			}
		}
		else{
			System.out.println("Resultats non concluants :\n\t- Verifier la prÃ©sence d'un circuit absorbant\n\t- Verifier que interet_max ou distance_max > 0\n");
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
				
		for(int i=0;i<getNbNoeuds();i++){
			tableauDistanceKilo.put(i, Double.MAX_VALUE);
			tableauParent.put(i, -1);
		}
		
		tableauDistanceKilo.put(vStart.getId(), 0.0);
		tableauParent.put(vStart.getId(), vStart.getId());
		
		// Algo de Bellmann Ford !!!
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
					
		return result;
	}

	public void writeDotFile( String file ) throws IOException {
				
		//CrÃ©ation de l'objet
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
			//On Ã©crit la chaÃ®ne
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
	
	public boolean loadFromString(String input) throws NumberFormatException, IOException {
		
		InputStreamReader rd = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
		return this.load(rd);		
	}
	
	public boolean loadFromDotFile(String cheminFichierDot) throws IOException {
		
		FileInputStream ips = null;
		try {
			ips = new FileInputStream(cheminFichierDot);
		} catch (FileNotFoundException e) {}
			InputStreamReader ipsr = new InputStreamReader(ips);

			if ( ipsr != null ) {
				return load(ipsr);
			}

			return false;
			
	}
	
	public boolean load(InputStreamReader rd) throws NumberFormatException, IOException {
		this.liste_noeud = new ArrayList<Noeud>();
		this.liste_arc = new ArrayList<Arc>();
		
		HashMap<String, Ville> table_correspondance = new HashMap<String, Ville>();
		
		
		String contenuGraphe = "";
		
		InputStream ips = null;

			BufferedReader br=new BufferedReader(rd);
			
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
//							System.out.println("Label-info : "+label_infos);
							
							// Infos dans le label ou separation des ce point la ? Dans tous les cas, les infos doivent etre identifiees des m
							// maintenant.
							
							String label = label_infos.split("\\(")[0];
							int interet = ( label_infos.split("\\(")[1].split("\\)").length > 0) ? label_infos.split("\\(")[1].split("\\)")[0].length() : 0;
							//System.out.println("Label --> "+label+" ;  Interet"+interet);
	
							Ville nouvelle_ville = new Ville( node_counter, label, interet);
							this.ajouterNoeud( nouvelle_ville );
							// Noeud
							
//							System.out.println("adding ''"+label+"''");
							table_correspondance.put(label, nouvelle_ville);
							node_counter++;
						}
						else {
							
							String identifiant_noeuds = ligne.split("label=\"")[0].split(" \\[")[0];
//								System.out.println("Idn : "+identifiant_noeuds);
								String identifiant_noeud_source = identifiant_noeuds.split("->")[0].replaceAll(" ", "");
								String identifiant_noeud_cible = identifiant_noeuds.split("->")[1].replaceAll(" ", "");
								
								
//								System.out.println("''"+identifiant_noeud_source+"''->''"+identifiant_noeud_cible+"''");
							String label_infos = ligne.split("label=\"")[1].split("\"")[0];
							
							// Infos dans le label ou separation des ce point la ? Dans tous les cas, les infos doivent etre identifiees des m
							// maintenant.
								
								String label = label_infos.split("\\(")[0];
								
								String infos = label_infos.split("\\(")[1].split("\\)")[0];
								int interet = (infos.split(";").length > 1 ) ? infos.split(";")[1].length() : 0;
								float distance = Float.valueOf(infos.split(";")[0]);
							
//								System.out.println("Ajout de "+ label + " avec interet "+interet+" et distance "+ distance);
//								System.out.println(table_correspondance.get(identifiant_noeud_source).getId()+" ^^ ");
//								System.out.println(" ^^ "+table_correspondance.get(identifiant_noeud_cible).getId());
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
//			System.out.println("--"+contenuGraphe+"--");
	
			if ( closeB ) {
				return true;
			}
			else {
				return false;
			}
	
		}
			return false;
		
	}
	
}
