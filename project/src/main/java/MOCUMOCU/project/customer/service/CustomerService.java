package MOCUMOCU.project.customer.service;

import MOCUMOCU.project.coupon.dto.FindEventDTO;
import MOCUMOCU.project.customer.dto.ChangePhoneNumDTO;
import MOCUMOCU.project.customer.dto.CustomerInfoDTO;
import MOCUMOCU.project.customer.dto.CustomerLoginDTO;
import MOCUMOCU.project.customer.dto.CustomerRegisterDTO;
import MOCUMOCU.project.customer.entity.Customer;

import java.util.List;

public interface CustomerService {

    Long join(Customer customer);

    void withdrawal(Long id);

    void updatePhoneNum(ChangePhoneNumDTO changePhoneNumDTO);

    void updatePassword(Long id, String password);

    void updatePhoneNum(Long id, String phoneNum);

    boolean authPassword(String password, Long id);

    boolean updateAttendance(Long id);

    boolean login(CustomerLoginDTO customerLoginDTO);

    Customer findCustomer(Long id);

    CustomerInfoDTO findCustomerByEmail(String email);

    Customer findByPhoneNum(String phoneNum);

    boolean isPhoneNumExist(String phoneNum);

    boolean attendance(Long id);

    int sendPoint(Long id);

    String findCustomerId(String customerName, String phoneNum);

    String findCustomerPassword(String customerEmail);

    String findEmail(String customerName, String phoneNum);

    String findPassword(String email);

    List<FindEventDTO> findEvent();

    String findBigImageUrl(Long marketId);
}
