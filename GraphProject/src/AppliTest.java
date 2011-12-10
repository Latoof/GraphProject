import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;



public class AppliTest {

	public static int MATRICE = 0;
	public static int LISTE = 1;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		int t=10; // A modifier pour changer le test (je m'en sers aussi)
		if (t == 0 ) {
			Graphe_matrice graphe = new Graphe_matrice();
			
			Noeud n0 = new Noeud();
			Noeud n1 = new Noeud();
			Noeud n2 = new Noeud();
			Noeud n3 = new Noeud();
			Noeud n4 = new Noeud();
			Noeud n5 = new Noeud();

			graphe.ajouterNoeud(n0);			
			graphe.ajouterNoeud(n1);
			graphe.ajouterNoeud(n2);
			graphe.ajouterNoeud(n3);
			graphe.ajouterNoeud(n4);
			graphe.ajouterNoeud(n5);
			
			Arc a0 = new Arc(0, 0, n0, n0);
			Arc a1 = new Arc(1, 0, n0, n1);
			Arc a2 = new Arc(2, 0, n0, n3);
			Arc a3 = new Arc(3, 0, n1, n3);
			Arc a4 = new Arc(4, 0, n1, n2);
			Arc a5 = new Arc(5, 0, n2, n1);
			Arc a6 = new Arc(6, 0, n1, n4);
			Arc a7 = new Arc(7, 0, n4, n5);
			
			Arc a10 = new Arc(10, 0, n0, n0);
			Arc a11 = new Arc(11, 0, n1, n0);
			Arc a12 = new Arc(12, 0, n3, n0);
			Arc a13 = new Arc(13, 0, n3, n1);
			Arc a14 = new Arc(14, 0, n2, n1);
			Arc a15 = new Arc(15, 0, n1, n2);
			Arc a16 = new Arc(16, 0, n4, n1);
			Arc a17 = new Arc(17, 0, n5, n4);
			
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
			
			Noeud n0 = new Noeud();
			Noeud n1 = new Noeud();
			Noeud n2 = new Noeud();
			Noeud n3 = new Noeud();
			Noeud n4 = new Noeud();

			
			graphe.ajouterNoeud(n0);
			graphe.ajouterNoeud(n1);
			graphe.ajouterNoeud(n2);
			graphe.ajouterNoeud(n3);
			graphe.ajouterNoeud(n4);

	
			
			Arc a0 = new Arc(0, 0);
			Arc a1 = new Arc(1, 0);
			Arc a2 = new Arc(2, 0);
			Arc a3 = new Arc(3, 0);
			Arc a4 = new Arc(4, 0);
			Arc a5 = new Arc(5, 0);
			Arc a6 = new Arc(6, 0);

			
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
			Carte_m carte = new Carte_m();
			
			Ville v1 = new Ville(0, "1", 1);
			Ville v2 = new Ville(1, "2", 2);
			Ville v3 = new Ville(2, "3", 1);
			Ville v4 = new Ville(3, "4", 1);
			Ville v5 = new Ville(4, "5", 3);
			Ville v6 = new Ville(5, "6", 1);
			
			carte.ajouterNoeud(v1);
			carte.ajouterNoeud(v2);
			carte.ajouterNoeud(v3);
			carte.ajouterNoeud(v4);
			carte.ajouterNoeud(v5);
			carte.ajouterNoeud(v6);
	
			Route r1 = new Route(0, "1->1", 1, 1, v1, v1);
			Route r2 = new Route(1, "1->2", 2, 1, v1, v2);
			Route r3 = new Route(2, "2->4", 2, 2, v2, v4);
			Route r4 = new Route(3, "1->4", 1, 1, v1, v4);
			Route r5 = new Route(4, "2->3", 3, 3, v2, v3);
			Route r6 = new Route(5, "2->5", 2, 4, v2, v5);
			Route r7 = new Route(6, "3->5", 2, 1, v3, v5);
			Route r8 = new Route(7, "5->6", 1, 1, v5, v6);

			carte.ajouterArc(r1);
			carte.ajouterArc(r2);
			carte.ajouterArc(r3);
			carte.ajouterArc(r4);
			carte.ajouterArc(r5);
			carte.ajouterArc(r6);
			carte.ajouterArc(r7);
			carte.ajouterArc(r8);
			
			System.out.println("Creation : Well done\n");


//			carte.genererItineraireAgregation(v1, v6, 1);
			
			carte.genererItineraireDetourBorne(carte.getVilleFromId(0), carte.getVilleFromId(5), 3.0);

	
//			Chrono c = new Chrono();
//			c.start();
//			carte.genererItineraireDetourBorne(v1, v6, 3.0);
//			c.stop();
//			System.out.println("Chrono : "+c.getMilliseconds()+" ms");
//			carte.plusCourtDijkstra(v1);
			
			System.out.println("END");
						
		}
		else if ( t == 6 ) {
			Carte_m carte = new Carte_m();
			try {
			carte.loadFromDotFile( "./map.dot" );
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

			//carte.writeDotFile( "./map_out.dot" );
		}
		else if ( t==7 ){
			Carte_m carte = new Carte_m();
			carte.loadFromDotFile("./essai2.dot");
			

			
			
			
			System.out.println(carte);
			// Code ici
			Chrono c = new Chrono();
			c.start();
			carte.genererItineraireDetourBorne(carte.getVilleFromId(1), carte.getVilleFromId(3), 3.0);
	//		carte.genererItineraireDetourBorne(v1, v2, coeff)
			c.stop();
			System.out.println("Chrono : "+c.getMilliseconds()+" ms");
			
			System.out.println(carte);
//			carte.genererItineraireAgregation(carte.getVilleFromId(0), 1);
		}
		else if (t == 8){
			Carte_m carte = new Carte_m();
			carte.loadFromDotFile("./essai2.dot");
			
			System.out.println(carte);
			
			carte.genererItineraireAgregation(carte.getVilleFromId(0), carte.getVilleFromId(3), 0.63);
		}
		else if ( t == 9) { // Simple test de validite du temps CPU

			Carte_m carte = new Carte_m();
			/*
			Ville v1 = new Ville(0, "1", 1);
			Ville v2 = new Ville(1, "2", 2);
			Ville v3 = new Ville(2, "3", 1);
			Ville v4 = new Ville(3, "4", 1);
			Ville v5 = new Ville(4, "5", 3);
			Ville v6 = new Ville(5, "6", 1);
			
			carte.ajouterNoeud(v1);
			carte.ajouterNoeud(v2);
			carte.ajouterNoeud(v3);
			carte.ajouterNoeud(v4);
			carte.ajouterNoeud(v5);
			carte.ajouterNoeud(v6);
	
			Route r1 = new Route(0, "1->1", 1, 1, v1, v1);
			Route r2 = new Route(1, "1->2", 2, 1, v1, v2);
			Route r3 = new Route(2, "2->4", 2, 2, v2, v4);
			Route r4 = new Route(3, "1->4", 1, 1, v1, v4);
			Route r5 = new Route(4, "2->3", 3, 3, v2, v3);
			Route r6 = new Route(5, "2->5", 2, 4, v2, v5);
			Route r7 = new Route(6, "3->5", 2, 1, v3, v5);
			Route r8 = new Route(7, "5->6", 1, 1, v5, v6);

			carte.ajouterArc(r1);
			carte.ajouterArc(r2);
			carte.ajouterArc(r3);
			carte.ajouterArc(r4);
			carte.ajouterArc(r5);
			carte.ajouterArc(r6);
			carte.ajouterArc(r7);
			carte.ajouterArc(r8);
			*/
			TestsTempsProcesseur tp = new TestsTempsProcesseur( );
			
			/*
			long tps_CheminPlusCourt = tp.testCheminPlusCourt( );
			
 			System.out.println("TC ---> Temps proc pour chemin le plus court : "+ ( tps_CheminPlusCourt > -1 ? tps_CheminPlusCourt : "Echec" ));

			
			long tps_DetourBorne = tp.testDetourBorne( 0.5 );
			System.out.println("TC ---> Temps proc pour chemin le plus court : "+ ( tps_DetourBorne > -1 ? tps_DetourBorne : "Echec" ));
			*/
			


			long tps_serie = tp.serieTestDetourBorne(100, LISTE, 0.4, 8, (float)0.5 );
 			System.out.println("TC ---> Temps proc moyen borne : "+ ( tps_serie > -1 ? tps_serie : "Echec" ));

 			/*
 			System.out.println("TC ---> Temps proc pour chemin le plus court : "+ ( tps_CheminPlusCourt > -1 ? tps_CheminPlusCourt : "Echec" ));
			System.out.println("TC ---> Temps proc pour detour borne : "+ ( tps_DetourBorne > -1 ? tps_DetourBorne : "Echec" ));
			*/
 			System.out.println("TC ---> Temps proc moyen borne : "+ ( tps_serie > -1 ? tps_serie : "Echec" ));

		}
		else if ( t == 10 ) { // Re_tests sur l'implementation liste_adj (car bizzarreries)
			
			TestsTempsProcesseur tp = new TestsTempsProcesseur( );
			tp.genererCarte(8, (float) 0.2);
			
			Carte_l cl = tp.carte_l;
			
			System.out.println(cl);
			System.out.println("Successeurs : "+cl.getSuccesseurs( cl.getNoeudFromId(3) ));
			System.out.println("Predecesseurs : "+cl.getPredecesseurs( cl.getNoeudFromId(3) ));
			
			
		}
	}
}
