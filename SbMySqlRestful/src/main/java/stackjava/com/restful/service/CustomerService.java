package stackjava.com.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stackjava.com.restful.entities.Customer;
import stackjava.com.restful.repository.CustomerRepository;
import stackjava.com.restful.service.ServiceResult.Status;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	public ServiceResult findByName(String name) {
		ServiceResult result = new ServiceResult();
		result.setData(customerRepo.findByName(name));
		return result;
	}
	
	public ServiceResult findByNameAndAddress(String name, String address) {
		ServiceResult result = new ServiceResult();
		result.setData(customerRepo.findByNameAndAddress(name, address));
		return result;
	}
	
	public ServiceResult findAll() {
		ServiceResult result = new ServiceResult();
		result.setData(customerRepo.findAll());
		return result;
	}
	
	public ServiceResult findById(int id) {
		ServiceResult result = new ServiceResult();
		Customer customer = customerRepo.findById(id).orElse(null);
		result.setData(customer);
		return result;
	}

	public ServiceResult create(Customer customer) {
		ServiceResult result = new ServiceResult();
		result.setData(customerRepo.save(customer));
		return result;
	}

	public ServiceResult update(Customer customer) {
		ServiceResult result = new ServiceResult();

		if (!customerRepo.findById(customer.getId()).isPresent()) {
			result.setStatus(Status.FAILED);
			result.setMessage("Customer Not Found");
		} else {
			result.setData(customerRepo.save(customer));
		}
		return result;
	}

	public ServiceResult delete(int id) {
		ServiceResult result = new ServiceResult();

		Customer customer = customerRepo.findById(id).orElse(null);
		if (customer == null) {
			result.setStatus(Status.FAILED);
			result.setMessage("Customer Not Found");
		} else {
			customerRepo.delete(customer);
			result.setMessage("success");
		}
		return result;
	}
}
