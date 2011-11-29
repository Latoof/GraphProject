
public class Arc implements Comparable {
	
	int		id;
	String 	label;
	double 	ponderation;
	Noeud	noeudSource, noeudCible;
	
	/**
	 * @param id
	 * @param label
	 * @param pondération
	 * @param noeudSource
	 * @param noeudCible
	 */
	public Arc(int id, String label, double ponderation, Noeud noeudSource, Noeud noeudCible) {
		this.id = id;
		this.label = label;
		this.ponderation = ponderation;
		this.noeudCible = noeudCible;
		this.noeudSource = noeudSource;
	}
	
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

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the ponderation
	 */
	public double getPonderation() {
		return ponderation;
	}

	/**
	 * @param ponderation the ponderation to set
	 */
	public void setPonderation(double ponderation) {
		this.ponderation = ponderation;
	}

	@Override
	public String toString() {
		return "Arc [id=" + id + ", label=" + label + ", ponderation="
				+ ponderation + "]";
	}


	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Arc a = (Arc)this;
		Arc b = (Arc)arg0;
		return a.getId() - b.getId();
	}
}
