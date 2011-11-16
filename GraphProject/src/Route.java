
public class Route extends Arc {

	String identifiant;
	int interet;
	
	/***
	 * 
	 * @param id
	 * @param identifiant
	 */

	public Route(int id, String identifiant, int distance, int interet, Ville villeSource, Ville villeCible) {
		super(id, identifiant, distance, villeSource, villeCible);
		this.identifiant = identifiant;
		this.interet = interet;
	}
	
	/**
	 * @return the interet
	 */
	public int getInteret() {
		return interet;
	}

	/**
	 * @param interet the interet to set
	 */
	public void setInteret(int interet) {
		this.interet = interet;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	public String toDotLine() {
		String ligneDot =""; // on initialise la ligneDot de la transition
		ligneDot += "\"" + this.getNoeudSource().getLabel() + "\""; // on ajoute le noeud source entre guillemets
		ligneDot += " -> ";
		ligneDot += "\"" + this.getNoeudCible().getLabel() + "\""; //on ajoute le noeud destination entre guillemets
		ligneDot += " ["; // on ajoute ensuite les options de la transition
		ligneDot += "label=\""+this.getIdentifiant()+"\" ";
		ligneDot += "fontcolor=" + "black" + " ";
		ligneDot += "labelfontname=" + "Sans" + " ";
		ligneDot += "labelfontsize=" + "14" + " ";
		ligneDot += "style=" + "solid" + " ";
		ligneDot += "color=" + "black" + " ";
		ligneDot += "arrowhead=" + "normal";
		ligneDot += "]; \n" ; // fin des options
		
		return ligneDot;
	}
	
}
