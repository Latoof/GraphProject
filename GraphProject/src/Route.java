
public class Route extends Arc {

	/**
	 * @uml.property  name="identifiant"
	 */
	String identifiant;
	/**
	 * @uml.property  name="interet"
	 */
	int interet;
	
	/***
	 * 
	 * @param id
	 * @param identifiant
	 */

	public Route(int id, String identifiant, double distance, int interet, Ville villeSource, Ville villeCible) {
		super(id, distance, villeSource, villeCible);
		this.identifiant = identifiant;
		this.interet = interet;
	}
	
	/**
	 * @return la version toString de l'interet
	 */
	public String getInteretString(){
		
		String stars = new String();
		
		for (int i=0;i < this.interet;i++){
			stars += "*";
		}
		
		return stars;
	}

	/**
	 * @return  the interet
	 * @uml.property  name="interet"
	 */
	public int getInteret() {
		return interet;
	}
	
	/**
	 * @param interet  the interet to set
	 * @uml.property  name="interet"
	 */
	public void setInteret(int interet) {
		this.interet = interet;
	}
	
	/**
	 * @return  the identifiant
	 * @uml.property  name="identifiant"
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant  the identifiant to set
	 * @uml.property  name="identifiant"
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	
	
	/**
	 * Permet de générer une string contenant la version .dot de la route
	 * @return La string contenant la version .dot de notre route
	 */
	public String toDotLine() {
		String ligneDot =""; // on initialise la ligneDot de la transition
		ligneDot += "\"" + ((Ville)this.getNoeudSource()).getNomVille() + "\""; // on ajoute le noeud source entre guillemets
		ligneDot += " -> ";
		ligneDot += "\"" + ((Ville)this.getNoeudCible()).getNomVille() + "\""; //on ajoute le noeud destination entre guillemets
		ligneDot += " ["; // on ajoute ensuite les options de la transition
		ligneDot += "label=\""+this.getIdentifiant();
		ligneDot += "(" + this.ponderation + ";";
		ligneDot += this.getInteretString() + ")\"";

		ligneDot += "];\n" ; // fin des options
		
		return ligneDot;
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 * On considere qu'une route est plus intéréssante si elle a une distance inférieure, 
	 * ET un interet plus important que l'autre route. Dans le cas, où les routes sont non 
	 * comparables, on ne considère pas les routes égales (au sens objets équivalents), et 
	 * on les distingue par leur id.
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Route a = (Route)this;
		Route b = (Route)o;
		if ((a.interet < b.getInteret()) && (a.ponderation > b.getPonderation())){
			return -1;
		}
		else if ((a.interet > b.getInteret()) && (a.ponderation < b.getPonderation())){
			return 1;
		}
		else {
			if(a.getId() > b.getId()){
				return 1;
			}
			else{
				return -1;
			}
		}
	}

	@Override
	public String toString() {
		return "Route [identifiant=" + identifiant + ", interet=" + interet
				+ ", id=" + id + ", ponderation=" + ponderation
				+ ", noeudSource=" + noeudSource + ", noeudCible=" + noeudCible
				+ "]";
	}
	
}
