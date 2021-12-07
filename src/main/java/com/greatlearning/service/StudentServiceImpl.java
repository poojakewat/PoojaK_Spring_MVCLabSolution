package com.greatlearning.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.entities.Student;

@Service
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory) {
		sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	public List<Student> findAll() {
		Transaction tx = session.beginTransaction();
		List<Student> students = session.createQuery("from Student").list();
		tx.commit();
		return students;
	}

	@Override
	@Transactional
	public Student findById(int id) {
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		tx.commit();
		return student;
	}

	@Override
	public void save(Student student) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();

	}

	@Transactional
	@Override
	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		if (student!= null)
			session.delete(student);
		tx.commit();

	}

	@Transactional
	@Override
	public List<Student> searchBy(String name, String country) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		String query = "";
		if (name.length() != 0 && country.length() != 0) {
			query = "from Student where name like '%" + name + "%' or author like '%" + country + "%'";
		} else if (name.length() != 0) {
			query = "from Student where name like '%" + name + "%'";
		} else if (country.length() != 0) {
			query = "from Student where country like '%" + country + "%'";
		}

		List<Student> Students = session.createQuery(query).list();
		tx.commit();
		return Students;
	}


}
