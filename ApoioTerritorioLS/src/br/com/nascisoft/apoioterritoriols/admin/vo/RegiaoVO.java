package br.com.nascisoft.apoioterritoriols.admin.vo;

import java.io.Serializable;

import br.com.nascisoft.apoioterritoriols.login.entities.Cidade;
import br.com.nascisoft.apoioterritoriols.login.entities.Regiao;


public class RegiaoVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private Long cidadeId;
	private String cidadeNome;
	private String letra;
	private Integer zoom;
	private Double latitudeCentro;
	private Double longitudeCentro;
	
	public RegiaoVO() {
		super();
	}
	
	public RegiaoVO(Regiao regiao, Cidade cidade) {
		this.setId(regiao.getId());
		this.setNome(regiao.getNome());
		this.setLetra(regiao.getLetra());
		this.setZoom(regiao.getZoom());
		this.setLatitudeCentro(regiao.getLatitudeCentro());
		this.setLongitudeCentro(regiao.getLongitudeCentro());
		this.setCidadeId(cidade.getId());
		this.setCidadeNome(cidade.getNome());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCidadeId() {
		return cidadeId;
	}
	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
	public String getCidadeNome() {
		return cidadeNome;
	}
	public void setCidadeNome(String cidadeNome) {
		this.cidadeNome = cidadeNome;
	}
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public Double getLatitudeCentro() {
		return latitudeCentro;
	}
	public void setLatitudeCentro(Double latitudeCentro) {
		this.latitudeCentro = latitudeCentro;
	}
	public Double getLongitudeCentro() {
		return longitudeCentro;
	}
	public void setLongitudeCentro(Double longitudeCentro) {
		this.longitudeCentro = longitudeCentro;
	}
	
	public Regiao getRegiao() {
		Regiao regiao = new Regiao();
		
		regiao.setId(this.getId());
		regiao.setNome(this.getNome());
		regiao.setLetra(this.getLetra());
		regiao.setZoom(this.getZoom());
		regiao.setLatitudeCentro(this.getLatitudeCentro());
		regiao.setLongitudeCentro(this.getLongitudeCentro());
		
		return regiao;
	}
}
