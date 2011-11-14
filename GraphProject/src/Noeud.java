
public class Noeud implements Comparable {

	int 	id;
	String 	label;
	
	/**
	 * @param id
	 * @param label
	 */
	public Noeud(int id, String label) {
		this.id = id;
		this.label = label;
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public String toString() {
		return "Noeud [id=" + id + ", label=" + label + "]";
	}



	public int compareTo(Object noeud_a_comparer) {

		return (this.id - ((Noeud) noeud_a_comparer).id); // Peut etre a inverser
	}
	
	
	
}
