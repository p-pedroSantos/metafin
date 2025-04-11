package br.org.ifpe.metafin.model;

import java.time.LocalDate;

public class Home {
	private LocalDate data;
	private double gasto;
	private double ganho;
	private double lucro;
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public double getGasto() {
		return gasto;
	}
	public void setGasto(double gasto) {
		this.gasto = gasto;
	}
	public double getGanho() {
		return ganho;
	}
	public void setGanho(double ganho) {
		this.ganho = ganho;
	}
	public double getLucro() {
		return lucro;
	}
	public void setLucro(double lucro) {
		this.lucro = lucro;
	}
	
	
	

}
