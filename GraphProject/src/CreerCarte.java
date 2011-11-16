
public class CreerCarte {

	/**
	 * @param args
	 */
	// java CreerCarte 10 0.15 g1.dot
	// dot g1.dot -Tpng carte1.png
	// java CreerCarte 20 0.20 g2.dot 0
	// java CreerCarte 30 0.40 g3.dot 0 10 2 20
	//}

	
	public static void main(String[] args) {
		int nbParam = args.length; 
		int i = 0;
		int graine;
		int max_interet_touristique;
		float min_distance;
		float max_distance;
		int nb_noeuds = Integer.valueOf(args[i++]);
		float densite = Float.valueOf(args[i++]);
		String nom_fichier = args[i++];
		
		if (nbParam == 3) {
			new CreationGraphe(nb_noeuds, densite, nom_fichier);
		}
		if (nbParam == 4) {
			graine = Integer.valueOf(args[i++]);
			new CreationGraphe(nb_noeuds, densite, nom_fichier, graine);
		}
		if (nbParam >= 6) {
			graine = Integer.valueOf(args[i++]);
			max_interet_touristique = Integer.valueOf(args[i++]);
			min_distance = Float.valueOf(args[i++]);
			max_distance = Float.valueOf(args[i]);
			new CreationGraphe(nb_noeuds, densite, nom_fichier, graine, max_interet_touristique, min_distance, max_distance);
		}
	}
	
}
