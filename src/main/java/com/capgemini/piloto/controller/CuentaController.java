package com.capgemini.piloto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.service.CuentaService;

@Controller
public class CuentaController {
	
private CuentaService cuentaService;
	
	@Autowired(required=true)
	@Qualifier(value="cuentaService")
	public void setCuentaService(CuentaService cs){
		this.cuentaService = cs;
	}
	
	@RequestMapping(value = "/cuenta", method = RequestMethod.GET)
	public String listCuenta(Model model) {
		model.addAttribute("cuenta", new Cuenta());
		model.addAttribute("listCuenta", this.cuentaService.listCuenta());
		return "cuenta";
	}
	
	//For add and update person both
	@RequestMapping(value= "/cuenta/add", method = RequestMethod.POST)
	public String addCuenta(@ModelAttribute("cuenta") Cuenta c){
		
		if(c.getId() == 0){
			this.cuentaService.addCuenta(c);
		}else{
			this.cuentaService.updateCuenta(c);
		}
		
		return "redirect:/cuenta";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removeCuenta(@PathVariable("id") int id){
		
        this.cuentaService.removeCuenta(id);
        return "redirect:/cuenta";
    }
 
    @RequestMapping("/edit/{id}")
    public String editCuenta(@PathVariable("id") int id, Model model){
        model.addAttribute("cuenta", this.cuentaService.getCuentaId(id));
        model.addAttribute("listCuenta", this.cuentaService.listCuenta());
        return "cuenta";
    }

}
