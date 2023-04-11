package com.nadhem.livres.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nadhem.livres.entities.Livre;
import com.nadhem.livres.service.LivreService;



@Controller
public class LivreController {
	@Autowired
	LivreService livreService;

	@GetMapping("/showCreate")
	public String showCreate(ModelMap model) {
		model.addAttribute("livre", new Livre());
		return "createLivre";
	}

	@PostMapping("/saveLivre")
	public String saveLivre(@ModelAttribute("livre") Livre livre, BindingResult result,
			@RequestParam("datePublication") String datePublication, ModelMap modelMap) throws ParseException {
		
		if (result.hasErrors()) {
			return "createLivre";
		}
		
		// conversion de la date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(datePublication);
		livre.setDatePublication(date);

		Livre savedLivre = livreService.saveLivre(livre);
		String msg = "Livre enregistré avec Id " + savedLivre.getIdLivre();
		modelMap.addAttribute("msg", msg);
		return "redirect:ListeLivres";
	}

	@RequestMapping("/ListeLivres")
	public String listeLivres(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
		Page<Livre> livres = livreService.getAllLivresParPage(page, size);
		modelMap.addAttribute("livres", livres);
		modelMap.addAttribute("pages", new int[livres.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeLivres";
	}

	@RequestMapping("/supprimerLivre")
	public String supprimerLivre(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		livreService.deleteLivreById(id);
		Page<Livre> livres = livreService.getAllLivresParPage(page, size);
		modelMap.addAttribute("livres", livres);
		modelMap.addAttribute("pages", new int[livres.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeLivres";
	}

	@RequestMapping("/modifierLivre")
	public String editerLivre(@RequestParam("id") Long id, ModelMap modelMap) {
		Livre livre = livreService.getLivre(id);
		modelMap.addAttribute("livre", livre);
		return "editerLivre";
	}

	@PostMapping("/updateLivre")
	public String updateLivre(@ModelAttribute("livre") Livre livre, BindingResult result,
			@RequestParam("datePublication") String datePublication, ModelMap modelMap) throws ParseException {
		
		if (result.hasErrors()) {
			return "editerLivre";
		}
		
		// conversion de la date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(datePublication);
		livre.setDatePublication(date);

		livreService.saveLivre(livre);
		String msg = "Livre modifié avec Id " + livre.getIdLivre();
		modelMap.addAttribute("msg", msg);
		return "redirect:ListeLivres";
	}

}
