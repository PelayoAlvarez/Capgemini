package com.capgemini.piloto.controller;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Sucursal;
import com.capgemini.piloto.model.dto.EmpleadoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmpleadoControllerTest {

	@LocalServerPort
    private int port;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private EmpleadoController empleadoController;
	
	@Test
	public void getAllEmpleadosTest() throws Exception {
		Sucursal sucursal = new Sucursal(1L, "Sucursal", "Calle", "User");
		Empleado empleado = new Empleado("39745374V", "Empleado", "Test", "Calle", "User", "password", sucursal);
		Empleado empleado2 = new Empleado("Z9228600Q", "Empleado dos", "Test", "Calle", "User", "password", sucursal);
		
		List<Empleado> allEmpleados = Arrays.asList(empleado, empleado2);
	
		given(empleadoController.getAllEmpleados()).willReturn(allEmpleados);
	
		mvc.perform(get("http://localhost:" + String.valueOf(port) + "/empleado/")
				.contentType(APPLICATION_JSON))
	    		.andExpect(status().isOk())
	    		.andExpect(jsonPath("$", hasSize(2)))
	    		.andExpect(jsonPath("$[0].dni", is(empleado.getDni())))
	    		.andExpect(jsonPath("$[1].dni", is(empleado2.getDni())));
	}
	
	@Test
	public void getEmpleadoTest() throws Exception {
		Sucursal sucursal = new Sucursal(1L, "Sucursal", "Calle", "User");
		Empleado empleado = new Empleado("39745374V", "Empleado", "Test", "Calle", "User", "password", sucursal);
		ResponseEntity<Empleado> reEmpleado = new ResponseEntity<>(empleado, HttpStatus.OK);

	    given(empleadoController.getEmpleado(empleado.getDni())).willReturn(reEmpleado);

	    mvc.perform(get("http://localhost:" + String.valueOf(port) + "/empleado/" + empleado.getDni())
	            .contentType(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("dni", is(empleado.getDni())));
	}
	
	@Test
	public void createEmpleadoTest() throws Exception {
		Sucursal sucursal = new Sucursal(1L, "Sucursal", "Calle", "User");
		Empleado empleado = new Empleado("39745374V", "Empleado", "Test", "Calle", "User", "password", sucursal);
		ResponseEntity<Empleado> reEmpleado = new ResponseEntity<>(empleado, HttpStatus.OK);
		EmpleadoDTO empleadoDto = new EmpleadoDTO(empleado);
		
		given(empleadoController.createEmpleado(any(EmpleadoDTO.class))).willReturn(reEmpleado);
		
		mvc.perform(post("http://localhost:" + String.valueOf(port) + "/empleado/")
				.content(asJsonString(empleadoDto))
	            .contentType(APPLICATION_JSON)
	            .accept(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("dni", is(empleado.getDni())));
	}
	
	@Test
	public void updateEmpleadoTest() throws Exception {
		Sucursal sucursal = new Sucursal(1L, "Sucursal", "Calle", "User");
		Empleado empleado = new Empleado("39745374V", "Empleado", "Test", "Calle", "User", "password", sucursal);
		EmpleadoDTO empleadoDto = new EmpleadoDTO(empleado);
		empleado.setNombre("Empleado actu");
		ResponseEntity<Empleado> reEmpleado = new ResponseEntity<>(empleado, HttpStatus.OK);
		
		given(empleadoController.updateEmpleado(any(EmpleadoDTO.class))).willReturn(reEmpleado);
		
		mvc.perform(put("http://localhost:" + String.valueOf(port) + "/empleado/")
				.content(asJsonString(empleadoDto))
	            .contentType(APPLICATION_JSON)
	            .accept(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("nombre", is(empleado.getNombre())));
	}
	
	@Test
	public void deleteEmpleadoTest() throws Exception {
		Sucursal sucursal = new Sucursal(1L, "Sucursal", "Calle", "User");
		Empleado empleado = new Empleado("39745374V", "Empleado", "Test", "Calle", "User", "password", sucursal);
		EmpleadoDTO empleadoDto = new EmpleadoDTO(empleado);
		empleado.setMcaHabilitado(false);
		ResponseEntity<Empleado> reEmpleado = new ResponseEntity<>(empleado, HttpStatus.OK);
		
		given(empleadoController.deleteEmpleado(empleado.getDni())).willReturn(reEmpleado);
		
		mvc.perform(post("http://localhost:" + String.valueOf(port) + "/empleado/")
				.content(asJsonString(empleadoDto))
	            .contentType(APPLICATION_JSON)
	            .accept(APPLICATION_JSON));
		mvc.perform(delete("http://localhost:" + String.valueOf(port) + "/empleado/" + empleado.getDni())
				.contentType(APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("mcaHabilitado", is(empleado.getMcaHabilitado())));
	}
	
	private static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
}
