package com.example.caffetteria.model;

import java.io.Serializable;

public class Prodotti_OrdiniId implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id_prodotto_ordine;
	private Integer id_ordine;
	private Integer id_prodotto;
	
	public Integer getId_prodotto_ordine() {
		return id_prodotto_ordine;
	}
	public void setId_prodotto_ordine(Integer id_prodotto_ordine) {
		this.id_prodotto_ordine = id_prodotto_ordine;
	}
	public Integer getId_ordine() {
		return id_ordine;
	}
	public void setId_ordine(Integer id_ordine) {
		this.id_ordine = id_ordine;
	}
	public Integer getId_prodotto() {
		return id_prodotto;
	}
	public void setId_prodotto(Integer id_prodotto) {
		this.id_prodotto = id_prodotto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Prodotti_OrdiniId(Integer id_prodotto_ordine, Integer id_ordine, Integer id_prodotto) {
		super();
		this.id_prodotto_ordine = id_prodotto_ordine;
		this.id_ordine = id_ordine;
		this.id_prodotto = id_prodotto;
	}
	public Prodotti_OrdiniId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
