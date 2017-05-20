package ar.edu.unlam.diit.scaw.beans;
//comentario de prueba del git
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.Person;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.PersonService;

@ManagedBean(name = "personBean", eager = true)
@SessionScoped
public class PersonBean implements Serializable {

	private static final long serialVersionUID = 2L;

	private String firstName = null;
	private String lastName = null;
	private String email = null;
	
	//Spring Inject
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
	PersonService service = (PersonService) context.getBean("personService");
	
	
	public PersonBean() {
		super();
	}
	
	public String save() {
		
		Person person = buildPerson();
		
		service.save(person);
		
		return "welcome";
	}
	
	
	public List<Person> getFindAll() {
		List<Person> list = service.findAll();
		return list;
	}
	
	private Person buildPerson() {
		Person person = new Person();
		person.setFirstName(this.firstName);
		person.setLastName(this.lastName);
		person.setEmail(this.email);
		
		return person;
	}
	
	

	public PersonBean(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
