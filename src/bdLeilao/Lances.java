package bdLeilao;

import java.sql.SQLException;

import dados.LeilaoDAO;

public class Lances {
	private User user;
	private float value;

	private LeilaoDAO banco;
	
	public Lances( User user, float value) throws SQLException {
		//cria um lance no banco
		banco.inserirNovoLance(user.getCPF(), value);
		this.user = user;
		this.value = value;
	}
	public void createLance(User user, float value) throws SQLException {
		new Lances( user, value);
	}
}
