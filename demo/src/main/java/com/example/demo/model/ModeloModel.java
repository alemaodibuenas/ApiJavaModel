package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class ModeloModel implements Serializable {

	private static final long serialVersionUID = 1L;

	// indicar que o id é uma chave primaria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Campo DESCRIÇÃO é requerido")
	@Length(min = 3 , max = 100, message = " O campo DESCRIÇÃO deve ter entre 3 e 100 caracteres")
	private String descricao;

	public ModeloModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModeloModel(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
