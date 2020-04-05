package com.divinisoft.project.dbmanager.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divinisoft.project.db.dao.VacationDetailDAO;
import com.divinisoft.project.db.dto.VacationDetailDTO;
import com.divinisoft.project.exception.VacationValidationException;
import com.divinisoft.project.model.VacationDetail;
import com.divinisoft.project.utility.DateUtility;

@Component
public class VacationDetailMapper extends AbstractMapper<VacationDetailDTO, VacationDetail> {

	@Autowired
	VacationTypeMapper vacationTypeMapper;

	@Autowired
	VacationDetailDAO vDetailDAO;

	@Override
	public VacationDetail convertToModel(VacationDetailDTO dto) {
		VacationDetail vDetail = null;
		String dateString = DateUtility.formatDateToString(dto.getDate());

		// check if the vacation falls under current year
		if (dateString.compareTo(DateUtility.getStartDateStringOfCurrentYear()) >= 0) {
			vDetail = new VacationDetail();
			vDetail.setVacationDetailId(dto.getId());
			vDetail.setDate(DateUtility.formatDateToString(dto.getDate()));
			vDetail.setVacationType(dto.getVacationType().getVacationType());
		}
		return vDetail;
	}

	@Override
	public VacationDetailDTO convertToDTO(VacationDetail model) {
		VacationDetailDTO vDetailDTO = null;
		this.validateVacationDate(model.getDate());
		Integer id = model.getVacationDetailId();
		if (id != null) {
			try {
				vDetailDTO = this.vDetailDAO.getOne(id);
			} catch (Exception e) {
				throw new VacationValidationException("Vacation not found for vacation id: " + id);
			}
		} else {
			vDetailDTO = new VacationDetailDTO();
		}

		Date inputDate = DateUtility.formatStringToDate(model.getDate());
		vDetailDTO.setDate(inputDate);
		vDetailDTO.setVacationType(this.vacationTypeMapper.convertToDTO(model.getVacationType()));
		return vDetailDTO;
	}

	@Override
	public List<VacationDetail> convertToModels(List<VacationDetailDTO> dtos) {
		return super.convertToModels(dtos);
	}

	@Override
	public List<VacationDetailDTO> convertToDTOs(List<VacationDetail> models) {
		return super.convertToDTOs(models);
	}

	public void validateVacationDate(String date) {
		String dateString = DateUtility.formatDateToString(new Date());
		if (date.compareTo(dateString) < 0) {
			throw new VacationValidationException("Application rejected due to past vacation date supplied!");
		} else if (DateUtility.isWeekendDay(date)) {
			throw new VacationValidationException("Application rejected due to vacation date falls on weekend!");
		} else if (DateUtility.isHoliday(date)) {
			throw new VacationValidationException("Application rejected due to vacation date falls on holiday!");
		}
	}
}
