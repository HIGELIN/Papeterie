package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	
	
	//Attributs
	private float montant;	
	private List<Ligne> lignesPanier;
	
	
	
	//Constructeurs
	public Panier() {
		lignesPanier = new ArrayList<Ligne>();
	}

	
	
	//Getters et Setters
	public float getMontant() {
		return montant;
	}

	public Ligne getLigne(int index) {
		Ligne l = null;						//
		l = lignesPanier.get(index);		//	ou = return lignesPanier.get(index);
		return l;							//
	}

	public List<Ligne> getLignesPanier() {
		return lignesPanier;
	}

	
	
	//Méthodes
		
	public void addLigne (Article article, int qte) {
		Ligne e = new Ligne(article, qte);
		lignesPanier.add(e);
	}
		
	public void updateLigne (int index, int newQte) {
		Ligne l = getLigne(index);
		l.setQte(newQte);
	}
	
	public void removeLigne (int index) {
		lignesPanier.remove(index);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Panier :\n");
		for (Ligne ligne : lignesPanier) {
			sb.append("ligne ");
			sb.append(lignesPanier.indexOf(ligne));
			sb.append(" : \t");
			sb.append(ligne.toString());
			sb.append("\n");
		}
		sb.append("\n");
		sb.append("Valeur du panier : ").append(getMontant()).append("\n");
		return sb.toString();
		
	}
	
	
	
	
	
	
	

}
