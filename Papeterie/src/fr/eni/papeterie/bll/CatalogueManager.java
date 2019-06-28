package fr.eni.papeterie.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {
	
	
	//Attributs
	private ArticleDAO daoArticle;
	private List<Article> catalogue;
	
	
	//Constructeur
	public CatalogueManager() {
		super();
		this.daoArticle = DAOFactory.getArticleDAO();
	}



	//Getters
	public List<Article> getCatalogue() throws BLLException {
		try {
			catalogue = daoArticle.selectAll();
		} catch (DALException e) {
			throw new BLLException("getCatalogue failed" , e);
		}
		return catalogue;
	}
	
	
	//Méthodes
	public void addArticle(Article a) throws BLLException {
		try {
			daoArticle.insert(a);
		} catch (DALException e) {
			throw new BLLException("addArticle failed - article = " + a, e);
		}
		
	}
	
	public void updateArticle(Article a) throws BLLException {
		try {
			daoArticle.update(a);
		} catch (DALException e) {
			throw new BLLException("updateArticle failed - article = " + a, e);
		}
	}
	
	
	public void removeArticle(int index) throws BLLException {
		try {
			daoArticle.delete(index);
		} catch (DALException e) {
			throw new BLLException("removeArticle failed - index = " + index, e);
		}
	}
	
	
	public void validerArticle(Article a) throws BLLException {
		
	}
	
	
	public void getArticle(int index) throws BLLException {
		try {
			daoArticle.selectById(index);
		} catch (DALException e) {
			throw new BLLException("getArticle failed - index = " + index, e);
		}

	}
	
	
	
	
	

}
