package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.constraints.Postcode;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotBlank
	@Length(min=1, max=50)
	@SafeHtml
	private String straat;
	@NotBlank
	@Length(min=1, max=7)
	@SafeHtml
	private String huisNr;
	@NotNull
	@Postcode
	private Integer postcode;
	@NotBlank
	@Length(min=1, max=50)
	@SafeHtml
	private String gemeente;
	
	protected Adres() {}
	
	

	public Adres(@NotBlank @Length(min = 1, max = 50) @SafeHtml String straat,
			@NotBlank @Length(min = 1, max = 7) @SafeHtml String huisNr, @NotNull Integer postcode,
			@NotBlank @Length(min = 1, max = 50) @SafeHtml String gemeente) {
		this.straat = straat;
		this.huisNr = huisNr;
		this.postcode = postcode;
		this.gemeente = gemeente;
	}



	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public String getGemeente() {
		return gemeente;
	}
	

}
