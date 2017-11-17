package bdLeilao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import dados.LeilaoDAO;

public class Leilao {
	
	private String id; //leilao tem id do banco, ele é null no inicio e o bacoo devolve o nro dele
	private String ownerUser; //cpf do usuario q eh o owner
	private float minimunPrice;
	private String winnerUser; //usuario vencedor(est� sempre sendo atualizado p o q d� o maior lance)
	private String shortDescription;
	private String longDescription;
	private String categoryy;
	private LeilaoDAO banco;
	private Lances lance;
	private HashMap<String, Double> Lances;
	
	public Leilao(String id,  String ownerUser,  float minimunPrice, String shortDescription, String longDescription, String categoryy ) throws SQLException{
	//manda para o banco salvar o leilao criado
		banco = new LeilaoDAO();
		banco.inserirNovoLeilao(ownerUser, minimunPrice, shortDescription, longDescription, categoryy);
		this.ownerUser = ownerUser;
		this.minimunPrice = minimunPrice;
		this.shortDescription = shortDescription;
		this.longDescription =  longDescription;
		this.categoryy = categoryy;
		this.id = id;
	}
	
	public void endLeilao() throws SQLException {
		//seta o vencedor, da drop em todos dados da tabela para ter uma tabela nova pra os proximos e encerra a conex�o
		this.winnerUser = actualWinner();
		//banco.LeiloesTerminados(ownerUser,winnerUser, minimunPrice,shortDescription,longDescription,categoryy);
		banco.sair();
	}
	public void saveNewLance( User user, float value) throws SQLException {
		//salva no banco novos lances que a pessoa tenha feito]
		Boolean createLance = true;
		for (Entry<String, Double> pair : Lances.entrySet()) {
			if(pair.getValue() == value || value < minimunPrice) {
				createLance = false;
			}
		}
		if(createLance) {
			lance.createLance( user, value);	
		}
		else {
			System.out.println("Valor para lance deve ser maior do que lances j� existentes e maior do que o valor minimo");
		}
	}
	public void cancelLance(User user, Double value) {
		
	}
	public void showAllLances() throws SQLException {
		//mostra todos os lances j� feitos(pega do banco)
		this.Lances = banco.listarTodosLances();
		
	}
	public HashMap<String, Double> returnLances(){
		return Lances;
	}
	public LeilaoDAO returnLeilao(){
		return banco;
	}
	public String actualWinner() {
		//diz quem � o vencedor no momento
		Double maior = 0.0;
		String winner = null;
		for (Entry<String, Double> pair : Lances.entrySet()) {
			if(pair.getValue() > maior) {
				maior = pair.getValue();
				winner = pair.getKey();
			}
		}
		return winner;
		
	}
}