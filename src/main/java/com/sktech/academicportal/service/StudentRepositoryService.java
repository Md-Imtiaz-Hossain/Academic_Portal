package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.StudentEntity;
import com.sktech.academicportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class StudentRepositoryService {
	
	@Autowired
	StudentRepository studentRepository;

	// Get All Student
	public List<StudentEntity> getAllStudent(){
		return studentRepository.findAll();
	}

	// Save Student
	public StudentEntity saveStudent(StudentEntity student) {
		return studentRepository.save(student);
	}

	// Get a single student by an id
	public StudentEntity getStudentById(Integer id) {
		return studentRepository.findById(id).get();
	}

	// Update student information
	public StudentEntity updateStudent(StudentEntity student) {
		return studentRepository.save(student);
	}

	// delete Student by their id
	public void deleteStudentById(Integer id) {
		studentRepository.deleteById(id);
	}




}