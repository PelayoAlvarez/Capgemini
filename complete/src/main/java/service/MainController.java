package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.TableItem;
import model.TableItemRepository;

@Controller    
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired 
	private TableItemRepository tableItemRepository;
	
	//Create a new TableItem and add it to the database with the information given in the request.
	@GetMapping(path="/add") 
	public @ResponseBody String addNewTableItem (@RequestParam Integer codEntidad
			, @RequestParam String descripcion, @RequestParam String clave, 
			@RequestParam Integer valor) {
		
		TableItem n = new TableItem();
		n.setCodEntidad(codEntidad);
		n.setDescripcion(descripcion);
		n.setClave(clave);
		n.setValor(valor);
		tableItemRepository.save(n);
		return "Saved";
	}
	
	//Retrieve every TableItem object from the database
	@GetMapping(path="/all")
	public @ResponseBody Iterable<TableItem> getAllTableItems() {
		// This returns a JSON or XML with the users
		return tableItemRepository.findAll();
	}
}
