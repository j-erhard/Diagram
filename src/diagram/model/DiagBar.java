package diagram.model;

import java.util.*;
import java.awt.*;

public class DiagBar {

private final String title;               // bar chart title
private final String xAxisLabel;          // x-axis label
private final String dataSource;          // data source
private String caption;                   // caption
private ArrayList<String> names;          // list of bar names
private ArrayList<Integer> values;        // list of bar values
private ArrayList<Color> colors;          // list of bar colors


    /**
    * Créé un nouveau diagramme à barre.
    *
    * @param title le titre
    * @param xAxisLabel la légende de l'axe horizontal
    * @param dataSource l'origine des données
    */
    public DiagBar(String title, String xAxisLabel, String dataSource) {
       if (title == null) throw new IllegalArgumentException("name is null");
       if (xAxisLabel == null) throw new IllegalArgumentException("x-axis label is null");
       if (dataSource == null) throw new IllegalArgumentException("data source is null");
       this.title = title;
       this.xAxisLabel = xAxisLabel;
       this.dataSource = dataSource;
       reset();
    }

    /**
    * Permet de réinitialiser un DiagBar
    */
    public void reset() {
        //initialise les listes names, values et colors
        names = new ArrayList <String>();
        values = new ArrayList <Integer>();
        colors = new ArrayList <Color>();

        //efface l'écran en blanc
        StdDraw.clear();
    }

    /**
    * Sets the caption of this bar chart.
    * The caption is drawn in the lower-right part of the window.
    *
    * @param caption the caption
    */
    public void setCaption(String caption) {
    // Affecte le parametre caption dans la variable caption (this.caption car elle c'est une variable commune)
    this.caption = caption;
    }

    /**
    * Ajoute une barre au diagramme.
    * La longueur de la barre est proportionnelle à sa valeur.
    * Les barre sont tracées de haut en bas dans l'ordre où elle sont ajoutées.
    * Toutes les barres d'une même catégorie sont de la même couleur.
    *
    * @param name     le nom de la barre
    * @param value    la valeur de la barre
    * @param category la catégorie de la barre
    */
    public void add(String name, int value, String category) {
        // ajoute name et catégory dans la liste name et ajoute value dans la liste value
        names.add(name);
        names.add(category);
        values.add(value);

        // taille de la liste value (= colors.size() = 1/2 * names.size())
        int tailleListe = values.size();

        // créer des couleurs aléatoire red green et blue pour les barres
        int r = (int)(Math.random()*156)+100;
        int g = (int)(Math.random()*156)+100;
        int b = (int)(Math.random()*156)+100;
        // ajoute la couleurs aléatoire dans la liste colors
        colors.add(new Color(r, g, b));

        // affect la même couleurs au barres de même catégorie
        for (int i=0; i<=tailleListe-1; i++) {
            if ((names.get(i*2+1).compareTo(category)) == 0) {
                colors.set(tailleListe-1, colors.get(i));
            }
        }
    }

    public void draw() {
        // créer des liste qui vont nous permettre d'insérer les derniers ajouts des liste names, values et colors
        ArrayList<String> names2 = new ArrayList <String>();
        ArrayList<Integer> values2 = new ArrayList <Integer>();
        ArrayList<Color> colors2 = new ArrayList <Color>();

        // inverse les liste names, values et colors pour que l'on puisse récupérer les derniers ajouts
        Collections.reverse(names);
        Collections.reverse(values);
        Collections.reverse(colors);

        // copie les dernier ajout des barres dans les nouvelles listes
        if (names.contains("STOP")){
            int placeStop = names.indexOf("STOP");
            placeStop /= 2;
            for (int i=0; i<placeStop; i++){
                names2.add(names.get(i*2+1));
                values2.add(values.get(i));
                colors2.add(colors.get(i));
            }
        } else {
            // sinon si c'est le premier ajout, copie seulement les listes
            for (int i=0; i<values.size(); i++){
                names2.add(names.get(i*2+1));
            }
            values2 = values;
            colors2 = colors;
            Collections.reverse(colors2);
        }
        // inverse anouveau les 6 listes
        Collections.reverse(names2);
        Collections.reverse(colors2);
        Collections.reverse(values2);
        Collections.reverse(names);
        Collections.reverse(values);
        Collections.reverse(colors);

        //Met en place l'Echelle de l'écran (1000;700)
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 700);

        //Créer des polices d'écriture
        Font font1 = new Font("SANS", Font.BOLD, 30);
        Font font2 = new Font("SANS", Font.BOLD, 40);
        Font font3 = new Font("SANS", Font.PLAIN, 16);
        Font font4 = new Font("SANS", Font.PLAIN, 12);
        Font font5 = new Font("SANS", Font.PLAIN, 16);

