package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO {
	
			
		Article selectById(int id) throws DALException;
		List<Article> selectAll() throws DALException;
		boolean update(Article article) throws DALException;
		void insert(Article art) throws DALException;
		void delete(int idArticle) throws DALException;
		
	
}
