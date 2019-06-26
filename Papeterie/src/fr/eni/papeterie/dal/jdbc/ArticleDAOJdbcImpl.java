/**
 * 
 */
package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;

/**
 * @author Eni Ecole
 * 
 */
public class ArticleDAOJdbcImpl {
	
	private static final String SQL_SELECT_BY_ID = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type from Articles where idArticle = ?";
	private static final String SQL_INSERT = "insert into articles(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ALL = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type from Articles";
	private static final String SQL_DELETE = "delete from Articles where idArticle = ?";
	private static final String SQL_UPDATE = "update Articles set reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?, grammage=?, couleur=? where idArticle=?";
	private static final String RAMETTE = "RAMETTE";
	private static final String STYLO = "STYLO";
	
	
	
	//Méthodes	
	public Article selectById(int id)  throws DALException {
		
		Connection uneConnection = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Article article = null;
		
		try {
			// Chargement du pilote Jdbc
			DriverManager.registerDriver(new SQLServerDriver());
			
			//Obtenir une connection
			String url = "jdbc:sqlserver://localhost:1433;databaseName=PAPETERIE_DB";
			uneConnection = DriverManager.getConnection(url, "sa", "Pa$$w0rd");
								
			rqt = uneConnection.prepareStatement(SQL_SELECT_BY_ID);
			
			rqt.setInt(1, id);
						
			rs = rqt.executeQuery();
			
			if (rs.next()) {
				//Création instance de Stylo
				if (STYLO.equals(rs.getString("type").trim())) {
					article = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"), rs.getString("couleur"));
				}
				//Création instance de Ramette
				if (RAMETTE.equals(rs.getString("type").trim())) {
					article = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"), rs.getInt("grammage"));
				}
			}			
			return article;
		} catch (SQLException e) {
				throw new DALException("selectById failed - id = " + id, e);
		} finally {
			try {
				if (rqt != null) {
						rqt.close();
				}
				if (uneConnection != null) {
						uneConnection.close();
				}
			} catch (SQLException e) {
				throw new DALException("selectById failed - id = " + id, e);
			}
		}
	}
		
	
	public List<Article> selectAll() throws DALException {
	
		
		List<Article> listeArticle = new ArrayList<>();
		String url = "jdbc:sqlserver://localhost:1433;databaseName=PAPETERIE_DB";
		
		try (Connection uneConnection = DriverManager.getConnection(url, "sa", "Pa$$w0rd"); Statement stmt = uneConnection.createStatement();) {
					//try puis () : permet de gérer automatiquement la fermeture de ces deux connections
			
			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);
			
			Article article = null;
			
			while (rs.next()) {
				if (rs.next()) {
					//Création instance de Stylo
					if ("STYLO".equals(rs.getString("type").trim())) {
						article = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"),
								rs.getString("designation"), rs.getFloat("prixUnitaire"),
								rs.getInt("qteStock"), rs.getString("couleur"));
					}
					//Création instance de Ramette
					if ("RAMETTE".equals(rs.getString("type").trim())) {
						article = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"),
								rs.getString("designation"), rs.getFloat("prixUnitaire"),
								rs.getInt("qteStock"), rs.getInt("grammage"));
					}
					listeArticle.add(article);
				}
			}
						

		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		} 
		
	
		return listeArticle;
	}
		
	
	public boolean update(Article article) throws DALException {
		
		String url = "jdbc:sqlserver://localhost:1433;databaseName=PAPETERIE_DB";
		
		boolean resultat = false;
				
		try (Connection uneConnection = DriverManager.getConnection(url, "sa", "Pa$$w0rd"); PreparedStatement rqt = uneConnection.prepareStatement(SQL_UPDATE);) {
			
			rqt.setString(1, article.getReference());
			rqt.setString(2, article.getMarque());
			rqt.setString(3, article.getDesignation());
			rqt.setFloat(4, article.getPrixUnitaire());
			rqt.setInt(5, article.getQteStock());
			if (article instanceof Ramette) {
				rqt.setInt(6, ((Ramette) article).getGrammage());				//Transtypage descendant vers ramette (cast)
				rqt.setNull(7, Types.NVARCHAR);									// méthode setNull car la couleur n'est pas renseigné
			}
			if (article instanceof Stylo) {
				rqt.setNull(6, Types.INTEGER);
				rqt.setString(7, ((Stylo) article).getCouleur());
			}
			rqt.setInt(8,  article.getIdArticle());
			
			int nbLignesImpactees = rqt.executeUpdate();
			
			if (nbLignesImpactees > 0) {
				resultat = true;
			}			
			
		} catch (SQLException e) {
			throw new DALException("Update article failed - article = " + article, e);
		}
		return resultat;
	}
		
	
	public void insert(Article art) throws DALException {
		Connection uneConnection = null;
		PreparedStatement pStmt = null;
		
		
		try {
			//Chargement du driver Jdbc
			DriverManager.registerDriver(new SQLServerDriver());
			
			//Ouverture de la connection à la base de données
			String url = "jdbc:sqlserver://127.0.0.1;databaseName=PAPETERIE_DB";
			uneConnection = DriverManager.getConnection(url, "sa", "Pa$$w0rd");
			
			//Création de la requete
			pStmt = uneConnection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			
			//Valoriser les paramètres de la requete
			pStmt.setString(1, art.getReference());
			pStmt.setString(2, art.getMarque());
			pStmt.setString(3, art.getDesignation());
			pStmt.setFloat(4, art.getPrixUnitaire());
			pStmt.setInt(5, art.getQteStock());
			if (art instanceof Ramette) {
				pStmt.setInt(6, ((Ramette) art).getGrammage());				//Transtypage descendant (cast)
				pStmt.setNull(7, java.sql.Types.NVARCHAR);
				pStmt.setString(8,  RAMETTE);
			}
			if (art instanceof Stylo) {
				pStmt.setNull(6, java.sql.Types.INTEGER);
				pStmt.setString(7, ((Stylo) art).getCouleur());
				pStmt.setString(8,  STYLO);
			}
			
			//Exécution de la requete
			pStmt.executeUpdate();
			
			ResultSet rsId = pStmt.getGeneratedKeys();
			
			if (rsId.next()) {
				art.setIdArticle(rsId.getInt(1));
			}
				
		} catch (SQLException e) {
			throw new DALException("Erreur à l'ajout d'un article : " +art, e);
			
		} finally {
			try {
				if (pStmt != null) {
					pStmt.close();
				}
				if (uneConnection != null) {
					uneConnection.close();
				}
			} catch (SQLException e) {
				throw new DALException("Erreur à l'ajout d'un article : " +art, e);
			}
		}
	}
		
	
	public void delete(int idArticle) throws DALException {
		
		String url = "jdbc:sqlserver://127.0.0.1;databaseName=PAPETERIE_DB";
		try (Connection uneConnection = DriverManager.getConnection(url, "sa", "Pa$$w0rd"); PreparedStatement rqt = uneConnection.prepareStatement(SQL_DELETE);) {
		
			rqt.setInt(1,  idArticle);
			rqt.executeUpdate();
				
		} catch (SQLException e) {
			throw new DALException("Delete article failes - id = " + idArticle, e);
		}	
	}


}
