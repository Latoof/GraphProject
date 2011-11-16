
public class Arc implements Comparable<Arc> {
	
	int		id;
	String 	label;
	int 	ponderation;
	Noeud	noeudSource, noeudCible;
	
	/**
	 * @param id
	 * @param label
	 * @param pondération
	 * @param noeudSource
	 * @param noeudCible
	 */
	public Arc(int id, String label, int ponderation, Noeud noeudSource, Noeud noeudCible) {
		this.id = id;
		this.label = label;
		this.ponderation = ponderation;
		this.noeudCible = noeudCible;
		this.noeudSource = noeudSource;
	}
	
	/**
	 * @param id
	 * @param label
	 * @param pondération
	 */
	public Arc(int id, String label, int ponderation) {
		this.id = id;
		this.label = label;
		this.ponderation = ponderation;
	}

	public void setNoeudSource(Noeud noeudSource) {
		this.noeudSource = noeudSource;
	}

	public void setNoeudCible(Noeud noeudCible) {
		this.noeudCible = noeudCible;
	}

	public Noeud getNoeudSource() {
		return noeudSource;
	}

	public Noeud getNoeudCible() {
		return noeudCible;
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Arc [id=" + id + ", label=" + label + ", ponderation="
				+ ponderation + "]";
	}


	
	@Override
	public int compareTo(Arc a) {
		// TODO Auto-generated method stub
		return this.getId() - a.getId();
	}
}
