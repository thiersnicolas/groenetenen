package be.vdab.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.enums.Gazontype;

public class Offerte implements Serializable {
	public interface Stap1 {}
	public interface Stap2 {}
	
	private static final long serialVersionUID = 1L;
	@NotBlank(groups = Stap1.class)
	@SafeHtml(groups = Stap1.class)
	private String voornaam;
	@NotBlank(groups = Stap1.class)
	@SafeHtml(groups = Stap1.class)
	private String familienaam;
	@NotBlank(groups = Stap1.class)
	@Email(groups = Stap1.class)
	private String emailAdres;
	@NotNull(groups = Stap2.class)
	@Min(value = 1, groups = Stap2.class)
	private Integer oppervlakte;
	private List<String> telefoonNrs = new ArrayList<>();
	private Map<Gazontype, Boolean> gazontypes = new LinkedHashMap<>();
	
	public Offerte() {
		telefoonNrs.add("");
		Arrays.stream(Gazontype.values()).forEach(gazontype-> gazontypes.put(gazontype, false));
	}
	
	public Map<Gazontype, Boolean> getGazontypes() {
		return gazontypes;
	}

	public void setGazontypes(Map<Gazontype, Boolean> gazontypes) {
		this.gazontypes = gazontypes;
	}

	public void nogEenTelefoonNr() {
		telefoonNrs.add("");
	}
	
	public List<String> getTelefoonNrs() {
		return telefoonNrs;
	}
	public void setTelefoonNrs(List<String> telefoonNrs) {
		this.telefoonNrs = telefoonNrs;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public String getEmailAdres() {
		return emailAdres;
	}
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}
	public Integer getOppervlakte() {
		return oppervlakte;
	}
	public void setOppervlakte(Integer oppervlakte) {
		this.oppervlakte = oppervlakte;
	}
	
	
}
