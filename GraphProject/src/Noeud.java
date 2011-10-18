
public class Noeud {

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Noeud [id=" + id + ", label=" + label + "]";
	}
	
	
	
}
