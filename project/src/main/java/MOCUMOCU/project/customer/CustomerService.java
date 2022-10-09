package MOCUMOCU.project.customer;

import MOCUMOCU.project.domain.Privacy;
import MOCUMOCU.project.form.CouponInfoDTO;
import MOCUMOCU.project.form.CustomerInfoDTO;
import MOCUMOCU.project.form.CustomerLoginDTO;

import java.util.List;

public interface CustomerService {

    Long join(Customer customer);

    void withdrawal(Long id);

    void updatePrivacy(Long id, Privacy privacy);

    List<CouponInfoDTO> findAllCoupon(Long id);

    void updateLastDate();

    boolean login(CustomerLoginDTO customerLoginDTO);

    Customer findCustomer(Long id);

    CustomerInfoDTO findCustomerByEmail(String email);

    Customer findByPhoneNum(String phoneNum);

    boolean isPhoneNumExist(String phoneNum);


}
