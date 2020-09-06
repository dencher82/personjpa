package telran.ashkelon2020.person.service;

import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;

public interface PersonService {
	
	boolean addPerson(PersonDto personDto);
	
	PersonDto getPersonById(Integer id);
	
	PersonDto updatePerson(Integer id, PersonUpdateDto personUpdateDto);
	
	PersonDto deletePerson(Integer id);
	
	Iterable<PersonDto> findPersonsByName(String name);
	
	Iterable<PersonDto> fingPersonsByAges(Integer fromAge, Integer toAge);
	
}
