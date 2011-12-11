
public class Arc implements Comparable {
	
	/**
	 * @uml.property  name="id"
	 */
	int		id;
	/**
	 * @uml.property  name="ponderation"
	 */
	double 	ponderation;
	/**
	 * @uml.property  name="noeudSource"
	 * @uml.associationEnd  
	 */
	Noeud	noeudSource;
	/**
	 * @uml.property  name="noeudCible"
	 * @uml.associationEnd  
	 */
	Noeud noeudCible;
	
	/**
	 * @param id
	 * @param label
	 * @param pondération
	 * @param noeudSource
	 * @param noeudCible
	 */
	public Arc(int id, double ponderation, Noeud noeudSource, Noeud noeudCible) {
		this.id = id;
		this.ponderation = ponderation;
		this.noeudCible = noeudCible;
		this.noeudSource = noeudSource;
	}
	
	public Arc(int id, int ponderation) {
		this.id = id;
		this.ponderation = ponderation;
	}

	/**
	 * @param noeudSource
	 * @uml.property  name="noeudSource"
	 */
	public void setNoeudSource(Noeud noeudSource) {
		this.noeudSource = noeudSource;
	}

	/**
	 * @param noeudCible
	 * @uml.property  name="noeudCible"
	 */
	public void setNoeudCible(Noeud noeudCible) {
		this.noeudCible = noeudCible;
	}

	/**
	 * @return
	 * @uml.property  name="noeudSource"
	 */
	public Noeud getNoeudSource() {
		return noeudSource;
	}

	/**
	 * @return
	 * @uml.property  name="noeudCible"
	 */
	public Noeud getNoeudCible() {
		return noeudCible;
	}
	
	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return this.id;
	}


	/**
	 * @return  the ponderation
	 * @uml.property  name="ponderation"
	 */
	public double getPonderation() {
		return ponderation;
	}

	/**
	 * @param ponderation  the ponderation to set
	 * @uml.property  name="ponderation"
	 */
	public void setPonderation(double ponderation) {
		this.ponderation = ponderation;
	}

	@Override
	public String toString() {
		return "Arc [id=" + id + ", ponderation="
				+ ponderation + "]";
	}


	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Arc a = (Arc)this;
		Arc b = (Arc)arg0;
		return a.getId() - b.getId();
	}
}
