package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Produto {
	private int id;
	private String nome;
	private float salario;
	private int idade;
	private LocalDateTime dataingresso;	
	private LocalDate dataaposentadoria;
	
	public Produto() {
		id = -1;
		nome = "";
		salario = 0.00F;
		idade = 0;
		dataingresso = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		dataaposentadoria = LocalDate.now().plusMonths(6); // o default é uma aposentadoria de 6 meses.
	}

	public Produto(int id, String nome, float salario, int idade, LocalDateTime ingresso, LocalDate v) {
		setId(id);
		setnome(nome);
		setsalario(salario);
		setidade(idade);
		setdataingresso(ingresso);
		setdataaposentadoria(v);
	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public float getsalario() {
		return salario;
	}

	public void setsalario(float salario) {
		this.salario = salario;
	}

	public int getidade() {
		return idade;
	}
	
	public void setidade(int idade) {
		this.idade = idade;
	}
	
	public LocalDate getdataaposentadoria() {
		return dataaposentadoria;
	}

	public LocalDateTime getdataingresso() {
		return dataingresso;
	}

	public void setdataingresso(LocalDateTime dataingresso) {
		// Pega a Data Atual
		LocalDateTime agora = LocalDateTime.now();
		// Garante que a data de Ingresso não pode ser futura
		if (agora.compareTo(dataingresso) >= 0)
			this.dataingresso = dataingresso;
	}

	public void setdataaposentadoria(LocalDate dataaposentadoria) {
		// a data de Ingresso deve ser anterior é data de aposentadoria.
		if (getdataingresso().isBefore(dataaposentadoria.atStartOfDay()))
			this.dataaposentadoria = dataaposentadoria;
	}

	public boolean emaposentadoria() {
		return LocalDateTime.now().isBefore(this.getdataaposentadoria().atTime(23, 59));
	}


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Piloto: " + nome + "   Salario: R$" + salario + "   Idade.: " + idade + "   Ingresso: "
				+ dataingresso  + "   Data de aposentadoria: " + dataaposentadoria;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Produto) obj).getID());
	}	
}