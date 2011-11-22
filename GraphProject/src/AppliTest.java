
public class AppliTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int t=5; // A modifier pour changer le test (je m'en sers aussi)
		if (t == 0 ) {
			Graphe_matrice graphe = new Graphe_matrice();
			
			Noeud n0 = new Noeud(0, "A");
			Noeud n1 = new Noeud(1, "B");
			Noeud n2 = new Noeud(2, "C");
			Noeud n3 = new Noeud(3, "D");
			Noeud n4 = new Noeud(4, "E");
			Noeud n5 = new Noeud(5, "F");

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

			System.out.println("Parcours en Profondeur");
			graphe.parcoursProfondeur(n0, true);
			System.out.println("END Prof");
			
			System.out.println("Parcours en Largeur");
			graphe.parcoursLargeur(n0);
			System.out.println("END Larg");	
			
			Ville v1 = new Ville(0, "Nantes", 2);
			System.out.println(v1.getLabel() +" " + v1.getInteret());
			
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
			
			Ville v1 = new Ville(1, "Nantes", 4);
			Ville v2 = new Ville(2, "Angers", 3);
			Ville v3 = new Ville(3, "Les Sables d'Olonnes", 2);
			Ville v4 = new Ville(4, "La Roche/Yon", 2);
			Ville v5 = new Ville(5, "Pornichet", 5);
			Ville v6 = new Ville(6, "paris", 5);
			
			carte.ajouterNoeud(v1);
			carte.ajouterNoeud(v2);
			carte.ajouterNoeud(v3);
			carte.ajouterNoeud(v4);
			carte.ajouterNoeud(v5);
			carte.ajouterNoeud(v6);
	
			Route r1 = new Route(1, "route66s", 10.6, 3, v1, v2);
			Route r2 = new Route(2, "a57", 23.5, 4, v2, v4);
			Route r3 = new Route(3, "a11", 29.8, 1, v4, v5);
			Route r4 = new Route(4, "route66n", 10.6, 3, v2, v1);
			Route r5 = new Route(5, "a59", 10.6, 3, v6, v4);
			Route r6 = new Route(6, "a58", 10.6, 3, v2, v3);
			Route r7 = new Route(6, "a58", 10.6, 3, v2, v3);
			Route r8 = new Route(8, "d5554", 50.3, 5, v5, v1);
			Route r9 = new Route(9, "d125", 30.3, 2, v1, v6);
			Route r10 = new Route(10, "n45", 27.8, 1, v1, v3);

			carte.ajouterArc(r1);
			carte.ajouterArc(r2);
			carte.ajouterArc(r3);
			carte.ajouterArc(r4);
			carte.ajouterArc(r5);
			carte.ajouterArc(r6);
			carte.ajouterArc(r7);
			carte.ajouterArc(r8);
			carte.ajouterArc(r9);
			carte.ajouterArc(r10);
			
			System.out.println("Well done.");
			//System.out.println(carte.toString());
			
			carte.writeDotFile();
			
		}

	}
}
