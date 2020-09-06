package telran.ashkelon2020.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.person.dto.AgesDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;
import telran.ashkelon2020.person.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);		
	}
	
	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.getPersonById(id);
	} 
	
	@PutMapping("/{id}")
	public PersonDto updatePerson(@PathVariable Integer id, @RequestBody PersonUpdateDto personUpdateDto) {
		return personService.updatePerson(id, personUpdateDto);
	}
	
	@DeleteMapping("/{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}
	
	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findPersonsByName(@PathVariable String name) {
		return personService.findPersonsByName(name);
	}
	
	@GetMapping("/period")
	public Iterable<PersonDto> findPersonsByAges(@RequestBody AgesDto agesDto) {
		return personService.fingPersonsByAges(agesDto.getAgeFrom(), agesDto.getAgeTo());
	}
	
}
