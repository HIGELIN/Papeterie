package fr.eni.papeterie.bo;

public class Stylo extends Article {
	
	//Attributs
	private String couleur;

	
	
	//Constructeurs
	public Stylo(Integer idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock,
			String couleur) {
		super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
		this.couleur = couleur;
	}
	
	public Stylo(String reference, String marque, String designation, float prixUnitaire, int qteStock,
			String couleur) {
		super(reference, marque, designation, prixUnitaire, qteStock);
		this.couleur = couleur;
	}

	public Stylo() {
		super();
	}

	
	
	//Getters et Setters
	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	
	
	//Méthodes
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stylo [toString=");
		builder.append(couleur.toString());
		builder.append(", couleur=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}


	

}
