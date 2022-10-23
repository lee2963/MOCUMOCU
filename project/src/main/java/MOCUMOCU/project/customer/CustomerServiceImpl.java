package MOCUMOCU.project.customer;

import MOCUMOCU.project.customer.form.ChangePhoneNumDTO;
import MOCUMOCU.project.customer.form.CustomerInfoDTO;
import MOCUMOCU.project.customer.form.CustomerLoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Long join(Customer customer) {
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    public void withdrawal(Long id) {
        Customer findCustomer = customerRepository.findOne(id);
        customerRepository.remove(findCustomer);
    }

    @Override
    public void updatePhoneNum(ChangePhoneNumDTO changePhoneNumDTO) {
        Customer findCustomer = customerRepository.findOne(changePhoneNumDTO.getId());
        findCustomer.getPrivacy().setPhoneNum(changePhoneNumDTO.getPhoneNum());
    }

    @Override
    public boolean updateLastDate(Long id) {
        Customer findCustomer = customerRepository.findOne(id);
        return findCustomer.getLastDate().isBefore(LocalDate.now());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean login(CustomerLoginDTO customerLoginDTO) {

        List<Customer> findCustomers = customerRepository.findByEmail(customerLoginDTO.getCustomerEmail());

        if (findCustomers.isEmpty()) {
            return false;
        } else {
            return findCustomers.get(0).getPrivacy().getPassword().equals(customerLoginDTO.getCustomerPassword());
        }
    }

    @Override
    public Customer findCustomer(Long id) {
        customerRepository.findOne(id);
        return customerRepository.findOne(id);
    }

    @Override
    public CustomerInfoDTO findCustomerByEmail(String email) {

        List<Customer> findCustomers = customerRepository.findByEmail(email);
        Customer findCustomer = findCustomers.get(0);
        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
        customerInfoDTO.setCustomerId(findCustomer.getId());
        customerInfoDTO.setCustomerName(findCustomer.getPrivacy().getName());
        customerInfoDTO.setCustomerEmail(findCustomer.getPrivacy().getEmail());
        customerInfoDTO.setLogIn(true);
        customerInfoDTO.setUserType("Customer");
        return customerInfoDTO;
    }

    @Override
    public Customer findByPhoneNum(String phoneNum) {
        return customerRepository.findByPhoneNum(phoneNum).get(0);

    }

    @Override
    public boolean isPhoneNumExist(String phoneNum) {
        List<Customer> findCustomers = customerRepository.findByPhoneNum(phoneNum);

        return !findCustomers.isEmpty();

    }

}
