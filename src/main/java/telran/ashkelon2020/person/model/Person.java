package telran.ashkelon2020.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity(name = "Person") // for JPA, we use name "persons" in JPQL queries
@Table(name = "persons") // name "persons" use as table name in DB
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Inheritance Mapping Strategy make one table
//@Inheritance(strategy = InheritanceType.JOINED) // Inheritance Mapping Strategy make three tables with relationships (without duplicate fields)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Inheritance Mapping Strategy make three independent tables
public class Person {
	@Id // for JPA
	Integer id;
	String name;
	LocalDate birthDate;
//	@Embedded // include in table in DB
	Address address;
	
}
