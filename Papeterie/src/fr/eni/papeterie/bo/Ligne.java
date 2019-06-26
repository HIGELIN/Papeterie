package fr.eni.papeterie.bo;

public class Ligne {
	
	//Attributs
	protected int qte;
	private Article article;		//code le lien entre les deux classes Ligne et Article

	
	//Constructeurs
	public Ligne(Article article, int qte) {		//l'ordre de article puis qte doit être identique au diagramme de classe
		super();
		this.qte = qte;
		this.article = article;
	}

	
	//Getters et Setters
	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
		
	public float getPrix() {
		float prix = article.getPrixUnitaire();
		return prix;
		
	}


	
	//Méthodes
	@Override
	public String toString() {
		return String.format("Ligne [qte=%s, article=%s, getArticle()=%s]", qte, article, getArticle());
	}


	

	
	
	

}
