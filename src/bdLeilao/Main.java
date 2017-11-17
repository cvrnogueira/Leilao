package bdLeilao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Main extends Application  {

	private TextField consultaNome;
	private TextField cpf;
	private TextField value;
	private TextField email;
	private Leilao leilao;
	private static TextArea textArea;
	private TextField ownerUser;
	private TextField minimunPrice;
	private TextField shortDescription;
	private TextField longDescription;
	private TextField categoryy;

	@Override
	public void start(Stage primaryStage) throws Exception {
		setup();
		textArea = new TextArea();
		
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: #1abc9c;");
		GridPane leftPane = new GridPane();
		leftPane.setAlignment(Pos.CENTER);
		leftPane.setHgap(10);
		leftPane.setVgap(10);
		leftPane.setPadding(new Insets(10, 10, 10, 10));
	
		//----botões--//
		Button btnGetFonePeloNome = new Button("Get Fone pelo Nome");
		btnGetFonePeloNome.setPrefWidth(250);
		btnGetFonePeloNome.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(btnGetFonePeloNome, 1, 9);
		Button btnConsulta = new Button("Lista Todos lances já feitos");
		btnConsulta.setPrefWidth(250);
		btnConsulta.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(btnConsulta, 0, 9);
		Button btnInserirNovo = new Button("Inserir Novo lance");
		btnInserirNovo.setPrefWidth(250);
		btnInserirNovo.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(btnInserirNovo, 2, 9);
		Button btnSair = new Button("Finalizar Leilao");
		btnSair.setPrefWidth(250);
		btnSair.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(btnSair, 3, 9);
		Button btnInserirNovoUser = new Button("Inserir Novo Usuario");
		btnInserirNovo.setPrefWidth(250);
		btnInserirNovo.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(btnInserirNovoUser, 4, 9);
		Button btnInserirNovoLeilao = new Button("Inserir Novo Leilao");
		btnInserirNovo.setPrefWidth(250);
		btnInserirNovo.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(btnInserirNovoLeilao, 5, 9);
		
		//--cpf e valor do usu para cadastrar--//
		cpf = new TextField("CPF");
		cpf.setPrefWidth(200);
		cpf.setStyle("-fx-background-color:#ecf0f1");
		value = new TextField("Valor");
		value.setPrefWidth(200);
		value.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(cpf, 2, 7);
		leftPane.add(value, 2, 8);
		
		//--cpf e email do usu para cadastrar--//
		cpf = new TextField("CPF");
		cpf.setPrefWidth(200);
		cpf.setStyle("-fx-background-color:#ecf0f1");
		email = new TextField("Email");
		email.setPrefWidth(200);
		email.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(cpf, 4, 7);
		leftPane.add(email, 4, 8);
		
		//--Dados do leilao--//
		ownerUser = new TextField("CPF Dono do leilao");
		ownerUser.setPrefWidth(200);
		ownerUser.setStyle("-fx-background-color:#ecf0f1");
		minimunPrice = new TextField("Preço mínimo");
		minimunPrice.setPrefWidth(200);
		minimunPrice.setStyle("-fx-background-color:#ecf0f1");
		shortDescription = new TextField("Pequena descrição");
		shortDescription.setPrefWidth(200);
		shortDescription.setStyle("-fx-background-color:#ecf0f1");
		longDescription = new TextField("Longa descrição");
		longDescription.setPrefWidth(200);
		longDescription.setStyle("-fx-background-color:#ecf0f1");
		categoryy = new TextField("Categoria");
		categoryy.setPrefWidth(200);
		categoryy.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(ownerUser, 5, 7);
		leftPane.add(minimunPrice, 5, 8);
		leftPane.add(shortDescription, 5, 9);
		leftPane.add(longDescription, 5, 10);
		leftPane.add(categoryy, 5, 11);
		
		//-----Nome do usuário a ser buscado---/
		consultaNome = new TextField("Nome");
		consultaNome.setPrefWidth(200);
		consultaNome.setStyle("-fx-background-color:#ecf0f1");
		leftPane.add(consultaNome, 1, 7);
		
		btnConsulta.setOnAction(e -> {
				try {
					listarTodosLances();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		});
			
//		btnGetFonePeloNome.setOnAction(e -> {
//				if(consultaNome.getText() != null){
//					try {
//						getFonePeloNome(consultaNome.getText());
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
//				}
//			});
		
		btnInserirNovo.setOnAction(e -> {
			if(cpf.getText() != null && value.getText() != null){
				try {
					insereNovoLance(cpf.getText(),value.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInserirNovoUser.setOnAction(e -> {
			if(cpf.getText() != null && email.getText() != null){
				try {
					insereNovaPessoa(cpf.getText(),email.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnSair.setOnAction(e -> {
				try {
					sair();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		});
				
		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.setSpacing(5);
		root.getChildren().add(new Label("Output:"));
		root.getChildren().add(textArea);

		pane.setLeft(leftPane);
		pane.setBottom(root);
		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Leilao");
	
		primaryStage.show();
	}
	
	private void setup() throws SQLException {
		//leilao = new Leilao("86820397021", 250, "descrica curta", "descricao longa", "informática");
	}
	
	public void listarTodosLances() throws SQLException{
		textArea.setText(" ");
		HashMap<String, Double> lances = new HashMap<>();
		lances = leilao.returnLeilao().listarTodosLances();
		
		for (Entry<String, Double> pair : lances.entrySet()) {
			textArea.appendText(pair.getValue() + " -" + pair.getKey());
			textArea.appendText("\n");
		}
 
	}
//	
//	public void getFonePeloNome(String nome) throws SQLException{
//		textArea.setText(" ");
//		ArrayList<String> telefones = new ArrayList<String>();
//		telefones = agenda.returnAgenda().getFonePeloNome(nome);
//		
//		
//		for(String telefone: telefones){
//			textArea.appendText(telefone);
//			textArea.appendText("\n");
//		}
//
//	}
	
	public void insereNovoLance(String cpf, String value) throws SQLException{
		textArea.setText(" ");
		leilao.returnLeilao().inserirNovoLance(cpf, Float.parseFloat(value));
	
			textArea.setText("Usuário inserido!");
	}
	public void insereNovaPessoa(String cpf, String email) throws SQLException{
		textArea.setText(" ");
		leilao.returnLeilao().inserirNovaPessoa(cpf, email);
	
			textArea.setText("Usuário inserido!");
	}
	
	public void insereNovoLeilao(String ownerUser,  float minimunPrice, String shortDescription, String longDescription, String categoryy ) throws SQLException{
		leilao.returnLeilao().sair();
		textArea.setText(" ");
		leilao.returnLeilao().inserirNovoLeilao(ownerUser, minimunPrice, shortDescription, longDescription, categoryy);
		textArea.setText("Leilão inserido!");
	}
	public void sair() throws SQLException{
		leilao.returnLeilao().sair();
		textArea.setText("Conexão fechada!");
	}


}