package com.greatlearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.entities.Student;


@Service
public interface StudentService {

	public List<Student> findAll();

	Student findById(int id);

	void save(Student Student);

	void deleteById(int id);

	List<Student> searchBy(String name, String country);


}
