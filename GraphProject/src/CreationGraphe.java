/**
 * 
 */
import java.io.*;
/**
 * @author Chantal Enguehard, Christophe Jermann
 *
 */
public class CreationGraphe {
	private int nb_noeuds;
	private int nb_arcs;
	private float densite; //nombre d'arcs / (nombre de noeuds)^2
	private String nom_fichier;
	private int max_interet_touristique;
	private float min_distance;
	private float max_distance;


	CreationGraphe(int nb_noeuds, float densite, String nom_fichier, int graine) {
		this.nb_noeuds = nb_noeuds;
		this.densite = densite;
		this.nom_fichier = nom_fichier;
		nb_arcs = Math.round(densite * nb_noeuds * nb_noeuds);
		max_interet_touristique = 5;
		min_distance = 5;
		max_distance = 100;
		genere(graine);
	}
	
	CreationGraphe(int nb_noeuds, float densite, String nom_fichier) {
		this.nb_noeuds = nb_noeuds;
		this.densite = densite;
		this.nom_fichier = nom_fichier;
		nb_arcs = Math.round(densite * nb_noeuds * nb_noeuds);
		max_interet_touristique = 5;
		min_distance = 5;
		max_distance = 100;
		genere(0);
	}

	
	CreationGraphe(int nb_noeuds, float densite, String nom_fichier, int graine, int max_interet_touristique, float min_distance, float max_distance) {
		this.nb_noeuds = nb_noeuds;
		this.densite = densite;
		this.nom_fichier = nom_fichier;
		this.max_interet_touristique = max_interet_touristique;
		this.min_distance = min_distance;
		this.max_distance = max_distance;

		nb_arcs = Math.round(densite * nb_noeuds * nb_noeuds);
		max_interet_touristique = 5;
		min_distance = 5;
		max_distance = 100;
		genere(graine);
	}

	
	private String genere_etoiles(int nb) {
		int i;
		String etoiles = "";
		for (i = 1; i <= nb; i++) {
			etoiles += "*";
		}
		return(etoiles);
	}
	
	private String noeudToString(int label_noeud, int it) {
		String etoiles = genere_etoiles(it);

		String noeud = label_noeud + " [label=\"" + label_noeud  + "(" + etoiles + ")\"]\n";
		return(noeud);
	}

		
	private String arcToString(int label_arc, int debut, int arrivee, float d, int it) {
		String distance = "" + d;
		String etoiles = genere_etoiles(it);

		distance = distance.substring(0, distance.lastIndexOf(".") + 2);

		String arc = debut + "->" + arrivee  + " [label=\"" + label_arc + "(" + distance + ";" + etoiles + ")\"]\n";
		return(arc);
	}

	private void genere(int graine) {
		int debut, arrivee, label_arc, label_noeud;
		String arc, noeud;
		
		

		int it;
		float dist;

		java.util.Random nb = new java.util.Random(graine) ; 
		
		try {
			FileWriter fw = new FileWriter(nom_fichier);
			System.out.println("densite = " + densite);
			System.out.println("nb_noeuds = " + nb_noeuds);
			System.out.println("nb_arcs = " + nb_arcs);
			System.out.println("max_interet_touristique = " + max_interet_touristique);
			System.out.println("min_distance = " + min_distance);
			System.out.println("max_distance = " + max_distance);

			fw.write("digraph {\n");
			
			// les noeuds
			for (label_noeud = 1; label_noeud <= nb_noeuds; label_noeud++) {
				it = nb.nextInt(max_interet_touristique + 1);
				noeud = noeudToString(label_noeud, it);
				fw.write(noeud);

			}
			
			// un chemin parcourant tous les noeuds pour avoir un graphe connexe
			for (label_arc = 1; label_arc < nb_noeuds; label_arc++) {
				// noeud arrivee
				arrivee = label_arc + 1;
				
				// distance : [min_distance,  max_distance]
				dist = nb.nextFloat() * (max_distance - min_distance) + min_distance;
				
				// interet touristique : [0, max_interet_touristique]
				it = nb.nextInt(max_interet_touristique + 1);

				// ecriture
				arc = arcToString(label_arc, label_arc, arrivee, dist, it);
				fw.write(arc);
			}
			// un arc liant le dernier au premier noeud pour qu'il y ait toujours un chemin entre deux noeuds
			dist = nb.nextFloat() * (max_distance - min_distance) + min_distance;

			it = nb.nextInt(max_interet_touristique + 1);
			arc = arcToString(nb_noeuds, nb_noeuds, 1, dist, it);
			fw.write(arc);

			
			// completer les arcs
			for (label_arc = nb_noeuds+1 ; label_arc <= nb_arcs; label_arc++) {
				// noeud debut
				debut = 1 + nb.nextInt(nb_noeuds);

				// noeud arrivee
				arrivee = 1 + nb.nextInt(nb_noeuds);
							// arrivee et debut sont differents
				while (arrivee == debut) {
					arrivee = 1 + nb.nextInt(nb_noeuds);
				}
				// distance : [min_distance,  max_distance]
				dist = nb.nextFloat() * (max_distance - min_distance) + min_distance;

				// interet touristique : [0, max_interet_touristique]
				it = nb.nextInt(max_interet_touristique + 1);
				// ecriture
				arc = arcToString(label_arc, debut, arrivee, dist, it);
				fw.write(arc);
				
			}


			fw.write("}\n");
			fw.close();
		 } catch (IOException e) {
				 System.out.println("Erreur dans l'ecriture du fichier " + nom_fichier);
	        	}
	}

}