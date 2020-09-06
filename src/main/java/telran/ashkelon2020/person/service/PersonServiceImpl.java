package telran.ashkelon2020.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2020.person.dao.PersonRepository;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;
import telran.ashkelon2020.person.dto.exceptions.PersonNotFoundException;
import telran.ashkelon2020.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		Person person = modelMapper.map(personDto, Person.class);
		personRepository.save(person);
		return true;
	}

	@Override
	public PersonDto getPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePerson(Integer id, PersonUpdateDto personUpdateDto) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		if (personUpdateDto.getName() != null) {
			person.setName(personUpdateDto.getName());
		}
		if (personUpdateDto.getBirthDate() != null) {
			person.setBirthDate(personUpdateDto.getBirthDate());
		}
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		personRepository.deleteById(id);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findPersonsByName(String name) {
		return personRepository.findPersonsByName(name)
				.stream()
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> fingPersonsByAges(Integer fromAge, Integer toAge) {
		LocalDate fromDate = LocalDate.now().minusYears(toAge).plusDays(1);
		LocalDate toDate = LocalDate.now().minusYears(fromAge);
		System.out.println(fromDate);
		System.out.println(toDate);
		return personRepository.findPersonsByBirthDateBetween(fromDate, toDate)
				.stream()
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

}
