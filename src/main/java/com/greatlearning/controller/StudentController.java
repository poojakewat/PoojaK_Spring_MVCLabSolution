package com.greatlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.entities.Student;
import com.greatlearning.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentController {


	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listBooks(Model model) {
		List<Student> student = studentService.findAll();

		model.addAttribute("Students", student);

		return "list-student";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd( Model theModel) {

		// get the Book from the service

		Student student = new Student();
		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Students", student);

		// send over to our form
		return "update-save-student";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId") int theId, Model theModel) {

		// get the Book from the service
		Student student = studentService.findById(theId);

		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Students", student);

		// send over to our form
		return "update-save-student";
	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {

		//	System.out.println(id);
		Student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		} else
			theStudent = new Student(name, department, country);
		// save the Book
		studentService.save(theStudent);

		// use a redirect to prevent duplicate submissions
		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("bookId") int theId) {

		// delete the Book
		studentService.deleteById(theId);

		// redirect to /Books/list
		return "redirect:/student/list";

	}

	@RequestMapping("/search")
	public String search(@RequestParam("name") String name, @RequestParam("country") String country, Model theModel) {

		// check names, if both are empty then just give list of all Books

		if (name.trim().isEmpty() && country.trim().isEmpty()) {
			return "redirect:/student/list";
		} else {
			// else, search by first name and last name
			List<Student> theStudent = studentService.searchBy(name, country);

			// add to the spring model
			theModel.addAttribute("Students", theStudent);

			// send to list-Books
			return "list-student";
		}

	}



}

