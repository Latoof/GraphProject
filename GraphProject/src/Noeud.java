
public class Noeud implements Comparable {

	int 	id;
	private static int compteur_noeuds = 0;
	
	
	public Noeud() {
		
		// this.id = ++compteur_noeuds;
		// ID commencant a 1
		
		this.id = compteur_noeuds++;
		// ID commencant a 0
		System.out.println("Ajout noeud "+this.id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "Noeud [id=" + id +"];";
	}


	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Noeud a = (Noeud)this;
		Noeud b = (Noeud)arg0;
		return a.getId() - b.getId();
	}
	
	
	
}
