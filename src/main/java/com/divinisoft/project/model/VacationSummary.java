package com.divinisoft.project.model;

public class VacationSummary implements Comparable<VacationSummary> {

	private String vacationType;
	private int totalDays;
	private int daysTaken;

	public String getVacationType() {
		return vacationType;
	}

	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getDaysTaken() {
		return daysTaken;
	}

	public void setDaysTaken(int daysTaken) {
		this.daysTaken = daysTaken;
	}

	@Override
	public int compareTo(VacationSummary vacationSummary) {
		return vacationSummary.getVacationType().compareTo(this.vacationType);
	}

}
