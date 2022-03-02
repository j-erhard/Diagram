package diagram.model;

import java.util.*;
import java.io.*;

public class PreprocessData {

	public static void main(String[] args) {
        //Nom des colonnes
        String fichier = args[0];
        String colCaptions = args[1];
        String colNames = args[2];
        String colValues = args[3];
        String colCats = args[4];

        //place des colonnes par rapport au noms
        int placeCaption = 0;
        int placeName =0;
        int placeValues = 0;
        int placeCats = 0;

        //Liste du contenu des colonnes
        ArrayList<String> idCaption = new ArrayList<String>();
        ArrayList<String> idNames = new ArrayList<String>();
        ArrayList<String> idValues = new ArrayList<String>();
        ArrayList<String> idCats = new ArrayList<String>();
        ArrayList<String> copieIdCaption = new ArrayList<String>();

        String ligne;

        try {
                Scanner sc = new Scanner(new File(fichier));

                ligne = sc.nextLine();
                String[] lst = ligne.split(",");
                int nbArguments = lst.length;
                for(int i=0; i<nbArguments; i++){
                        if(colCaptions.equals(lst[i])){
                                placeCaption = i;
                        } else if(colNames.equals(lst[i])){
                                placeName = i;
                        }else if(colValues.equals(lst[i])){
                                placeValues = i;
                        }else if(colCats.equals(lst[i])){
                                placeCats = i;
                        }
                }
                while(sc.hasNextLine()) {
                        ligne = sc.nextLine();
                        ligne = ligne.replaceAll(",,", ",0,");
                        lst = ligne.split(",");
                        idCaption.add(lst[placeCaption]);
                        copieIdCaption.add(lst[placeCaption]);
                        idNames.add(lst[placeName]);
                        idValues.add(lst[placeValues]);
                        idCats.add(lst[placeCats]);
                }
                //Affichage des données dans outFile
                //affichage de l'entete
                System.out.println("Mettre le titre du diagramme");
                System.out.println("Value ($ million)");
                System.out.println("Source: inconnu");
                System.out.println("");
                //affichage du corps
                String suppr = "\"";
                int indexSuppr = 0;
                while(copieIdCaption.size() > 0){
                        int nbMemeCaption = 0;
                        Object minCaption = Collections.min(copieIdCaption);
                        while(copieIdCaption.contains(minCaption)){
                                indexSuppr = copieIdCaption.indexOf(minCaption);
                                copieIdCaption.remove(indexSuppr);
                                nbMemeCaption += 1;
                        }

                        System.out.println(nbMemeCaption);
                        for(int i=0; i < idCaption.size(); i++){
                                if(minCaption.equals(idCaption.get(i))){
                                        System.out.print(idCaption.get(i).replaceAll(suppr,""));
                                        System.out.print(",");
                                        System.out.print(idNames.get(i).replaceAll(suppr,""));
                                        System.out.print(",");
                                        System.out.print(idValues.get(i).replaceAll(suppr,""));
                                        System.out.print(",");
                                        System.out.println(idCats.get(i).replaceAll(suppr,""));
                                }
                        }
                        System.out.println("");
                }
                System.out.println("");
                System.out.println("");

        } catch (FileNotFoundException e) {
                System.out.println("Fichier non trouvé");
        }
    }
}