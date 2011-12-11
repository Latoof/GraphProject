
public class Noeud implements Comparable {

	/**
	 * @uml.property  name="id"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="Noeud" qualifier="this:Graphe java.lang.Double"
	 */
	int 	id;

	
	public Noeud() {
		
		this.id = -1;
		// ID commencant a 0
	}

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
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
