package MOCUMOCU.project.customer;

import MOCUMOCU.project.customer.form.ChangePhoneNumDTO;
import MOCUMOCU.project.customer.form.CustomerInfoDTO;
import MOCUMOCU.project.customer.form.CustomerLoginDTO;

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
