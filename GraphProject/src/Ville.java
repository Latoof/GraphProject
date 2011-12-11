/**
* @author Matthieu Lenogue - Maxime Ouairy
*/

public class Ville extends Noeud {
	
	/**
	 * @uml.property  name="interet"
	 */
	int 	interet;
	/**
	 * @uml.property  name="nomVille"
	 */
	String 	nomVille;
	
	/**
	 * Constructeur d'objet de type Ville. 
	 * On considère qu'une ville à un identifiant, un nom, et un intérêt.
	 * @param idNumb
	 * @param nomVille
	 * @param interet
	 */
	public Ville(int idNumb, String nomVille, int interet) {
		super();
		this.interet = interet;
		this.nomVille = nomVille;
	}
	
	/**
	 * @return
	 * @uml.property  name="interet"
	 */
	public int getInteret() {
		return interet;
	}
	
	/**
	 * @param interet
	 * @uml.property  name="interet"
	 */
	public void setInteret(int interet) {
		this.interet = interet;
	}
	
	/**
	 * @return
	 * @uml.property  name="nomVille"
	 */
	public String getNomVille() {
			return nomVille;
		}

	/**
	 * @param nomVille
	 * @uml.property  name="nomVille"
	 */
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

	@Override
	public String toString() {
		return "Ville [interet=" + interet + ", nomVille=" + nomVille + ", id="
				+ id + "]";
	}
	
}
