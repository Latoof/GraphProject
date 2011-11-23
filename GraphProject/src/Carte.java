import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Carte extends Graphe_matrice {

	//ArrayList	listeRoutes, listeVilles;

	public Carte() {
		super();
	}
	
	public void getCheminsOptAgreg (Ville vStart) {
		
	}
	
	public int loadFromDotFile(String cheminFichierDot) throws IOException {
		
		this.liste_noeud = new ArrayList<Noeud>();
		this.liste_arc = new ArrayList<Arc>();
		
		HashMap<String, Ville> table_correspondance = new HashMap<String, Ville>();
		
		
		String contenuGraphe = "";
		
		InputStream ips = null;
		try {
			ips = new FileInputStream(cheminFichierDot);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		
		String ligne = "";
		boolean openB = false, closeB = false;
		
		while ( (ligne = br.readLine()) != null && openB == false ) {
			
			if ( ligne.contains("{") ) {
				openB = true;
			}
			
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
	
}
