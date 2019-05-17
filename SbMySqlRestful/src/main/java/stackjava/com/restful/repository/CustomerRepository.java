package stackjava.com.restful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stackjava.com.restful.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public List<Customer> findByName(String name);
	public List<Customer> findByNameAndAddress(String name, String address);
}
