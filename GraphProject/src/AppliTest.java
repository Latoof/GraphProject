
public class AppliTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int t=1; // A modifier pour changer le test (je m'en sers aussi)
		if (t == 0 ) {
			Graphe_matrice graphe = new Graphe_matrice();
			
			Noeud n1 = new Noeud(0, "A");
			Noeud n2 = new Noeud(1, "B");
			Noeud n3 = new Noeud(2, "C");
			
			graphe.ajouterSommet(n1);
			graphe.ajouterSommet(n2);
			graphe.ajouterSommet(n3);
	
			
			Arc a1 = new Arc(0, "a", 0);
			Arc a2 = new Arc(1, "b", 0);
			Arc a3 = new Arc(2, "c", 0);
			
			/*graphe.ajouterArc(a1);
			graphe.ajouterArc(a2);
			graphe.ajouterArc(a3);
			*/
			System.out.println(graphe.toString());
			
			graphe.supprimerSommet(n2);
			
			System.out.println(graphe.toString());
		}
		else if ( t == 1 ) {
			Graphe_liste graphe = new Graphe_liste();
			
			Noeud n0 = new Noeud(0, "A");
			Noeud n1 = new Noeud(1, "B");
			Noeud n2 = new Noeud(2, "C");
			
			graphe.ajouterNoeud(n0);
			graphe.ajouterNoeud(n1);
			graphe.ajouterNoeud(n2);
	
			
			Arc a0 = new Arc(0, "a", 0);
			Arc a1 = new Arc(1, "b", 0);
			Arc a2 = new Arc(2, "c", 0);
			
			graphe.ajouterArc(a0,n0,n1);
			graphe.ajouterArc(a1,n2,n1);
			graphe.ajouterArc(a2,n0,n2);
			
			
			
			System.out.println(graphe.toString());
		}
		
		
	}

}
