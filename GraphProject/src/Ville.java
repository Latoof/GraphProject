
public class Ville extends Noeud {
	
	int 	interet;
	
	public Ville(int idNumb, String label, int interet) {
		super(idNumb, label);
		this.interet = interet;
	}

	public int getInteret() {
		return interet;
	}

	public void setInteret(int interet) {
		this.interet = interet;
	}
	
	
}
