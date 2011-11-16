
public class Ville extends Noeud {
	
	int 	interet;
	String 	nomVille;
	
	public Ville(int idNumb, String nomVille, int interet) {
		super(idNumb, nomVille);
		this.interet = interet;
	}
	
	public int getInteret() {
		return interet;
	}

	public void setInteret(int interet) {
		this.interet = interet;
	}
	
	
	public String toDotLine() {

		String ligneDot = "\"" + this.label + "\""; // on commence par
		// prendre le label
		// du noeud
		ligneDot += " ["; // on met le crochet qui precede les options
		// ensuite on met les options
		ligneDot += "label=\"" + this.label + "\" ";
		ligneDot += "fontcolor=" + "black" + " ";
		ligneDot += "shape=" + "ellipse" + " ";
		ligneDot += "color=" + "black" + " ";
		ligneDot += "fontname=" + "Sans" + " ";
		ligneDot += "fontsize=" + "14" + " ";
		ligneDot += "style=" + "Sans" + " ";
		ligneDot += "]; \n"; // on ferme le crochet

		return ligneDot;
	}
	
}
