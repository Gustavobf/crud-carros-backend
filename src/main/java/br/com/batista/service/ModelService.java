package br.com.batista.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.batista.dto.ModelDTO;
import br.com.batista.entity.Model;
import br.com.batista.mapper.ModelMapper;
import br.com.batista.repository.ModelRepository;

@Service
public class ModelService {

	private ModelRepository modelRepository;

	@Autowired
	public ModelService(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}

	public Page<ModelDTO> getAll(Pageable pageable) {
		return modelRepository.findAll(pageable).map(entity -> ModelMapper.mapToModelDto(entity, new ModelDTO()));
	}

	public ModelDTO getById(final Long id) {
		final Optional<Model> model = modelRepository.findById(id);
		final ModelDTO modelDTO = ModelMapper.mapToModelDto(model.get(), new ModelDTO());
		return modelDTO;
	}

	public ModelDTO create(final ModelDTO modelDTO) {
		final Model model = ModelMapper.mapToModel(modelDTO, new Model());
		final Model savedModel = modelRepository.save(model);
		final ModelDTO dto = ModelMapper.mapToModelDto(savedModel, new ModelDTO());
		return dto;
	}

	public void delete(final Long id) {
		modelRepository.deleteById(id);
	}

	public ModelDTO update(final ModelDTO modelDTO) {
		final Model model = ModelMapper.mapToModel(modelDTO, new Model());
		final Model savedModel = modelRepository.save(model);
		final ModelDTO dto = ModelMapper.mapToModelDto(savedModel, new ModelDTO());
		return dto;
	}

}
