package com.divinisoft.project.dbmanager.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<D, M> {

	public abstract M convertToModel(D dto);

	public abstract D convertToDTO(M model);

	public List<M> convertToModels(List<D> dtos) {
		List<M> models = new ArrayList<M>();
		for (D dtoD : dtos) {
			if (this.convertToModel(dtoD) != null) {
				models.add(this.convertToModel(dtoD));
			}
		}

		return models;
	}

	public List<D> convertToDTOs(List<M> models) {
		List<D> dtos = new ArrayList<D>();
		for (M dto : models) {
			if (this.convertToDTO(dto) != null) {
				dtos.add(this.convertToDTO(dto));
			}
		}

		return dtos;
	}
}
