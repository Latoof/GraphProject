import java.util.ArrayList;
import java.util.Vector;


public class AppliTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int t=0; // A modifier pour changer le test (je m'en sers aussi)
		if (t == 0 ) {
			Graphe_matrice graphe = new Graphe_matrice();
			
			Noeud n0 = new Noeud(0, "A");
			Noeud n1 = new Noeud(1, "B");
			Noeud n2 = new Noeud(2, "C");
			Noeud n3 = new Noeud(3, "D");
			Noeud n4 = new Noeud(4, "E");
			Noeud n5 = new Noeud(5, "F");
			
			Noeud n10 = new Noeud(10, "Z");
			

			graphe.ajouterSommet(n0);			
			graphe.ajouterSommet(n1);
			graphe.ajouterSommet(n2);
			graphe.ajouterSommet(n3);
			graphe.ajouterSommet(n4);
			graphe.ajouterSommet(n5);
			
			graphe.ajouterSommet(n10);
			
			Arc a0 = new Arc(0, "a", 0, n0, n0);
			Arc a1 = new Arc(1, "b", 0, n0, n1);
			Arc a2 = new Arc(2, "c", 0, n0, n3);
			Arc a3 = new Arc(3, "d", 0, n1, n3);
			Arc a4 = new Arc(4, "e", 0, n1, n2);
			Arc a5 = new Arc(5, "f", 0, n2, n1);
			Arc a6 = new Arc(6, "g", 0, n1, n4);
			Arc a7 = new Arc(7, "h", 0, n4, n5);
			
			graphe.ajouterArc(a0);
			graphe.ajouterArc(a1);
			graphe.ajouterArc(a2);
			graphe.ajouterArc(a3);
			graphe.ajouterArc(a4);
			graphe.ajouterArc(a5);
			graphe.ajouterArc(a6);
			graphe.ajouterArc(a7);
			
			System.out.println(graphe.toString());
			
			graphe.supprimerSommet(n10);
			
			System.out.println(graphe.toString());
			
			System.out.println("Successeurs N1 :");
			System.out.println(graphe.getSuccesseurs(n1));
			System.out.println("Arcs sortants N1 :");
			System.out.println(graphe.getArcsSortants(n1));
			System.out.println("Predecesseurs N3 :");
			System.out.println(graphe.getPredecesseurs(n3));
			System.out.println("Arcs entrants N3 :");
			System.out.println(graphe.getArcsEntrants(n3));
			System.out.println("Voisins N3 :");
			System.out.println(graphe.getVoisins(n3));

			//graphe.supprimerArc(a2);
			
			System.out.println(graphe.toString());
			
			System.out.println("Parcours en Profondeur");
			graphe.parcoursProfondeur(n0);
						
		}
		/*
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

			
			System.out.println(" Parcous profondeur : " + graphe.parcoursProfondeur(n1, null) );
			System.out.println(" Parcous largeur    : " + graphe.parcoursLargeur( n1 ) );


		}
		*/
	}
}
