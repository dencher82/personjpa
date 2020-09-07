package telran.ashkelon2020.person.controller;

import org.modelmapper.ModelMapper;
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
import telran.ashkelon2020.person.dto.CityPopulationDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;
import telran.ashkelon2020.person.dto.SalariesDto;
import telran.ashkelon2020.person.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@Autowired
	ModelMapper modelMapper;
		
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
	
	@PostMapping("/age/period")
	public Iterable<PersonDto> findPersonsByAges(@RequestBody AgesDto agesDto) {
		return personService.findPersonsByAges(agesDto.getAgeFrom(), agesDto.getAgeTo());
	}
	
	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findPersonsByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}
	
	@GetMapping("/population/city")
	public Iterable<CityPopulationDto> getCityPopulation() {
		return personService.getCityPopulations();
	}
	
	@PostMapping("/employee/salary/range")
	public Iterable<PersonDto> findEmployeesBySalary(@RequestBody SalariesDto salariesDto){
		return personService.findEmployeesBySalary(salariesDto.getMin(), salariesDto.getMax());
	}
	
	@GetMapping("/children")
	public Iterable<PersonDto> getChildren() {
		return personService.getChildren();
	}
	
}
