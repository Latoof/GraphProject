
public class Noeud implements Comparable<Noeud> {

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

	@Override
	public int compareTo(Noeud arg0) {
		// TODO Auto-generated method stub
		return this.getId() - arg0.getId();
	}
	
	
	
}
