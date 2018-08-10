package be.vdab.valueobjects;

import be.vdab.constraints.Postcode;
import be.vdab.constraints.PostcodeReeksVanKleinerDanOfGelijkAanTot;

@PostcodeReeksVanKleinerDanOfGelijkAanTot
public class PostcodeReeks {
	@Postcode
	private Integer vanpostcode;
	@Postcode
	private Integer totpostcode;
	
	public Integer getVanpostcode() {
		return vanpostcode;
	}

	public Integer getTotpostcode() {
		return totpostcode;
	}

	public boolean bevat(int postcode) {
		return postcode >= vanpostcode && postcode <= postcode;
	}
}
