package com.luv2code.springdemo.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	Logger myLogger = Logger.getLogger(getClass().getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get current session
		Session currentSession = sessionFactory.getCurrentSession();

		// create query ... sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);

		// get result list
		List<Customer> customers = theQuery.getResultList();

		// return result list
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {

		// get current session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/update the customer ... finally ;-)
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(Integer customerId) {

		// get current session
		Session currentSession = sessionFactory.getCurrentSession();

		// get customer by id
		Customer theCustomer = currentSession.get(Customer.class, customerId);

		return theCustomer;
	}

	@Override
	public void deleteCustomer(Integer theId) {

		// get current session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);

		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {

		// get current session
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Customer> theQuery = null;

		// setup query if searchName is filled
		if (null != searchName && searchName.trim().length() > 0) {
			theQuery = currentSession.createQuery(
					"from Customer where lower(firstName) like :searchName or lower(lastName) like :searchName",
					Customer.class);
			theQuery.setParameter("searchName", "%" + searchName.trim().toLowerCase() + "%");
		} else {
			// simply select all customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}

		// execute and get result
		List<Customer> customers = theQuery.getResultList();

		return customers;
	}
}
