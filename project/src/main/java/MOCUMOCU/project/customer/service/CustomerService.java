package MOCUMOCU.project.customer.service;

import MOCUMOCU.project.customer.dto.ChangePhoneNumDTO;
import MOCUMOCU.project.customer.dto.CustomerInfoDTO;
import MOCUMOCU.project.customer.dto.CustomerLoginDTO;
import MOCUMOCU.project.customer.entity.Customer;

public interface CustomerService {

    Long join(Customer customer);

    void withdrawal(Long id);

    void updatePhoneNum(ChangePhoneNumDTO changePhoneNumDTO);

    boolean updateLastDate(Long id);

    boolean login(CustomerLoginDTO customerLoginDTO);

    Customer findCustomer(Long id);

    CustomerInfoDTO findCustomerByEmail(String email);

    Customer findByPhoneNum(String phoneNum);

    boolean isPhoneNumExist(String phoneNum);


}
