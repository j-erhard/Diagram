**************************************************************************
**               Ce projet a été réalisé par Julien ERHARD              **
**                       pour le défi S1 en 2020                        **
**                    en DUT informatique de Belfort                    **
**************************************************************************

--------------------------------------------------------------------------
|                         Ce dossier contient:                           |
|                                                                        |
|  DiagBar.java | Bar.java | AnimatedDiagBar.java | PreprocessData.java  |
--------------------------------------------------------------------------

Pour éxécuter ces programmes vous devez compiler chaque programme.

=> StdDraw.java est une bibliothèque java.
	* compilation:
		java DiagBar

=> DiagBar.java contient une classe qui permet de représenter un diagramme à barres.
	* compilation:
		javac DiagBar.java
	* exécution:
		java DiagBar
		
=> Bar.java contient une classe qui permet de représenter une barre au sein d'un diagramme.
   Une barre est caractérisé par ses atributs (name, value, category). La classe Bar
   comporte les méthode classiques d'accès à ses atributs et permets de les trier par
   valeurs
   	* compilation:
   		javac Bar.java
   	* exécution:
   		java Bar

=> AnimatedDiagBar.java contient une classe qui permet de représenter un diagramme animé à partir
   des classes précédentes: DiagBar.java Bar.java
   	* compilation:
   		javac AnimatedDiagBar.java
   	* exécution:
   		java AnimatedDiagBar <dataFile> <nBars> <sort|nosort>
   			dataFile      -> le chemin vers le fichier de données
   			nBars         -> le nombre de barres à afficher
   			sort|nosort   -> indique si les barres affichées sont triées
   	  	exemple: java AnimatedDiagBar outFile 13 sort

=> PreprocessData.java contient une classe qui permet de construire un fichier de données au format
   adapté à la classe AnimatedDiagBar.
   	* compilation:
   		javac PreprocessData.java
   	* exécution:
   		java PreprocessData <dataFile> <idCaptions> <idNames> <idValues> <idCats> [ > outFile ]
   			dataFile      -> le chemin vers le fichier de données .csv
   			idCaptions    -> indice de la colonne contenant les titres des diagrammes
   			idNames       -> indice de la colonne contenant les noms des barres
   			idValues      -> indice de la colonne contenant les valeurs des barres
   			idCats        -> indice de la colonne contenant les valeurs des catégories
   			outFile       -> fichier généré
   		exemple: java PreprocessData outFile population.csv Time Location PopTotal Variant > outFile
   		
