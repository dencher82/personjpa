package telran.ashkelon2020.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.ashkelon2020.person.dto.CityPopulationDto;
import telran.ashkelon2020.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{
	
	@Query(value = "select * from persons where name=?1", nativeQuery = true) // native query has a lots limitations
	List<Person> findPersonsByName(String name);
	
	List<Person> findPersonsByBirthDateBetween(LocalDate fromDate, LocalDate toDate);
	
//	@Query("select p from Person p where p.address.city=?1") // Java Persistent Query Language
//	Stream<Person> findPersonsByAddressCity(String city);
	
	@Query("select p from Person p where p.address.city=:city")
	Stream<Person> findPersonsByAddressCity(@Param("city") String city);
	
	@Query("select new telran.ashkelon2020.person.dto.CityPopulationDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc")
	List<CityPopulationDto> getCityPopulation();
	
	@Query("select p from Person p where p.salary between ?1 and ?2")
	Stream<Person> findEmployeesBySalaryBetween(Integer min, Integer max);
	
	@Query("select p from Person p where p.kindergarten is not null")
	Stream<Person> getChildren();
	
}
