/**
* @author Matthieu Lenogue - Maxime Ouairy
*/

public class Ville extends Noeud {
	
	int 	interet;
	String 	nomVille;
	
	/**
	 * Constructeur d'objet de type Ville. 
	 * On considère qu'une ville à un identifiant, un nom, et un intérêt.
	 * @param idNumb
	 * @param nomVille
	 * @param interet
	 */
	public Ville(int idNumb, String nomVille, int interet) {
		super(idNumb);
		this.interet = interet;
		this.nomVille = nomVille;
	}
	
	public int getInteret() {
		return interet;
	}
	
	public void setInteret(int interet) {
		this.interet = interet;
	}
	
	public String getNomVille() {
			return nomVille;
		}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}
	/**
	 * @return la version toString de l'interet
	 */
	public String getInteretString(){
		
		String stars = "";
		
		for (int i=0;i < this.interet; i++){
			stars += "*";
		}
		
		return stars;
	}

	
	/**
	 * Permet de générer une string contenant la version .dot de la ville
	 * @return La string contenant la version .dot de notre ville
	 */
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
	 * On considere d'abord l'interet puis l'identifiant pour classer les villes entre elles.
	 * @param o
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Ville a = (Ville)this;
		Ville b = (Ville)o;
		
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ville [interet=" + interet + ", nomVille=" + nomVille + "]";
	}
	
}
