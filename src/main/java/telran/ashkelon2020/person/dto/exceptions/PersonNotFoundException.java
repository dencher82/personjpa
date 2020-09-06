package telran.ashkelon2020.person.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6127450328054536141L;
	
	public PersonNotFoundException(Integer id) {
		super("Person with ID " + id + " not found");
	}
	
}