        /* ***********************************
         *      Affichage du Diagramme       *
         *********************************** */
        int valeurMax = Collections.max(values2);        // valeur maximum des valeurs des barres
        int nbBar = values2.size();                      // nombre de barre à afficher
        double hauteur = 550.0;                          // auteur du diagbar
        double largueur = 900.0;                         // largueur du diagbar
        int marge = 20;                                  // marge de droite et de gauche pour l'affichage des barres
        int tailleBar = 42*((int)hauteur/nbBar)/100;     // hauteur d'une barre (en fonction du nombre de barre)

        //Affichage echelle et valeurs de l'echelle
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setPenRadius(0.001);
        StdDraw.setFont(font5);
        int valeurEchelle = valeurMax;
        int nbEchelle = 0;
        int valeurNbEchelle =0;
        while(valeurEchelle>=100){
            valeurEchelle = valeurEchelle /10;
            nbEchelle += 1;
            valeurNbEchelle = valeurEchelle;
        }
        if(valeurNbEchelle<20){
            valeurNbEchelle = 2;
        }if(valeurNbEchelle<50){
            valeurNbEchelle = 5;
        }else if(valeurNbEchelle<70){
            valeurNbEchelle = 10;
        }else{
            valeurNbEchelle = 20;
        }
        while(nbEchelle>0){
            valeurNbEchelle = valeurNbEchelle*10;
            nbEchelle -= 1;
        }
        while(nbEchelle*valeurNbEchelle<valeurMax){
            nbEchelle += 1;
        }
        for (int i=0; i < nbEchelle; i++) {
            StdDraw.line(marge + (largueur / valeurMax) * valeurNbEchelle * i, marge, marge + (largueur / valeurMax) * valeurNbEchelle *i, 570);
            StdDraw.text(marge + (largueur / valeurMax) * valeurNbEchelle * i, 590, Integer.toString(valeurNbEchelle*i));
        }

        // AFFICHAGE des diagrammes, des noms et des valeurs
        for (int i=0; i<nbBar; i++) {
            int valeur = values2.get(i);
            //affichage diagrammes
            StdDraw.setPenColor(colors2.get(i));
            StdDraw.filledRectangle(marge+(values2.get(i))*largueur/(2*valeurMax), (hauteur/nbBar)*(nbBar-(i+1))+tailleBar+marge, (values2.get(i))*450.0/(valeurMax), tailleBar);
            //affichage noms
            StdDraw.setFont(font3);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.textRight(10+(valeur*910.0/valeurMax), (hauteur/nbBar)*(nbBar-(i+1))+tailleBar+marge, names2.get((i)));
            //affichage valeurs
            StdDraw.setFont(font4);
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.textLeft(marge+(valeur*910.0/valeurMax), (hauteur/nbBar)*(nbBar-(i+1))+tailleBar+marge, Integer.toString(values2.get(i)));
        }
        //écriture du titre
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(font1);
        StdDraw.text(500, 650, title);
        //écriture de la date
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setFont(font2);
        StdDraw.text(800, 90, caption);
        //écriture de la source
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setFont(font4);
        StdDraw.text(800, 50, dataSource);
        //écriture de la légende des axes
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setFont(font3);
        StdDraw.textLeft(40, 610, xAxisLabel);
        }
        
    // Exemple pour mise au point
    public static void main(String[] args) {
        // création du diagramme
        String title = "Famous brands";
        String xAxis = "stock value $(million)";
        String source = "Source: Interbrand website";
        DiagBar diag = new DiagBar(title, xAxis, source);
        diag.setCaption("2000-01-01");

        // ajout des barres suivantes au diagramme
        diag.add("adidas", 3791, "Sporting Goods");
        diag.add("Amazon", 4528, "Retail");
        diag.add("American Express", 16122, "Financial Services");
        diag.add("AOL", 4531, "Media");
        diag.add("Apple", 6594, "Technology");
        diag.add("AT&T", 25548, "Telecommunications");
        diag.add("Bacardi", 3187, "Alcohol");
        diag.add("Barbie", 2315, "Toys & Games");
        diag.add("BMW", 12969, "Automotive");
        diag.add("BP", 3066, "Energy");
        diag.add("Budweiser", 10684, "Alcohol");
        diag.add("Burger King", 2701, "Restaurants");
        diag.add("Chanel", 4141, "Luxury");

        // rendu du diagramme
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();
        diag.draw();
        StdDraw.show();
    }
}