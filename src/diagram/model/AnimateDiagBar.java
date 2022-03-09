package diagram.model;

import java.util.*;
import java.io.*;

public class AnimateDiagBar {
	public static void main(String[] args) {
		// récupère les arguments
		String fichier = args[0];
		int nbBarre = Integer.parseInt(args[1]);
		String sort = args[2];

		// Permet d'avoir un affichage de barres minimum et maximum
		if (nbBarre < 5)
			nbBarre = 5;
		else if (nbBarre > 25)
			nbBarre = 25;

		// création et initialisation des variables de type Sting: title, xAxis, source, caption et ligne
		String title = "titre";         // titre du diagramme
		String xAxis = "xAxis";         // légende de l'axe des absices
		String source = "source";       // source du diagramme
		String caption = "";            // date de chaque diagramme
		String ligne = "";              // prend la valeur de chaque ligne du document fichier

		// création et initialisation des variables de type int: compteurLigne, debutDiagbar, finDiagbar, nbTBar et tailleList
		int compteurLignes = 4;
		int debutDiagBar = 0;
		int finDiagBar = 0;
		int nbTBar = 0;
		int tailleList = 0;

		// créer et initialise les liste names, values et categorys
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		ArrayList<String> categorys = new ArrayList<String>();

		try {
			// met en place le scanner
			Scanner sc = new Scanner(new File(fichier));
			// donne la valeur du titre, de xAxis et de la source du fichier
			title = sc.nextLine();
			xAxis = sc.nextLine();
			source = sc.nextLine();

			//créer un écran pour afficher un diagramme (affichage lorsque l'on met: StdDraw.show())
			StdDraw.setCanvasSize(1000, 700);
			StdDraw.enableDoubleBuffering();
			// créer un diagramme avec la classe DiagBar
			DiagBar diag = new DiagBar(title, xAxis, source);

			try {
				Thread.sleep(6000);
			} catch (Exception e) {

			}

			while(sc.hasNextLine()){
				// met la ligne du fichier dans la variable ligne
				ligne = sc.nextLine();

				// si une ligne est vide, le diagramme commence à la prochaine ligne
				if (ligne.length() == 0){
					debutDiagBar = compteurLignes + 1;
				}
				//début du diagramme
				if (compteurLignes == debutDiagBar){
					nbTBar = Integer.parseInt(ligne);
					finDiagBar = debutDiagBar + nbTBar;
					tailleList = names.size();
					Bar[] tBars = new Bar[tailleList];
					for (int i=0; i<tailleList; i++){
						tBars[i] = new Bar(names.get(i), values.get(i), categorys.get(i));
					}
					// ne trie pas la liste si l'agument 2 est nosort
					if (!sort.equals("nosort"))
						Arrays.sort(tBars);

					// vide le contenu des listes
					names.clear();
					values.clear();
					categorys.clear();

					diag.setCaption(caption);
					diag.add("STOP", 0, "STOP");
					if (tBars.length >0) {
						for (int i = 0; i < nbBarre; i++) {
							diag.add(tBars[i].getName(), tBars[i].getValue(), tBars[i].getCategory());
						}
						diag.draw();
						StdDraw.show();
						StdDraw.pause(125);
						StdDraw.clear();
					}
				}
				// ajoute les valeurs dans les listes
				if (compteurLignes >= debutDiagBar+1 && compteurLignes <= finDiagBar && ligne.length() != 0) {
					String[] argumentTBar = ligne.split(",");
					caption = argumentTBar[0];
					names.add(argumentTBar[1]);
					values.add((int)Double.parseDouble(argumentTBar[3]));
					categorys.add(argumentTBar[2]);
				}
				compteurLignes ++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé");
		}
	}
}