
public class Route extends Arc {

	String identifiant;
	
	public Route(int id, String label, int ponderation) {
		super(id, label, ponderation);

	}

	public Route(int id, String identifiant, String label, int ponderation) {
		super(id, label, ponderation);
		this.identifiant = identifiant;
	}
	
	public Route(int id, String label, int ponderation, Noeud noeudSource, Noeud noeudCible) {
		super( id,  label,  ponderation,  noeudSource, noeudCible);
	}
	
	public Route(int id, String identifiant, String label, int ponderation, Noeud noeudSource, Noeud noeudCible) {
		super( id,  label,  ponderation,  noeudSource, noeudCible);
		this.identifiant = identifiant;
	}
	
	public String toDotLine() {
		String ligneDot =""; // on initialise la ligneDot de la transition
		ligneDot += "\"" + this.getNoeudSource().getLabel() + "\""; // on ajoute le noeud source entre guillemets
		ligneDot += " -> ";
		ligneDot += "\"" + this.getNoeudCible().getLabel() + "\""; //on ajoute le noeud destination entre guillemets
		ligneDot += " ["; // on ajoute ensuite les options de la transition
		ligneDot += "label=\""+label.toString()+"\" ";
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
