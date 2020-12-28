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
import org.springframework.core.env.Environment;
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
import com.carlosrey.springboot.backend.apirest.models.entity.Expediente;
import com.carlosrey.springboot.backend.apirest.models.services.IExpedienteService;

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
public class ExpedienteRestController {

	@Autowired
	private IExpedienteService expedienteService;

    @Autowired
    private Environment environment;
    
    @Autowired
    private Config config;
	
    private static final Logger logger = LoggerFactory.getLogger(ClassName.class);

	@GetMapping("/expediente")
	public  List<Expediente> index() 
	
	{
		logger.info("inicio metodo index ");
		List<Expediente> expediente = expedienteService.findAll();
		return expediente;
	}
	
	@GetMapping("/expediente/page/{page}")
	public  Page<Expediente> index(@PathVariable Integer page) 
	
	{
		logger.info("inicio metodo index Paginacion ");
		Page<Expediente> expediente = expedienteService.findAll(PageRequest.of(page,4));
		return expediente;
	}
	
	
	
	@GetMapping("expediente/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		logger.info("inicio metodo show ");
		
		Expediente expediente = null;
		Map<String,Object> response = new HashMap<>();
		

		try {
			 expediente = expedienteService.findById(id);
		} catch (Exception e) {
			response.put("mensaje", "El expediente ID: ".concat(id.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Expediente>(expediente,HttpStatus.OK);
	}
	
	@PostMapping("expediente")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Expediente expediente, BindingResult result)
	{
		
		logger.info("inicio metodo create ");
		
		
		Expediente expedienteNew = null;
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
			expedienteNew = expedienteService.save(expediente);
		} catch (DataAccessException e) {
			response.put("mensaje", "El expediente ID: ".concat(expediente.getIdExpediente().toString().concat("no se pudo crear correctamente")));
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "El expediente ID: ".concat(expediente.getIdExpediente().toString().concat("se creo correctamente")));
		response.put("expediente", expedienteNew);
		
		return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}

	@PutMapping("expediente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Expediente expediente,@PathVariable Long id)
	{
		logger.info("inicio metodo update ");
		
		Map<String,Object> response = new HashMap<>();
		
		try {
		
		Expediente expedienteActual = expedienteService.findById(id);
		
		expedienteActual.setNumPoliza(expediente.getNumPoliza());
		expedienteActual.setAsegurador(expediente.getAsegurador());
		expedienteActual.setTomadorSeguro(expediente.getTomadorSeguro());
		expedienteActual.setAseguPropietario(expediente.getAseguPropietario());
		expedienteActual.setFechaIniPoliza(expediente.getFechaIniPoliza());
		expedienteActual.setFechaFinPoliza(expediente.getFechaFinPoliza());
		expedienteActual.setRevision(expediente.getRevision());
		expedienteActual.setTipoVivienda(expediente.getTipoVivienda());
		expedienteActual.setTipoRevision(expediente.getTipoVivienda());
		expedienteActual.setDireccion(expediente.getDireccion());
		expedienteActual.setAnoConstrucion(expediente.getAnoConstrucion());
		expedienteActual.setSuperficieVivienda(expediente.getSuperficieVivienda());
		expedienteActual.setEstructura(expediente.getEstructura());
		expedienteActual.setEdificaValoraReposicion(expediente.getEdificaValoraReposicion());
		expedienteActual.setMobiliarioEnseres(expediente.getMobiliarioEnseres());
		expedienteActual.setRcEdificacion(expediente.getRcEdificacion());
		expedienteActual.setActivo(expediente.getActivo());
	
		expedienteService.save(expediente);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "El expediente ID: ".concat(expediente.getIdExpediente().toString().concat("no existe en la base de datos!")));
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("expediente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		logger.info("inicio metodo delete ");
		
		Map<String,Object> response = new HashMap<>();
		
		try {
		
		expedienteService.delete(id);
		
		} catch (DataAccessException e) {
		
			response.put("mensaje", "El expediente ID: ".concat(id.toString().concat("no existe en la base de datos!")));
			response.put("errors",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		response.put("mensaje", "El expediente ID: ".concat(id.toString().concat("ha sido eliminado con exito")));
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/expediente/exportar")
	public  List<Expediente> report() throws JRException, ClassNotFoundException, SQLException 
	
	{
		logger.info("inicio metodo report ");

		String pathReport = "C:/Users/carlos pc/JaspersoftWorkspace/MyReports/Invoice.jrxml";

		// Compilar el fichero jrxml
		JasperReport report = JasperCompileManager.compileReport(pathReport);

		// Rellenamos los parámetros del informe con valores
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("the_image", "src/main/files/the_image.png");

		// rellenamos el informe con datos y parámetros
		JasperPrint print = JasperFillManager.fillReport(report, parameters, config.getDataSource().getConnection());

		String pathDestiny = "C:/Users/carlos pc/JaspersoftWorkspace/MyReports/Invoice" + LocalDate.now() + ".pdf";

		JasperExportManager.exportReportToPdfFile(print, pathDestiny);

		return expedienteService.findAll();
				
	}

	
}
