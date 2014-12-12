package fr.univfcomte.LadierCoursier.ProjetMOP2014;

import java.util.Set;

public class Skieur implements Comparable<Skieur>{
	private String nom;
	private int anneeNaissance;
	private String club;
	private String nation;
	private Set <ParticipeCourse> pc;
	
	Skieur(String nom, int anneeNaissance, String club, String nation) {
		this.nom=nom;
		this.anneeNaissance=anneeNaissance;
		this.club=club;
		this.nation=nation;
	}

	public int compareTo(Skieur s) {
		return nom.compareTo(s.getNom());
	}

	public String toString() {
		return " Skieur "+nom+" né en "+anneeNaissance;
	}
	
	private String getNom() {
		return nom;
	}

}
