package telran.ashkelon2020.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.person.dao.PersonRepository;
import telran.ashkelon2020.person.dto.ChildDto;
import telran.ashkelon2020.person.dto.CityPopulationDto;
import telran.ashkelon2020.person.dto.EmployeeDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.dto.PersonUpdateDto;
import telran.ashkelon2020.person.dto.exceptions.PersonNotFoundException;
import telran.ashkelon2020.person.model.Child;
import telran.ashkelon2020.person.model.Employee;
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
		if (personDto.getClass().equals(ChildDto.class)) {
			Child child = modelMapper.map(personDto, Child.class);
			personRepository.save(child);
		} else if (personDto.getClass().equals(EmployeeDto.class)) {
			Employee employee = modelMapper.map(personDto, Employee.class);
			personRepository.save(employee);
		} else {
			Person person = modelMapper.map(personDto, Person.class);
			personRepository.save(person);
		}
		return true;
	}

	@Override
	public PersonDto getPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		if (person.getClass().equals(Child.class)) {
			return modelMapper.map(person, ChildDto.class);
		} else if (person.getClass().equals(Employee.class)) {
			return modelMapper.map(person, EmployeeDto.class);
		} else {
			return modelMapper.map(person, PersonDto.class);
		}
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
	public Iterable<PersonDto> findPersonsByAges(Integer fromAge, Integer toAge) {
		LocalDate fromDate = LocalDate.now().minusYears(toAge).plusDays(1);
		LocalDate toDate = LocalDate.now().minusYears(fromAge);
		System.out.println(fromDate);
		System.out.println(toDate);
		return personRepository.findPersonsByBirthDateBetween(fromDate, toDate)
				.stream()
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true) // need to operate with stream
	public Iterable<PersonDto> findPersonsByCity(String city) {
		return personRepository.findPersonsByAddressCity(city)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<CityPopulationDto> getCityPopulations() {
		return personRepository.getCityPopulation();
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findEmployeesBySalary(Integer min, Integer max) {
		return personRepository.findEmployeesBySalaryBetween(min, max)
				.map(e -> modelMapper.map(e, EmployeeDto.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> getChildren() {
		return personRepository.getChildren()
				.map(e -> modelMapper.map(e, ChildDto.class))
				.collect(Collectors.toList());
	}

}
