package com.divinisoft.project.dbmanager.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divinisoft.project.db.dao.VacationTypeDAO;
import com.divinisoft.project.db.entity.VacationTypeDTO;

@Component
public class VacationTypeMapper extends BaseConverter<VacationTypeDTO, String> {
	
	@Autowired
	VacationTypeDAO vacationTypeDAO;

	@Override
	public String convertToModel(VacationTypeDTO dto) {
		return dto.getVacationType();
	}

	@Override
	public VacationTypeDTO convertToDTO(String model) {
		return this.vacationTypeDAO.findByName(model).get(0);
	}

}
