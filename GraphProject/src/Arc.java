
public class Arc {
	
	int		id;
	String 	label;
	int 	ponderation;
	
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
