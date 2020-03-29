package com.divinisoft.project.dbmanager.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divinisoft.project.db.dao.VacationTypeDAO;
import com.divinisoft.project.db.dto.VacationTypeDTO;

@Component
public class VacationTypeMapper extends AbstractMapper<VacationTypeDTO, String> {
	
	@Autowired
	VacationTypeDAO vacationTypeDAO;

	@Override
	public String convertToModel(VacationTypeDTO dto) {
		return dto.getVacationType();
	}

	@Override
	public VacationTypeDTO convertToDTO(String model) {
		VacationTypeDTO vTypeDTO = this.vacationTypeDAO.findByName(model);
		if(vTypeDTO == null) {
			throw new IllegalArgumentException("Invalid vacation type supplied");
		}
		return vTypeDTO;
	}

}
