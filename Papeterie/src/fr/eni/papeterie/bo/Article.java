package fr.eni.papeterie.bo;

public abstract class Article {
	
	//Attributs d'instance
		private Integer idArticle;
		private String reference;
		private String marque;
		private String designation;
		private float prixUnitaire;
		private int qteStock;
		
			
	//Constructeurs
		public Article(Integer idArticle, String reference, String marque, String designation, float prixUnitaire,
				int qteStock) {
			super();
			this.idArticle = idArticle;
			this.reference = reference;
			this.marque = marque;
			this.designation = designation;
			this.prixUnitaire = prixUnitaire;
			this.qteStock = qteStock;
		}
		
		public Article(String reference, String marque, String designation, float prixUnitaire, int qteStock) {
			super();
			this.reference = reference;
			this.marque = marque;
			this.designation = designation;
			this.prixUnitaire = prixUnitaire;
			this.qteStock = qteStock;
		}
		
		public Article() {
			super();
		}
				
		
		//Getters et Setters
		public Integer getIdArticle() {
			return idArticle;
		}
		
		public void setIdArticle(Integer idArticle) {
			this.idArticle = idArticle;
		}
		
		public String getReference() {
			return reference;
		}

		public void setReference(String reference) {
			this.reference = reference;
		}

		public String getMarque() {
			return marque;
		}

		public void setMarque(String marque) {
			this.marque = marque;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public float getPrixUnitaire() {
			return prixUnitaire;
		}

		public void setPrixUnitaire(float prixUnitaire) {
			this.prixUnitaire = prixUnitaire;
		}

		public int getQteStock() {
			return qteStock;
		}

		public void setQteStock(int qteStock) {
			this.qteStock = qteStock;
		}
		
		
		//Méthodes
		@Override
		public String toString() {								//Méthode toString générer auto en StringBuilder et non en concaténation
			StringBuilder builder = new StringBuilder();
			builder.append("Article [idArticle=");
			builder.append(idArticle);
			builder.append(", reference=");
			builder.append(reference);
			builder.append(", marque=");
			builder.append(marque);
			builder.append(", designation=");
			builder.append(designation);
			builder.append(", prixUnitaire=");
			builder.append(prixUnitaire);
			builder.append(", qteStock=");
			builder.append(qteStock);
			builder.append("]");
			return builder.toString();
		}
		
		
		

		
		

}
