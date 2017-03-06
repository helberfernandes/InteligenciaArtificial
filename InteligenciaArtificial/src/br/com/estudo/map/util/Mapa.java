package br.com.estudo.map.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XmlRootElement(name="mapa")
public class Mapa {

	@XStreamImplicit
	private List<Cidade> cidades = new ArrayList<Cidade>();

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	
}
