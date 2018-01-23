package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.TransferenciaModel;
import service.TransferenciaService;

@Controller
public class TransferenciaController {
	
private TransferenciaService transferService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(TransferenciaService ps){
		this.transferService = ps;
	}
	
	@RequestMapping(value = "/transferencia", method = RequestMethod.GET)
	public String listTransfer(Model model) {
		model.addAttribute("transferencia", new TransferenciaModel());
		model.addAttribute("listTransfer", this.transferService.listTransferencia());
		return "transferencia";
	}
	
	//For add and update person both
	@RequestMapping(value= "/transfencia/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("transferencia") TransferenciaModel t){
		
		if(t.getId() == 0){
			this.transferService.addTransferencia(t);
		}else{
			this.transferService.updateTransferencia(t);
		}
		
		return "redirect:/transferencia";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.transferService.removeTransferencia(id);
        return "redirect:/transferencia";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("transferencia", this.transferService.getTransferenciaId(id));
        model.addAttribute("listTransfer", this.transferService.listTransferencia());
        return "transferencia";
    }

}
