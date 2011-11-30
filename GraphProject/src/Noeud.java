
public class Noeud implements Comparable {

	int 	id;
	
	public Noeud(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

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
