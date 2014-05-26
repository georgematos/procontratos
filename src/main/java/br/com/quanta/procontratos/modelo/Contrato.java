package br.com.quanta.procontratos.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Contrato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5124675756085521755L;

	@Id
	@Column(unique = true, length = 20)
	@NotBlank
	private String numero;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@NotNull
	private Date dataIni;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@NotNull
	private Date dataFim;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Fornecedor fornecedor;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "contrato_id")
	private List<ItemContratado> itens;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ItemContratado> getItens() {
		return itens;
	}

	public void setItens(List<ItemContratado> itens) {
		this.itens = itens;
	}

}
