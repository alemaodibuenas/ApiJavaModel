package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ModeloDTO;
import com.example.demo.model.ModeloModel;
import com.example.demo.repository.IModeloRepository;
import com.example.demo.service.exceptions.DataIntegrityViolationException;
import com.example.demo.service.exceptions.ObjectNotFoundException;

@Service
public class ModeloService {

	@Autowired
	private IModeloRepository repository;
	
	public ModeloModel findById(Integer id) {
		java.util.Optional<ModeloModel> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! id: "
				+ id  + ", Tipo " + ModeloModel.class.getName()));
	}
	
	public List<ModeloModel> findAll(){
		return repository.findAll();	
	}
	//Metodo create
	public ModeloModel create(ModeloModel obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	//Metodo update
	public ModeloModel update(Integer id, ModeloDTO objDto) {
		ModeloModel obj = findById(id);
		obj.setDescricao(objDto.getDescricao());
		return repository.save(obj);
	}
	//Metodo delete
	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.example.demo.service.exceptions.DataIntegrityViolationException(
			"Modelo nao pode ser deletado! Caso ele possua depedencia com chave estrangeira");			
		}
		
	}

}
