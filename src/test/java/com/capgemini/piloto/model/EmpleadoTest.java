package com.capgemini.piloto.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.piloto.errors.impl.DniFormatException;
import com.capgemini.piloto.errors.impl.EmailFormatException;
import com.capgemini.piloto.errors.impl.PasswordFormatException;
import com.capgemini.piloto.errors.impl.TelefonoFormatException;
import com.capgemini.piloto.errors.impl.TextoFormatException;
import com.capgemini.piloto.util.validator.ComunValidator;
import com.capgemini.piloto.util.validator.PersonValidator;

public class EmpleadoTest {

	Sucursal sucursal;
	Empleado empleado;
	
	@Before
	public void setUp() {
		sucursal = new Sucursal(1L, "Sucursal", "Calle", "User");
		empleado = new Empleado("39745374V", "Empleado", "Test", "Calle", "User", "password", sucursal);
		empleado.setFijo("985291057");
		empleado.setMovil("657102359");
		empleado.setEmail("test@gmail.com");
	}
	
	@Test
	public void testGeneral() {
		PersonValidator.validateDni(empleado.getDni());
		PersonValidator.validateNombre(empleado.getNombre());
		ComunValidator.validateTexto(empleado.getApellidos(), "apellidos", 30);
		ComunValidator.validateTexto(empleado.getDireccion(), "dirección", 50);
		PersonValidator.validateTelefonoFijo(empleado.getFijo());
		PersonValidator.validateTelefonoMovil(empleado.getMovil());
		PersonValidator.validateEmail(empleado.getEmail());
		PersonValidator.validatePassword(empleado.getPassword());
		assertTrue(empleado.getSucursal() != null);
	}

	@Test
	public void testDni() {
		// Letra incorrecta
		empleado.setDni("39745374P");
		DniFormatException e = assertThrows(DniFormatException.class, () -> PersonValidator.validateDni(empleado.getDni()));
		assertEquals("Formato de NIF/NIE incorrecto", e.getMessage());
		// Sin letra
		empleado.setDni("39745374");
		e = assertThrows(DniFormatException.class, () -> PersonValidator.validateDni(empleado.getDni()));
		assertEquals("Formato de NIF/NIE incorrecto", e.getMessage());
		// Con un número menos
		empleado.setDni("9745374K");
		e = assertThrows(DniFormatException.class, () -> PersonValidator.validateDni(empleado.getDni()));
		assertEquals("Formato de NIF/NIE incorrecto", e.getMessage());
		// Correcto otra vez
		empleado.setDni("39745374V");
		PersonValidator.validateDni(empleado.getDni());		
	}
	
