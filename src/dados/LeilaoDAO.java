package dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bdLeilao.Leilao;

public class LeilaoDAO {
	private Connection conexao;
	public LeilaoDAO() throws SQLException{
		conexao = DriverManager.getConnection(BDInfo.SERVER, BDInfo.USER, BDInfo.PASSWORD);
}
	
			 public void inserirNovaPessoa(String cpf, String email) throws SQLException{
			        Scanner in = new Scanner(System.in);      
			        Connection conexao = DriverManager.getConnection(BDInfo.SERVER, BDInfo.USER, BDInfo.PASSWORD); 
			        String comandoSQL = "INSERT INTO USERSLEILAO(cpf, email) VALUES (?, ?)";
			        PreparedStatement stmt = conexao.prepareStatement(comandoSQL); //se perapar para mandar o comando comandoSQL no banco
			        
			        stmt.setString(1,cpf);
			        stmt.setString(2,email);
			        stmt.execute();
			        stmt.close();
			    }
			 public void inserirNovoLance(String cpf, float value) throws SQLException{
			        Scanner in = new Scanner(System.in);      
			        Connection conexao = DriverManager.getConnection(BDInfo.SERVER, BDInfo.USER, BDInfo.PASSWORD); 
			        String comandoSQL = "INSERT INTO LANCESLEILAO(cpf, value) VALUES (?, ?)";
			        PreparedStatement stmt = conexao.prepareStatement(comandoSQL); //se perapar para mandar o comando comandoSQL no banco
			        
			        stmt.setString(1,cpf);
			        stmt.setDouble(2,value);
			        stmt.execute();
			        stmt.close();
			    }
			 
			 public Leilao inserirNovoLeilao(String ownerUser,  float minimunPrice, String shortDescription, String longDescription, String categoryy ) throws SQLException{
			        Scanner in = new Scanner(System.in);      
			        Connection conexao = DriverManager.getConnection(BDInfo.SERVER, BDInfo.USER, BDInfo.PASSWORD); 
			        String comandoSQL = "INSERT INTO DETALHESLEILAO( ownerUser,   minimunPrice,  shortDescription,  longDescription,  categoryy ) VALUES (?, ?, ?, ?, ?)";
			        PreparedStatement stmt = conexao.prepareStatement(comandoSQL); //se perapar para mandar o comando comandoSQL no banco
			       
			        stmt.setString(1,ownerUser);
			        stmt.setFloat(2,minimunPrice);
			        stmt.setString(3,shortDescription);
			        stmt.setString(4,longDescription);
			        stmt.setString(5,categoryy);
			        stmt.execute();
			        stmt.close();
			        String comandoSQL2 = "SELECT DETALHESLEILAO.CURRVAL FROM DUAL";
			        PreparedStatement stmt2 = conexao.prepareStatement(comandoSQL2); //se perapar para mandar o comando comandoSQL no banco
			        ResultSet id = stmt2.executeQuery(); 
			        String id2 = id.getString(1);
			        stmt2.close();
			        return new Leilao(id2,  ownerUser,   minimunPrice,  shortDescription,  longDescription,  categoryy );
			    }
			 public ArrayList< Leilao> visualizarDetalhesLeilaoEncerrado(String id) throws SQLException {
				 ArrayList< Leilao> leiloes = new  ArrayList< Leilao>();
			        String comandoSQL = "SELECT * FROM DETALHESLEILAO WHERE CURRVAL = " +"''"+id +"''";
			        PreparedStatement consultaSQL = conexao.prepareStatement(comandoSQL); 
			        //mando executar a consulta
			        ResultSet registros = consultaSQL.executeQuery(); 

			        while(registros.next()){
			            String ownerUser = registros.getString("ownerUser");
			            Float minimunPrice = registros.getFloat("minimunPrice");
			            String shortDescription = registros.getString("shortDescription");
			            String longDescription = registros.getString("shortDescription");
			            String categoryy =registros.getString("categoryy");
			            leiloes.add(new Leilao(id, ownerUser, minimunPrice, shortDescription, longDescription, categoryy));
			        }
			        //fecha tudo!
			        registros.close();
			        consultaSQL.close();
			        return leiloes;
			 }
			 public HashMap<String, Float> listarTodosLances() throws SQLException{
					HashMap<String, Float> lances = new HashMap<String, Float>();
			        String comandoSQL = "SELECT cpf, value FROM LANCESLEILAO ORDER BY value";
			        PreparedStatement consultaSQL = conexao.prepareStatement(comandoSQL); 
			        //mando executar a consulta
			        ResultSet registros = consultaSQL.executeQuery(); 

			        while(registros.next()){
			            String cpf = registros.getString("cpf");
			            Float value = registros.getFloat("value");
			            lances.put(cpf, value);
			        }
			        //fecha tudo!
			        registros.close();
			        consultaSQL.close();
			        return lances;
			    }
//				public void LeiloesTerminados(String ownerUser, String winnerUser, float minimunPrice,String shortDescription, String longDescription, String categoryy) throws SQLException {     
//		        Connection conexao = DriverManager.getConnection(BDInfo.SERVER, BDInfo.USER, BDInfo.PASSWORD); 
//		        String comandoSQL = "INSERT INTO LEILOESTERMINADOS( ownerUser, winnerUser,  minimunPrice,  shortDescription,  longDescription,  categoryy ) VALUES (?, ?, ?, ?, ?)";
//		        PreparedStatement stmt = conexao.prepareStatement(comandoSQL); 
//		       
//		        stmt.setString(1,ownerUser);
//		        stmt.setString(2,winnerUser);
//		        stmt.setFloat(3,minimunPrice);
//		        stmt.setString(4,shortDescription);
//		        stmt.setString(5,longDescription);
//		        stmt.setString(6,categoryy);
//		        stmt.execute();
//		        stmt.close();
//					
//				}
//			 public Leilao fetchLeilaoInfo() {
//				 return Leilao
//			 }
				
				public void sair(String winnerUser, String codLeilao) throws SQLException{
					//Deleta dados das tabelas e seta vencedor
					 String comandoSQL = "ALTER TABLE DETALHESLEILAO SET winnerUser =" +"'"+ winnerUser +"'"+ "where codLeilao = " +"'"+  codLeilao +"'";
				     PreparedStatement stmt = conexao.prepareStatement(comandoSQL); //se perapar para mandar o comando comandoSQL no banco
				     String comandoSQL2 = "DELETE * FROM LANCESLEILAO";
				     PreparedStatement stmt2 = conexao.prepareStatement(comandoSQL2); //se perapar para mandar o comando comandoSQL no banco
				     stmt.execute();
				     stmt.close();
				     stmt2.execute();
				     stmt2.close();
				     conexao.close();
				}

}