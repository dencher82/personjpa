package telran.ashkelon2020.person.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2020.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{
	
	List<Person> findPersonsByName(String name);
	
	List<Person> findPersonsByBirthDateBetween(LocalDate fromDate, LocalDate toDate);
	
}
