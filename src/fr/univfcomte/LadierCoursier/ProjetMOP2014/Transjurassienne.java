package fr.univfcomte.LadierCoursier.ProjetMOP2014;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Transjurassienne {
	List<CourseParAnnee> listCourses;
	TreeSet<Skieur> listSkieurs;
	int nbFichiersCsv;

	Transjurassienne() {
		listSkieurs = new TreeSet<Skieur>();
		remplirListSkieurs();
		listCourses = new ArrayList<CourseParAnnee>(nbFichiersCsv);
		remplirListCourse();
		System.out.println(listSkieurs.first().toString());
	}
	
	private void remplirListCourse() {
		for (int i=0;i<nbFichiersCsv;i++) {
			listCourses[i] = new CourseParAnnee();
		}
	}

	void remplirListSkieurs() {
		//on ouvre et on parcourt le dossier
		File repertoire = new File(".csv");  
		nbFichiersCsv=0;
		String [] listefichiers;
		listefichiers=repertoire.list();
		
		//pour chaque fichier
		for(int i=0;i<listefichiers.length;i++) {
		    System.out.println(listefichiers[i]);
		    //si le fichier est un .csv
		    if(listefichiers[i].endsWith(".csv")==true){
			    	try {  
			    		nbFichiersCsv++;
			    		int k=0;
			          String ligne;
			          BufferedReader lecteurAvecBuffer = new BufferedReader(new FileReader(".csv/"+listefichiers[i]));
			          //pour chaque ligne du fichier
			          	while ((ligne = lecteurAvecBuffer.readLine()) != null) {
			          		//pour éviter la première ligne des fichiers csv
			          	  if (k!=0) {
				        	  String[] items=ligne.split(";");
				        	  String nom="", club="", nation="";
				        	  int anneeNaissance=0;
				        	  boolean sexe=false; //false = homme ; true = femme
				        	  //pour chaque item de chaque ligne
				              for (int j=0;j<items.length;j++) {
				            	  switch (j) {
				            	  	case 2 : 
				            	  		nom=items[j];
				            	  	break;
				            	  	case 3 :
				            	  		anneeNaissance = Integer.parseInt(items[j]); 
				            	  	break;
				            	  	case 4 :
				            	  		club=items[j];
				            	  	break;
				            	  	case 5 :
				            	  		nation=items[j];
				            	  	break;
				            	  	case 7 :
				            	  		if (items[j].charAt(items[j].length()-1)=='M') sexe=false;
				            	  		else sexe=true;
				            	  	break;
				            	  }
				              }
				              if (sexe) listSkieurs.add(new SkieurFemme(nom,anneeNaissance,club,nation));
				              else listSkieurs.add(new SkieurHomme(nom,anneeNaissance,club,nation));
			          	  }
			          	  k++;
			            }
			          	lecteurAvecBuffer.close();
			    	}
			    	catch(Exception e) {
			            System.out.println("Erreur fichier : "+e.getMessage()+" "+e.getLocalizedMessage());
			        }
		    }
		}
		//rectificatif d'une erreur sur le premier élément
		listSkieurs.remove(listSkieurs.first());
	}
	
}
