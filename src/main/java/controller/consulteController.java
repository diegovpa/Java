package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Consult;
import resources.InvalidConsultException;
import service.ConsultService;

@RestController
@RequestMapping("/consultas")
public class consulteController {
	
	@Autowired
	private ConsultService consultService;
	
	@PostMapping("/sc")
	public ResponseEntity<?> scheduleAppointment(@RequestBody Consult consult){
		try {
			Consult scheduleAppointment = consultService.scheduleAppointment(consult);
			return ResponseEntity.ok(scheduleAppointment);
       } catch (InvalidConsultException e) {
    	   return ResponseEntity.badRequest().body(e.getMessage());
	}
		
	}
	
	@GetMapping("/list")
	public List<Consult> ConsultList(){
		return consultService.consultList();
	}
	
	
	@GetMapping("/list/{id}")
	public ResponseEntity<Optional<Consult>> pqsConsult(@PathVariable Long id){
		try { 
		    Optional<Consult> pqsConsult = consultService.pqsConsult(id);
			return ResponseEntity.ok().body(pqsConsult);
			
		} catch (InvalidConsultException e) {
			return ResponseEntity.noContent().build();
		}
		
	}

}
