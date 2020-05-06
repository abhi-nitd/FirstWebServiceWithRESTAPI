package com.abhishek;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepositary extends CrudRepository<Student,Integer>
{
	List<Student> findBySubject(String subject);
	List<Student> findByName(String name);
//	@Query(value="DELETE from student WHERE name = ?1",
//			nativeQuery=true)
//	void deleteByName(String name);
	@Query(value="select name from student order by name", nativeQuery=true)
	List<String> findNameInOrder();
	@Query(value="select subject from student order by subject", nativeQuery=true)
	List<String> findSubjectInOrder();
	@Query(value="select * from student order by name", nativeQuery=true)
	List<Student> findStudentInOrderByName();
	@Query(value="select * from student order by subject", nativeQuery=true)
	List<Student> findStudentInOrderBySubject();
}