	@Test
	public void testNombre() {
		// Con ñ funciona
		empleado.setNombre("Empleado ñ");
		PersonValidator.validateNombre(empleado.getNombre());
		// Con números
		empleado.setNombre("Empleado 1");
		TextoFormatException e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateNombre(empleado.getNombre()));
		assertEquals("Formato de texto de nombre es incorrecto", e.getMessage());
		// Demasiado largo
		empleado.setNombre("Empleadooooooooo");
		e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateNombre(empleado.getNombre()));
		assertEquals("Formato de texto de nombre es incorrecto", e.getMessage());
		// Vacío
		empleado.setNombre("");
		e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateNombre(empleado.getNombre()));
		assertEquals("Formato de texto de nombre es incorrecto", e.getMessage());
		// Solo con espacios en blanco
		empleado.setNombre("       ");
		e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateNombre(empleado.getNombre()));
		assertEquals("Formato de texto de nombre es incorrecto", e.getMessage());
		// Correcto otra vez
		empleado.setNombre("Empleado");
		PersonValidator.validateNombre(empleado.getNombre());
	}
	
	@Test
	public void testApellidos() {
		// Con ñ funciona
		empleado.setApellidos("Test ñ");
		PersonValidator.validateApellidos(empleado.getApellidos());
		// Con números
		empleado.setApellidos("Test 1");
		TextoFormatException e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateApellidos(empleado.getApellidos()));
		assertEquals("Formato de texto de apellidos es incorrecto", e.getMessage());
		// Demasiado largo
		empleado.setApellidos("Teeeeeeeeeeeeeeeeeeeeeeeeeeeest");
		e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateApellidos(empleado.getApellidos()));
		assertEquals("Formato de texto de apellidos es incorrecto", e.getMessage());
		// Vacío
		empleado.setApellidos("");
		e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateApellidos(empleado.getApellidos()));
		assertEquals("Formato de texto de apellidos es incorrecto", e.getMessage());
		// Solo con espacios en blanco
		empleado.setApellidos("       ");
		e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateApellidos(empleado.getApellidos()));
		assertEquals("Formato de texto de apellidos es incorrecto", e.getMessage());
		// Correcto otra vez
		empleado.setApellidos("Test");
		PersonValidator.validateApellidos(empleado.getApellidos());
	}
	
	@Test
	public void testDireccion() {
		// Con ñ funciona
		empleado.setDireccion("Calle ñ");
		PersonValidator.validateDireccion(empleado.getDireccion());
		// Con números funciona
		empleado.setDireccion("Calle 1");
		PersonValidator.validateDireccion(empleado.getDireccion());
		// Con símbolos funciona
		empleado.setDireccion("C/ Falsa 1 5ºE");
		PersonValidator.validateDireccion(empleado.getDireccion());
		// Demasiado largo
		empleado.setDireccion("Calleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		TextoFormatException e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateDireccion(empleado.getDireccion()));
		assertEquals("Formato de texto de dirección es incorrecto", e.getMessage());
		// Vacío
		empleado.setDireccion("");
		e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateDireccion(empleado.getDireccion()));
		assertEquals("Formato de texto de dirección es incorrecto", e.getMessage());
		// Solo con espacios en blanco
		empleado.setDireccion("       ");
		e = assertThrows(TextoFormatException.class, () -> PersonValidator.validateDireccion(empleado.getDireccion()));
		assertEquals("Formato de texto de dirección es incorrecto", e.getMessage());
		// Correcto otra vez
		empleado.setDireccion("Calle");
		PersonValidator.validateDireccion(empleado.getDireccion());
	}
	
	@Test
	public void testFijo() {
		// Empezando por 8
		empleado.setFijo("885291057");
		PersonValidator.validateTelefonoFijo(empleado.getFijo());
		// Un número de menos
		empleado.setFijo("98529105");
		TelefonoFormatException e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoFijo(empleado.getFijo()));
		assertEquals("Formato de teléfono fijo incorrecto", e.getMessage());
		// Con prefijo bien
		empleado.setFijo("+34985291057");
		PersonValidator.validateTelefonoFijo(empleado.getFijo());
		empleado.setFijo("0034985291057");
		PersonValidator.validateTelefonoFijo(empleado.getFijo());
		// Con prefijo mal
		empleado.setFijo("34985291057");
		e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoFijo(empleado.getFijo()));
		assertEquals("Formato de teléfono fijo incorrecto", e.getMessage());
		// Con letras
		empleado.setFijo("treintaycuatro985291057");
		e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoFijo(empleado.getFijo()));
		assertEquals("Formato de teléfono fijo incorrecto", e.getMessage());
		// Con espacios en blanco
		empleado.setFijo("9 8 5 2 9 1 0 5 7");
		e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoFijo(empleado.getFijo()));
		assertEquals("Formato de teléfono fijo incorrecto", e.getMessage());
		// Vacío
		empleado.setFijo("");
		e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoFijo(empleado.getFijo()));
		assertEquals("Formato de teléfono fijo incorrecto", e.getMessage());
		// Correcto otra vez
		empleado.setFijo("985291057");
		PersonValidator.validateTelefonoFijo(empleado.getFijo());
	}
	
	@Test
	public void testMovil() {
		// Empezando por 7
		empleado.setMovil("757102359");
		PersonValidator.validateTelefonoMovil(empleado.getMovil());
		// Un número de menos
		empleado.setMovil("65710235");
		TelefonoFormatException e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoMovil(empleado.getMovil()));
		assertEquals("Formato de teléfono móvil incorrecto", e.getMessage());
		// Con prefijo bien
		empleado.setMovil("+34657102359");
		PersonValidator.validateTelefonoMovil(empleado.getMovil());
		empleado.setMovil("0034657102359");
		PersonValidator.validateTelefonoMovil(empleado.getMovil());
		// Con prefijo mal
		empleado.setMovil("34657102359");
		e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoMovil(empleado.getMovil()));
		assertEquals("Formato de teléfono móvil incorrecto", e.getMessage());
		// Con letras
		empleado.setMovil("treintaycuatro657102359");
		e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoMovil(empleado.getMovil()));
		assertEquals("Formato de teléfono móvil incorrecto", e.getMessage());
		// Con espacios en blanco
		empleado.setMovil("6 5 7 1 0 2 3 5 9");
		e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoMovil(empleado.getMovil()));
		assertEquals("Formato de teléfono móvil incorrecto", e.getMessage());
		// Vacío
		empleado.setMovil("");
		e = assertThrows(TelefonoFormatException.class, () -> PersonValidator.validateTelefonoMovil(empleado.getMovil()));
		assertEquals("Formato de teléfono móvil incorrecto", e.getMessage());
		// Correcto otra vez
		empleado.setMovil("657102359");
		PersonValidator.validateTelefonoMovil(empleado.getMovil());
	}
	
	@Test
	public void testEmail() {
		// Sin .com
		empleado.setEmail("test@gmail");
		EmailFormatException e = assertThrows(EmailFormatException.class, () -> PersonValidator.validateEmail(empleado.getEmail()));
		assertEquals("Formato de e-mail incorrecto", e.getMessage());
		// Sin @
		empleado.setEmail("testgmail.com");
		e = assertThrows(EmailFormatException.class, () -> PersonValidator.validateEmail(empleado.getEmail()));
		assertEquals("Formato de e-mail incorrecto", e.getMessage());
		// Sin dominio
		empleado.setEmail("test@.com");
		e = assertThrows(EmailFormatException.class, () -> PersonValidator.validateEmail(empleado.getEmail()));
		assertEquals("Formato de e-mail incorrecto", e.getMessage());
		// Sin nombre del correo
		empleado.setEmail("@gmail.com");
		e = assertThrows(EmailFormatException.class, () -> PersonValidator.validateEmail(empleado.getEmail()));
		assertEquals("Formato de e-mail incorrecto", e.getMessage());
		// Correcto otra vez 
		empleado.setEmail("test@gmail.com");
		PersonValidator.validateEmail(empleado.getEmail());
	}
	
	@Test
	public void testPassword() {
		// Muy corta
		empleado.setPassword("pasword");
		PasswordFormatException e = assertThrows(PasswordFormatException.class, () -> PersonValidator.validatePassword(empleado.getPassword()));
		assertEquals("Formato de contraseña incorrecto", e.getMessage());
		// Muy larga
		empleado.setPassword("passwoooooooooooooooooooooooooord");
		e = assertThrows(PasswordFormatException.class, () -> PersonValidator.validatePassword(empleado.getPassword()));
		assertEquals("Formato de contraseña incorrecto", e.getMessage());
		// Con símbolos
		empleado.setPassword("password100%segura");
		e = assertThrows(PasswordFormatException.class, () -> PersonValidator.validatePassword(empleado.getPassword()));
		assertEquals("Formato de contraseña incorrecto", e.getMessage());
	}
}
