import java.io.IOException;
import java.util.TreeSet;


public class AppliTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		int t=0; // A modifier pour changer le test (je m'en sers aussi)
		if (t == 0 ) {
			Graphe_matrice graphe = new Graphe_matrice();
			
			Noeud n0 = new Noeud(0, "1");
			Noeud n1 = new Noeud(1, "2");
			Noeud n2 = new Noeud(2, "3");
			Noeud n3 = new Noeud(3, "4");
			Noeud n4 = new Noeud(4, "5");
			Noeud n5 = new Noeud(5, "6");

			graphe.ajouterNoeud(n0);			
			graphe.ajouterNoeud(n1);
			graphe.ajouterNoeud(n2);
			graphe.ajouterNoeud(n3);
			graphe.ajouterNoeud(n4);
			graphe.ajouterNoeud(n5);
			
			Arc a0 = new Arc(0, "a", 0, n0, n0);
			Arc a1 = new Arc(1, "b", 0, n0, n1);
			Arc a2 = new Arc(2, "c", 0, n0, n3);
			Arc a3 = new Arc(3, "d", 0, n1, n3);
			Arc a4 = new Arc(4, "e", 0, n1, n2);
			Arc a5 = new Arc(5, "f", 0, n2, n1);
			Arc a6 = new Arc(6, "g", 0, n1, n4);
			Arc a7 = new Arc(7, "h", 0, n4, n5);
			
			Arc a10 = new Arc(10, "z", 0, n0, n0);
			Arc a11 = new Arc(11, "y", 0, n1, n0);
			Arc a12 = new Arc(12, "w", 0, n3, n0);
			Arc a13 = new Arc(13, "v", 0, n3, n1);
			Arc a14 = new Arc(14, "u", 0, n2, n1);
			Arc a15 = new Arc(15, "t", 0, n1, n2);
			Arc a16 = new Arc(16, "s", 0, n4, n1);
			Arc a17 = new Arc(17, "r", 0, n5, n4);
			
			graphe.ajouterArc(a0);
			graphe.ajouterArc(a1);
			graphe.ajouterArc(a2);
			graphe.ajouterArc(a3);
			graphe.ajouterArc(a4);
			graphe.ajouterArc(a5);
			graphe.ajouterArc(a6);
			graphe.ajouterArc(a7);
			
			graphe.ajouterArc(a10);
			graphe.ajouterArc(a11);
			graphe.ajouterArc(a12);
			graphe.ajouterArc(a13);
			graphe.ajouterArc(a14);
			graphe.ajouterArc(a15);
			graphe.ajouterArc(a16);
			graphe.ajouterArc(a17);
			
			System.out.println(graphe.toString());

			graphe.parcoursProfondeur(n0, true);
			
			System.out.println("Parcours en Largeur");
			graphe.parcoursLargeur(n0);
			System.out.println("END Larg");	

			
		}
		
		else if ( t == 1 ) {
			Graphe_liste graphe = new Graphe_liste();
			
			Noeud n0 = new Noeud(0, "A");
			Noeud n1 = new Noeud(1, "B");
			Noeud n2 = new Noeud(2, "C");
			Noeud n3 = new Noeud(3, "D");
			Noeud n4 = new Noeud(4, "E");

			
			graphe.ajouterNoeud(n0);
			graphe.ajouterNoeud(n1);
			graphe.ajouterNoeud(n2);
			graphe.ajouterNoeud(n3);
			graphe.ajouterNoeud(n4);

	
			
			Arc a0 = new Arc(0, "a", 0);
			Arc a1 = new Arc(1, "b", 0);
			Arc a2 = new Arc(2, "c", 0);
			Arc a3 = new Arc(3, "d", 0);
			Arc a4 = new Arc(4, "e", 0);
			Arc a5 = new Arc(5, "f", 0);
			Arc a6 = new Arc(6, "g", 0);

			
			graphe.ajouterArc(a0,n0,n1);
			graphe.ajouterArc(a1,n2,n1);
			graphe.ajouterArc(a2,n0,n2);
			graphe.ajouterArc(a3,n1,n3);
			graphe.ajouterArc(a4,n1,n1);
			graphe.ajouterArc(a5,n1,n3);						
			graphe.ajouterArc(a6,n2,n4);						

			
			System.out.println(graphe.toString());
			
			//graphe.supprimerNoeud(n1);
			//graphe.supprimerArc(a0);
			
			//System.out.println(graphe.toString());
			
			System.out.println( "Predecesseurs de n2 : " + graphe.getPredecesseurs(n2) );
			System.out.println( "Arcs sortants de n2 : " + graphe.getArcsSortants(n2) );
			System.out.println();
			System.out.println( "Predecesseurs de n1 : " + graphe.getPredecesseurs(n1) );
			System.out.println( "Successeurs de n1 : " + graphe.getSuccesseurs(n1) );
			
			System.out.println("Voisin de n1 : " + graphe.getVoisins(n1)); 
			
			System.out.println( "Arcs sortants de n1 : " + graphe.getArcsSortants(n1) );
			System.out.println( "Arcs entrants en n1 : " + graphe.getArcsEntrants(n1) );

			
			//System.out.println(" Parcous profondeur : " + graphe.parcoursProfondeur(n1, false) );
			//System.out.println(" Parcous largeur    : " + graphe.parcoursLargeur( n1 ) );


		}
		else if ( t == 5 ) {
			Carte carte = new Carte();
			
			Ville v1 = new Ville(1, "Angers", 0);
			Ville v2 = new Ville(2, "Nantes", 0);
			Ville v3 = new Ville(3, "Pornichet", 0);
			Ville v4 = new Ville(4, "Rezé", 0);
			Ville v5 = new Ville(5, "St-Luce-sur-Loire", 0);
			Ville v6 = new Ville(6, "Rennes", 1);
			
			carte.ajouterNoeud(v1);
			carte.ajouterNoeud(v2);
			carte.ajouterNoeud(v3);
			carte.ajouterNoeud(v4);
			carte.ajouterNoeud(v5);
			carte.ajouterNoeud(v6);
	
			Route r1 = new Route(0, "1->1", 1, 1, v1, v1);
			Route r2 = new Route(1, "1->2", 2, 0, v1, v2);
			Route r3 = new Route(2, "2->4", -2, 0, v2, v4);
			Route r4 = new Route(3, "1->4", 1, 0, v1, v4);
			Route r5 = new Route(4, "2->3", 3, 0, v2, v3);
			Route r6 = new Route(5, "2->5", 2, 0, v2, v5);
			Route r7 = new Route(6, "3->5", -2, 0, v3, v5);
			Route r8 = new Route(7, "5->6", 1, 0, v5, v6);

			carte.ajouterArc(r1);
			carte.ajouterArc(r2);
			carte.ajouterArc(r3);
			carte.ajouterArc(r4);
			carte.ajouterArc(r5);
			carte.ajouterArc(r6);
			carte.ajouterArc(r7);
			carte.ajouterArc(r8);
			
			System.out.println("Creation : Well done\n");
			/*
			TreeSet<Route> setVille = new TreeSet<Route>();
			setVille.add(r1);
			setVille.add(r2);
			setVille.add(r3);
			
			System.out.println(setVille);
			*/

			carte.writeDotFile("./map.dot");
			carte.genererItineraireAgregation(v1, 0.5);
			
			System.out.println("END");
						
		}
		else if ( t == 6 ) {
			Carte carte = new Carte();
			try {
			carte.loadFromDotFile( "./map.dot" );
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

			//carte.writeDotFile( "./map_out.dot" );
		}
		else if ( t==7 ){
			Carte carte = new Carte();
			carte.loadFromDotFile("./test.dot");
			
			System.out.println(carte);
			carte.genererItineraireAgregation(carte.getVilleFromId(0), 1);
		}
	}
}
