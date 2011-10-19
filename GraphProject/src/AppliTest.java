
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
			
		
		System.out.println(graphe.toString());
	}

}
