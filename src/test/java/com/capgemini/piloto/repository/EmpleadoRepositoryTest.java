package com.capgemini.piloto.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Sucursal;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class EmpleadoRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmpleadoRepository empleadoRepository;
		
	@Test
	public void getAllEmpleadosTest() {
		List<Empleado> empleadosIniciales = empleadoRepository.findAll();
				
		//given
		Sucursal sucursal = new Sucursal(1L, "Sucursal", "Calle", "User");
		Empleado empleado = new Empleado("00000000T", "Empleado", "Test", "Calle", "User", "password", sucursal);
	    entityManager.persist(empleado);
	    entityManager.flush();
	
	    Empleado empleado2 = new Empleado("00000001R", "Empleado dos", "Test", "Calle", "User", "password", sucursal);
	    entityManager.persist(empleado2);
	    entityManager.flush();
	
	    //when
	    List<Empleado> empleados = empleadoRepository.findAll();
	    empleados.removeAll(empleadosIniciales);
	
	    //then
	    assertThat(empleados.size()).isEqualTo(2);
	    assertThat(empleados.get(0)).isEqualTo(empleado);
	    assertThat(empleados.get(1)).isEqualTo(empleado2);
	}

	@Test
	public void getEmpleadoTest() {
		//given
		Sucursal sucursal = new Sucursal(1L, "Sucursal", "Calle", "User");
		Empleado empleado = new Empleado("00000000T", "Empleado", "Test", "Calle", "User", "password", sucursal);
	    entityManager.persist(empleado);
	    entityManager.flush();
	
	    //when
	    Empleado testEmpleado = empleadoRepository.findByDni(empleado.getDni());
	
	    //then
	    assertThat(testEmpleado.getDni()).isEqualTo(empleado.getDni());
	}
	
	@Test
	public void updateEmpleadoTest() {
		//given
		Sucursal sucursal = new Sucursal(1L, "Sucursal", "Calle", "User");
		Empleado empleado = new Empleado("00000000T", "Empleado", "Test", "Calle", "User", "password", sucursal);
	    entityManager.persist(empleado);
	    entityManager.flush();
	    
	    //when
	    Empleado testEmpleado = SerializationUtils.clone(empleado);
	    empleado.setNombre("Empleado actu");
	    empleadoRepository.save(empleado);
	    
	    //then
	    assertThat(testEmpleado.getNombre()).isNotEqualTo(empleado.getNombre());
	    assertThat(testEmpleado.getNombre()).isEqualTo("Empleado");
	    assertThat(empleado.getNombre()).isEqualTo("Empleado actu");
	}
}
