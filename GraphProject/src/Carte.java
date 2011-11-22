import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Carte extends Graphe_matrice {


	
	//ArrayList	listeRoutes, listeVilles;

	public Carte() {
		super();
	}

	public void writeDotFile(){
		
		File file = new File("./graph.dot");
		FileWriter fw;		
		
		String dotString = "digraph {\n";
		
		try {
			//Création de l'objet
			fw = new FileWriter(file);
			for(int i=0; i<liste_noeud.size();i++){
				Ville v = (Ville)liste_noeud.get(i);
				dotString += v.toDotLine();
			}
			for(int i=0;i<liste_arc.size();i++){
				Route r = (Route)liste_arc.get(i);
				dotString += r.toDotLine();
				
			}

			
			dotString += "}";
			//On écrit la chaîne
			fw.write(dotString);
			//On ferme le flux
			fw.close();
			
			//affichage
			System.out.println(dotString);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
