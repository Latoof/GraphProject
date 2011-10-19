
public class Arc {
	
	int		id;
	String 	label;
	int 	pondération;
	
	/**
	 * @param id
	 * @param label
	 * @param pondération
	 */
	public Arc(int id, String label, int pondération) {
		this.id = id;
		this.label = label;
		this.pondération = pondération;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Arc [id=" + id + ", label=" + label + ", pondération="
				+ pondération + "]";
	}
	
	
	public int getId() {
		return this.id;
	}
	

}
