package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ModeloModel;
import com.example.demo.repository.IModeloRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api")
public class ModeloController {

	@Autowired
	private IModeloRepository repo;

	@GetMapping(path = "/modelos")
	public List<ModeloModel> getAll() {
		return this.repo.findAll();
	}

	@GetMapping(path = "/modelos/{id}")
	public ModeloModel getById(@PathVariable int id) {
		return this.repo.findById(id).orElse(null);
	}

	@PostMapping(path = "/modelos")
	public ModeloModel post(@RequestBody ModeloModel modelo) {
		return this.repo.save(modelo);
	}

	@DeleteMapping(path = "/modelos/{id}")
	public void delete(@PathVariable int id) {
		if (this.repo.findById(id).orElse(null) != null) {
			this.repo.deleteById(id);
		}
	}

	@PutMapping(path = "/modelos")
	public ModeloModel put(@RequestBody ModeloModel modelo) {
		return this.repo.findById(modelo.getId()).map(model -> {
			model.setDescricao(modelo.getDescricao());

			return this.repo.save(model);
		}).orElse(null);
	}

}
