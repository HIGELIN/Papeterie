package fr.eni.papeterie.bo;

public class Ramette extends Article {
	
	//Attributs
	private int grammage;
	
	
	
	//Constructeurs
	public Ramette(Integer idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock,
			int grammage) {
		super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
		this.grammage = grammage;
	}
		
	public Ramette(String reference, String marque, String designation, float prixUnitaire, int qteStock,
			int grammage) {
		super(reference, marque, designation, prixUnitaire, qteStock);
		this.grammage = grammage;
	}

	public Ramette() {
		super();
	}

	
	
	//Getters et Setters
	public int getGrammage() {
		return grammage;
	}

	public void setGrammage(int grammage) {
		this.grammage = grammage;
	}

		
	
	//Méthodes
	@Override
	public String toString() {
		return String.format("Ramette [grammage=%s, toString()=%s]", grammage, super.toString());
	}
	
	

}
