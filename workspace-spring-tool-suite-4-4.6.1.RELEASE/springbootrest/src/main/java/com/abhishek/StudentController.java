package com.abhishek;
//import java.awt.List;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController 
{
	@Autowired
	StudentRepositary repo;
	//private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	@GetMapping("Student")
	public List<Student> getStudent()
	{
		//log.info("All Students");
		List<Student> student = (List<Student>) repo.findAll();
		return student;
	}
	@GetMapping("Student/subject={subject}")
	List <Student> findStudentBySubject(@PathVariable String subject)
	{
		return repo.findBySubject(subject);
	}
	@GetMapping("Student/name={name}")
	List<Student> findStudentByName(@PathVariable String name)
	{
		return repo.findByName(name);
	}
	@GetMapping("Student/order/name/complete")
	List<Student> studentInSortedOrderByName()
	{
		return repo.findStudentInOrderByName();
	}
	@GetMapping("Student/order/subject/complete")
	List<Student> studentInSortedOrderBySubject()
	{
		return repo.findStudentInOrderBySubject();
	}
	@GetMapping("Student/order/name")
	public List<String> nameInSortedOrder()
	{
		return repo.findNameInOrder();
	}
	@GetMapping("Student/order/subject")
	List<String> subjectInSortedOrder()
	{
		return repo.findSubjectInOrder();
	}
	@PostMapping("Student")
	Student newStudent(@RequestBody Student newStudent) {
	    return repo.save(newStudent);
	}
	@GetMapping("Student/{id}")
	Optional<Student> one(@PathVariable int id) {
		return repo.findById(id);
	}
	@PutMapping("Student/{id}")
	Student replaceStudent(@RequestBody Student newStudent, @PathVariable int id) {
		return repo.findById(id).map(Student ->
		{
	        Student.setName(newStudent.getName());
	        Student.setRollNumber(newStudent.getRollNumber());
	        Student.setSubject(newStudent.getSubject());
	        return repo.save(Student);
	    })
	      .orElseGet(() -> {
	        newStudent.setRollNumber(id);
	        return repo.save(newStudent);
	      });
	  }

	@DeleteMapping("Student/{id}")
	void deleteEmployee(@PathVariable int id) {
		repo.deleteById(id);
	}
//	@DeleteMapping("Student/name={name}")
//	void deleteEmployeeByName(@PathVariable String name)
//	{
//		repo.deleteByName(name);
//	}
	


}
