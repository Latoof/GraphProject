
public class Arc {
	
	int		id;
	String 	label;
	int 	ponderation;
	Noeud	noeudSource, noeudCible;
	
	/**
	 * @param id
	 * @param label
	 * @param pondération
	 */
	public Arc(int id, String label, int ponderation, Noeud noeudSource, Noeud noeudCible) {
		this.id = id;
		this.label = label;
		this.ponderation = ponderation;
		this.noeudCible = noeudCible;
		this.noeudSource = noeudSource;
	}

	public Noeud getNoeudSource() {
		return noeudSource;
	}

	public Noeud getNoeudCible() {
		return noeudCible;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Arc [id=" + id + ", label=" + label + ", ponderation="
				+ ponderation + "]";
	}
	
	
	public int getId() {
		return this.id;
	}
	

}
