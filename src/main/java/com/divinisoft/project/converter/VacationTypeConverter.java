package com.divinisoft.project.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.divinisoft.project.hibernate.entity.VacationTypeDTO;
import com.divinisoft.project.repository.VacationTypeRepository;

public class VacationTypeConverter extends BaseConverter<VacationTypeDTO, String> {
	@Autowired
	VacationTypeRepository vaRepository;

	@Override
	public String convertToModel(VacationTypeDTO dto) {
		return dto.getVacationType();
	}

	@Override
	public VacationTypeDTO convertToDTO(String model) {
		return this.vaRepository.findByName(model).get(0);
	}

}
