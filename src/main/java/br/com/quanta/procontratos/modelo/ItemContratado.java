package br.com.quanta.procontratos.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Entity
public class ItemContratado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1086354648549793183L;

	@Id
	@GeneratedValue
	private Long numero;

	@Column(unique = true, length = 50)
	private String nome;

	@Digits(fraction = 2, integer = 8)
	private double valorUnt;

	@Min(1)
	private int qtd;

	@OneToOne(cascade=CascadeType.ALL)
	private Pagamento pagamento;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorUnt() {
		return valorUnt;
	}

	public void setValorUnt(double valorUnt) {
		this.valorUnt = valorUnt;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}
