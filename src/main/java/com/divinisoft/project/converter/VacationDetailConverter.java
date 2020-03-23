package com.divinisoft.project.converter;

import java.util.List;

import com.divinisoft.project.hibernate.entity.VacationDetailDTO;
import com.divinisoft.project.model.VacationDetail;

public class VacationDetailConverter extends BaseConverter<VacationDetailDTO, VacationDetail> {

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
		VacationTypeConverter vacationTypeConverter = new VacationTypeConverter();
		VacationDetailDTO vDetailDTO = new VacationDetailDTO();
		if (model.getVacationId() != null) {
			vDetailDTO.setId(model.getVacationId());
		}
		vDetailDTO.setDate(model.getDate());
		vDetailDTO.setVacationType(vacationTypeConverter.convertToDTO(model.getVacationType()));
		return vDetailDTO;
	}

	@Override
	public List<VacationDetailDTO> convertToDTOs(List<VacationDetail> models) {
		return super.convertToDTOs(models);
	}

}
