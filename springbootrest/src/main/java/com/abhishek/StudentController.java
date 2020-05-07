package com.abhishek;
//import java.awt.List;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger log = LoggerFactory.getLogger(StudentController.class);
	@GetMapping("Student")
	public List<Student> getStudent()
	{
		log.trace("Retrived all students!!");
		List<Student> student = (List<Student>) repo.findAll();
		if(student.size()==0)
		{
			log.error("No Student Registered!");
		}
		return student;
	}
	@GetMapping("Student/subject={subject}")
	List <Student> findStudentBySubject(@PathVariable String subject)
	{
		log.trace("Retrieving all students with Subject : "+subject);
		List <Student> student=(List<Student>) repo.findBySubject(subject);
		if(student.size()==0)
		{
			log.error("No Student with subject : "+subject);
		}
		return student;
	}
	@GetMapping("Student/name={name}")
	List<Student> findStudentByName(@PathVariable String name)
	{
		log.trace("Retrieving all students with Name : "+name);
		List <Student> student=(List<Student>) repo.findByName(name);
		if(student.size()==0)
		{
			log.error("No Student with Name : "+name);
		}
		return student;
	}
	@GetMapping("Student/order/name/complete")
	List<Student> studentInSortedOrderByName()
	{
		log.trace("Retrieving all students Ordered by their Name ");
		List <Student> student=(List<Student>) repo.findStudentInOrderByName();
		if(student.size()==0)
		{
			log.error("No Student Registered!!");
		}
		return student;
	}
	@GetMapping("Student/order/subject/complete")
	List<Student> studentInSortedOrderBySubject()
	{
		log.trace("Retrieving all students Ordered by their Subject ");
		List <Student> student=(List<Student>) repo.findStudentInOrderBySubject();
		if(student.size()==0)
		{
			log.error("No Student Registered!!");
		}
		return student;
	}
	@GetMapping("Student/order/name")
	public List<String> nameInSortedOrder()
	{
		log.trace("Retrieving Names of all students in Order!");
		List <String> student=(List<String>) repo.findNameInOrder();
		if(student.size()==0)
		{
			log.error("No Student Registered!!");
		}
		return student;
	}
	@GetMapping("Student/order/subject")
	List<String> subjectInSortedOrder()
	{
		log.trace("Retrieving Subjects of all students in Order!");
		List <String> student=(List<String>) repo.findSubjectInOrder();
		if(student.size()==0)
		{
			log.error("No Student Registered!!");
		}
		return student;
	}
	@PostMapping("Student")
	Student newStudent(@RequestBody Student newStudent) 
	{
		log.trace("Inserting Student with RollNumber :"+newStudent.getRollNumber()+" Name : "+newStudent.getName()+" Subject : "+newStudent.getSubject());
		int roll = newStudent.getRollNumber();
		Optional <Student> student= repo.findById(roll);
		if(student.isEmpty())
		{
			return repo.save(newStudent);
		}
		else
		{
			log.error("Student with RollNumber :"+roll+" already exists!!");
			return null;
		}
	    
	}
	@GetMapping("Student/{id}")
	Optional<Student> studentById(@PathVariable int id)
	{
		log.trace("Retrieving Student with RollNumber : "+id);
		Optional <Student> student= repo.findById(id);
		
		if(student.isEmpty())
		{
			log.error("No Student with RollNumber : "+id);
		}
		return repo.findById(id);
	}
	@PutMapping("Student/{id}")
	Student replaceStudent(@RequestBody Student newStudent, @PathVariable int id)
	{
		log.trace("Updating records for student with RollNumber : "+id);
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
	void deleteEmployee(@PathVariable int id) 
	{
		log.trace("Deleting Student with RollNumber : "+id);
		Optional <Student> student= repo.findById(id);
		if(student.isEmpty())
		{
			log.error("No Student with RollNumber : "+id);
		}
		else
		{
			repo.deleteById(id);
		}
		
		
	}
	@DeleteMapping("Student/name={name}")
	void deleteEmployeeByName(@PathVariable String name)
	{
		log.trace("Deleting Student with Name : "+name);
		List <Student> student= repo.findByName(name);
		if(student.isEmpty())
		{
			log.error("No Student with Name : "+name);
		}
		else
		{
			repo.deleteByName(name);
		}
		
	}
	@DeleteMapping("Student/subject={subject}")
	void deleteEmployeeBySubject(@PathVariable String subject) 
	{
		log.trace("Deleting Student with Subject : "+subject);
		List <Student> student= repo.findBySubject(subject);
		if(student.isEmpty())
		{
			log.error("No Student with Subject : "+subject);
		}
		else
		{
			repo.deleteBySubject(subject);
		}
		
	}
	
}
