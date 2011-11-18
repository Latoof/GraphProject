
public class Route extends Arc {

	String identifiant;
	int interet;
	
	/***
	 * 
	 * @param id
	 * @param identifiant
	 */

	public Route(int id, String identifiant, double distance, int interet, Ville villeSource, Ville villeCible) {
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
	
	public String getInteretString(){
		
		String stars = new String();
		
		for (int i=0;i < this.interet;i++){
			stars += "*";
		}
		
		return stars;
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
		ligneDot += "label=\""+this.getIdentifiant();
		ligneDot += "(" + this.ponderation + ";";
		ligneDot += this.getInteretString() + ")\"";
		/*
		ligneDot += "fontcolor=" + "black" + " ";
		ligneDot += "labelfontname=" + "Sans" + " ";
		ligneDot += "labelfontsize=" + "14" + " ";
		ligneDot += "style=" + "solid" + " ";
		ligneDot += "color=" + "black" + " ";
		ligneDot += "arrowhead=" + "normal";
		*/
		ligneDot += "];\n" ; // fin des options
		
		return ligneDot;
	}
	
	/**
	 * 
	 * @param r
	 * @return
	 * On considere qu'une route est plus intéréssante si elle a une distance inférieure, 
	 * ET un interet plus important que l'autre route. Dans le cas, où les routes sont non 
	 * comparables, on considére que les routes sont "égales" (Les 2 possibilités restent 
	 * à envisager).
	 */
	public int compareTo(Route r) {
		// TODO Auto-generated method stub
		if ((this.interet < r.getInteret()) && (this.ponderation > r.getPonderation())){
			return -1;
		}
		else if ((this.interet > r.getInteret()) && (this.ponderation < r.getPonderation())){
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
