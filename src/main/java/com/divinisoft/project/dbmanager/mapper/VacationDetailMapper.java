package com.divinisoft.project.dbmanager.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divinisoft.project.db.entity.VacationDetailDTO;
import com.divinisoft.project.model.VacationDetail;

@Component
public class VacationDetailMapper extends AbstractMapper<VacationDetailDTO, VacationDetail> {
	@Autowired
	VacationTypeMapper vacationTypeMapper;

	@Override
	public VacationDetail convertToModel(VacationDetailDTO dto) {
		VacationDetail vDetail = new VacationDetail();
		vDetail.setVacationId(dto.getId());
		vDetail.setDate(dto.getDate());
		vDetail.setVacationType(dto.getVacationType().getVacationType());
		return vDetail;
	}

	@Override
	public List<VacationDetail> convertToModels(List<VacationDetailDTO> dtos) {
		return super.convertToModels(dtos);
	}

	@Override
	public VacationDetailDTO convertToDTO(VacationDetail model) {
		VacationDetailDTO vDetailDTO = new VacationDetailDTO();
		if (model.getVacationId() != null) {
			vDetailDTO.setId(model.getVacationId());
		}
		vDetailDTO.setDate(model.getDate());
		vDetailDTO.setVacationType(this.vacationTypeMapper.convertToDTO(model.getVacationType()));
		return vDetailDTO;
	}

	@Override
	public List<VacationDetailDTO> convertToDTOs(List<VacationDetail> models) {
		return super.convertToDTOs(models);
	}

}
