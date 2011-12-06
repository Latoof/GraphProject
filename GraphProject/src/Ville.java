
public class Ville extends Noeud {
	
	int 	interet;
	String 	nomVille;
	
	public Ville(int idNumb, String nomVille, int interet) {
		super(idNumb);
		this.interet = interet;
		this.nomVille = nomVille;
	}
	
	public int getInteret() {
		return interet;
	}
	
	
	
public String getNomVille() {
		return nomVille;
	}

public void setNomVille(String nomVille) {
	this.nomVille = nomVille;
}

public String getInteretString(){
		
		String stars = "";
		
		for (int i=0;i < this.interet; i++){
			stars += "*";
		}
		
		return stars;
	}

	public void setInteret(int interet) {
		this.interet = interet;
	}
	
	
	public String toDotLine() {

		String ligneDot = "\"" + this.getNomVille() + "\""; // on commence par prendre le label du noeud
		ligneDot += " ["; // on met le crochet qui precede les options
		// ensuite on met les options
		ligneDot += "label=\"" + this.getNomVille();
		ligneDot += "(" + this.getInteretString() + ")\"";
		
		/*
		ligneDot += "fontcolor=" + "black" + " ";
		ligneDot += "shape=" + "ellipse" + " ";
		ligneDot += "color=" + "black" + " ";
		ligneDot += "fontname=" + "Sans" + " ";
		ligneDot += "fontsize=" + "14" + " ";
		ligneDot += "style=" + "Sans" + " ";
		*/
		
		ligneDot += "];\n"; // on ferme le crochet

		return ligneDot;
	}
	
	/**
	 * On considere d'abord l'interet puis l'id pour classer les villes entre elles.
	 */
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Ville a = (Ville)this;
		Ville b = (Ville)arg0;
		
		if (a.getInteret() > b.getInteret()){
			return 1;
		}
		else if (a.getInteret() < b.getInteret()){
			return -1;
		}
		else {
			if (a.getId() > b.getId()){
				return 1;
			}
			else{
				return -1;
			}
		}
	}
	
}
