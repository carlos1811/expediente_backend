package com.carlosrey.springboot.backend.apirest.controllers;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carlosrey.springboot.backend.apirest.configuration.Config;
import com.carlosrey.springboot.backend.apirest.models.entity.Cliente;
import com.carlosrey.springboot.backend.apirest.models.entity.Provincia;
import com.carlosrey.springboot.backend.apirest.models.services.IClienteService;

import javassist.bytecode.stackmap.TypeData.ClassName;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

   
    @Autowired
    private Config config;
	
    private static final Logger logger = LoggerFactory.getLogger(ClassName.class);

	@GetMapping("/clientes")
	public  List<Cliente> getClientes() 
	
	{
		logger.info("inicio metodo index ");
		List<Cliente> cliente = clienteService.findAll();
		return cliente;
	}
		
	@GetMapping("clientes/{id}")
	public ResponseEntity<?> getClientesId(@PathVariable Long id)
	{
		logger.info("inicio metodo show ");
		
		Cliente cliente = null;
		Map<String,Object> response = new HashMap<>();
		

		try {
			 cliente = clienteService.findById(id);
		} catch (Exception e) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			//return new ResponseEntity<Cliente>(cliente,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	@GetMapping("/clientes/page/{page}")
	public  Page<Cliente> index(@PathVariable Integer page) 
	
	{
		logger.info("inicio metodo index Paginacion ");
		Page<Cliente> cliente = clienteService.findAll(PageRequest.of(page,4));
		return cliente;
	}
	
	@PostMapping("clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result)
	{
		
		logger.info("inicio metodo create ");
		
		
		Cliente clienteNew = null;
		Map<String,Object> response = new HashMap<>();
		
		if (result.hasErrors()){

			List<String> errors = result.getFieldErrors().stream().
					map( err -> 
					" El campo '" + err.getField() + "' " + err.getDefaultMessage()).
					collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}	
			
		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "El cliente ID: ".concat(cliente.getIdCliente().toString().concat("no se pudo crear correctamente")));
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
			String  mensaje= "El cliente ID: ".concat(cliente.getIdCliente().toString().concat("se creo correctamente"));
		
		
		response.put("cliente", clienteNew);
		response.put("mensaje", mensaje);
		
		
		return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}

	@PutMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Cliente cliente,@PathVariable Long id)
	{
		logger.info("inicio metodo update ");
		
		Map<String,Object> response = new HashMap<>();
		
		try {
		
		Cliente clienteActual = clienteService.findById(id);
		
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setEmail(cliente.getEmail());
		clienteActual.setFechaPrueba(cliente.getFechaPrueba());
		clienteActual.setProvincia(cliente.getProvincia());
		
		clienteService.save(cliente);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "El cliente ID: ".concat(cliente.getIdCliente().toString().concat("no existe en la base de datos!")));
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "Actualizado correctamente el cliente");
		
		return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		logger.info("inicio metodo delete ");
		
		Map<String,Object> response = new HashMap<>();
		
		try {
		
		clienteService.delete(id);
		
		} catch (DataAccessException e) {
		
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat("no existe en la base de datos!")));
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		response.put("mensaje", "El cliente ID: ".concat(id.toString().concat("ha sido eliminado con exito")));
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/clientes/exportar")
	public  ResponseEntity<?> report() throws JRException, ClassNotFoundException, SQLException 
	
	{
		logger.info("inicio metodo report ");
		
		Map<String,Object> response = new HashMap<>();

		String pathReport = "C:/Users/carlos pc/JaspersoftWorkspace/MyReports/Invoice.jrxml";

		
		try {
		// Compilar el fichero jrxml
		JasperReport report = JasperCompileManager.compileReport(pathReport);

		// Rellenamos los parámetros del informe con valores
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("the_image", "src/main/files/the_image.png");

		// rellenamos el informe con datos y parámetros
		JasperPrint print = JasperFillManager.fillReport(report, parameters, config.getDataSource().getConnection());

		String pathDestiny = "C:/Users/carlos pc/JaspersoftWorkspace/MyReports/Invoice" + LocalDate.now() + ".pdf";

		JasperExportManager.exportReportToPdfFile(print, pathDestiny);
		
		} catch (Exception e) {
			
			response.put("mensaje", "Se ha producido un problema en la exportacion");

				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		response.put("mensaje", "la exportación ha sido correcta");
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
				
	}
	
	
	@GetMapping("/clientes/provincia")
	public  List<Provincia> provincias() 
	
	{
		logger.info("inicio metodo index ");
		List<Provincia> provincias = clienteService.findAllProvincias();
		return provincias;
	}


	
	
	
}
