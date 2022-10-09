package MOCUMOCU.project.customer;

import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.domain.Privacy;
import MOCUMOCU.project.form.CouponInfoDTO;
import MOCUMOCU.project.form.CustomerInfoDTO;
import MOCUMOCU.project.form.CustomerLoginDTO;
import MOCUMOCU.project.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

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
    public void updatePrivacy(Long id, Privacy privacy) {
        Customer findCustomer = customerRepository.findOne(id);
        findCustomer.setPrivacy(privacy);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponInfoDTO> findAllCoupon(Long id) {
        List<Coupon> myCoupons = couponRepository.findByCustomerId(id);
        List<CouponInfoDTO> couponInfoDTOList = new ArrayList<>();
        System.out.println("2");
        for (Coupon myCoupon : myCoupons) {
            CouponInfoDTO couponInfoDTO = new CouponInfoDTO();

            couponInfoDTO.setCouponId(myCoupon.getId());
            couponInfoDTO.setMarketName(myCoupon.getMarket().getMarketName());
            couponInfoDTO.setStampAmount(myCoupon.getAmount());

            couponInfoDTOList.add(couponInfoDTO);
        }
        System.out.println("#");
        return  couponInfoDTOList;
    }

    @Override
    public void updateLastDate() {

    }

    @Override
    @Transactional(readOnly = true)
    public boolean login(CustomerLoginDTO customerLoginDTO) {

        List<Customer> findCustomer = customerRepository.findByEmail(customerLoginDTO.getCustomerEmail());

        if (findCustomer.isEmpty()) {
            return false;
        } else {
            return findCustomer.get(0).getPrivacy().getPassword().equals(customerLoginDTO.getCustomerPassword());
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
        List<Customer> findCustomer = customerRepository.findByPhoneNum(phoneNum);

        return !findCustomer.isEmpty();

    }

}
