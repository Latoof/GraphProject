
public class AppliTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
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

}
