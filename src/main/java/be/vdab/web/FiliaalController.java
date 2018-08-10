package be.vdab.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.PostcodeReeks;

@Controller
@RequestMapping("/filialen")
class FiliaalController {
	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	private static final String VERWIJDERD_VIEW = "filialen/verwijded";
	private static final String PER_POSTCODE_VIEW = "filialen/perpostcode";
	private static final String WIJZIGEN_VIEW = "filialen/wijzigen";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	private static final String REDIRECT_URL_FILIAAL_NIET_GEVONDEN = "redirect:/filialen";
	private static final String REDIRECT_URL_NA_VERWIJDEREN = "redirect:/filialen/{id}/verwijderd";
	private static final String REDIRECT_URL_HEEFT_NOG_WERKNEMERS = "redirect:/filialen/{id}";
	private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/filialen/{id}";
	private static final String REDIRECT_URL_NA_LOCKING_EXCEPTION = "redirect:/filialen/{id}?optimisticlockingexception=true";
	private static final String FILIAAL_VIEW = "filialen/filiaal";

	private final FiliaalService filiaalService;

	FiliaalController(FiliaalService filiaalServie) {
		this.filiaalService = filiaalServie;
	}

	@GetMapping
	ModelAndView findAll() {
		return new ModelAndView(FILIALEN_VIEW, "filialen", filiaalService.findAll()).addObject("aantalFilialen",
				filiaalService.findAantalFilialen());
	}

	@GetMapping("toevoegen")
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW).addObject(new Filiaal());
	}

	@GetMapping("{filiaal}")
	ModelAndView read(@PathVariable Filiaal filiaal) {
		ModelAndView modelAndView = new ModelAndView(FILIAAL_VIEW);
		if (filiaal != null) {
			modelAndView.addObject(filiaal);
		}
		return modelAndView;
	}

	@GetMapping("{id}/verwijderd")
	String deleted() {
		return VERWIJDERD_VIEW;
	}

	@GetMapping("perpostcode")
	ModelAndView findByPostcodeReeks() {
		PostcodeReeks reeks = new PostcodeReeks();
		return new ModelAndView(PER_POSTCODE_VIEW).addObject(reeks);
	}

	@GetMapping(params = { "vanpostcode", "totpostcode" })
	ModelAndView ByPostcodeReeks(@Valid PostcodeReeks reeks, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(PER_POSTCODE_VIEW);
		if (!bindingResult.hasErrors()) {
			List<Filiaal> filialen = filiaalService.findByPostcodeReeks(reeks);
			if (filialen.isEmpty()) {
				bindingResult.reject("geenFilialen");
				return modelAndView;
			} else {
				return modelAndView.addObject("filialen", filiaalService.findByPostcodeReeks(reeks));
			}
		} else {
			return modelAndView;
		}
	}

	@GetMapping("{filiaal}/wijzigen")
	ModelAndView updateForm(@PathVariable Filiaal filiaal) {
		if (filiaal==null) {
			return new ModelAndView(REDIRECT_URL_FILIAAL_NIET_GEVONDEN);
		}
		return new ModelAndView(WIJZIGEN_VIEW).addObject(filiaal);
	}

	@PostMapping
	String create(@Valid Filiaal filiaal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;
		} else {
			return REDIRECT_URL_NA_TOEVOEGEN;
		}
	}

	@PostMapping("{filiaal}/verwijderen")
	String delete(@PathVariable Filiaal filiaal, RedirectAttributes redirectAttributes) {
		if (filiaal==null) {
			return REDIRECT_URL_FILIAAL_NIET_GEVONDEN;
		} else {
			long id = filiaal.getId();
			try {
				filiaalService.delete(id);
				redirectAttributes.addAttribute("id", id).addAttribute("naam", filiaal.getNaam());
				return REDIRECT_URL_NA_VERWIJDEREN;
			} catch (FiliaalHeeftNogWerknemersException ex) {
				redirectAttributes.addAttribute("id", id).addAttribute("fout", "filiaal heeft nog werknemers");
				return REDIRECT_URL_HEEFT_NOG_WERKNEMERS;
			}
		}
	}

	@PostMapping("{filiaal}/wijzigen")
	String update(@Valid Filiaal filiaal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		} else {
			try {
				filiaalService.update(filiaal);
				return REDIRECT_URL_NA_WIJZIGEN;
			} catch (ObjectOptimisticLockingFailureException ex) {
				return REDIRECT_URL_NA_LOCKING_EXCEPTION;
			}
		}
	}

	@InitBinder("postcodeReeks")
	void initBinderPostcodeReeks(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

	@InitBinder("filiaal")
	void initBinderFiliaal(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
}
