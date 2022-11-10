package MOCUMOCU.project.customer.service;

import MOCUMOCU.project.coupon.dto.FindEventDTO;
import MOCUMOCU.project.customer.repository.CustomerRepository;
import MOCUMOCU.project.customer.dto.ChangePhoneNumDTO;
import MOCUMOCU.project.customer.dto.CustomerInfoDTO;
import MOCUMOCU.project.customer.dto.CustomerLoginDTO;
import MOCUMOCU.project.customer.entity.Customer;
import MOCUMOCU.project.market.entity.Market;
import MOCUMOCU.project.market.repository.MarketRepository;
import MOCUMOCU.project.pointlog.entity.PointLog;
import MOCUMOCU.project.pointlog.entity.Type;
import MOCUMOCU.project.pointlog.repository.PointLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final MarketRepository marketRepository;

    private final PointLogRepository pointLogRepository;

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
    public void updatePassword(Long id, String password) {
        Customer findCustomer = customerRepository.findOne(id);
        findCustomer.getPrivacy().setPassword(password);
    }

    @Override
    public void updatePhoneNum(Long id, String phoneNum) {
        Customer findCustomer = customerRepository.findOne(id);
        findCustomer.getPrivacy().setPhoneNum(phoneNum);
    }

    @Override
    public boolean authPassword(String password, Long id) {
        Customer findCustomer = customerRepository.findOne(id);

        return findCustomer.getPrivacy().getPassword().equals(password);

    }
    @Override
    public boolean updateAttendance(Long id) {
        Customer findCustomer = customerRepository.findOne(id);
        LocalDate now = LocalDate.now();
        if (!now.equals(findCustomer.getAttendance())) {
            findCustomer.setAttendance(now);
            findCustomer.setUserPoint(findCustomer.getUserPoint() + 10);

            LocalDateTime nowTime = LocalDateTime.now();
            PointLog newPoint = new PointLog();
            newPoint.setCustomer(findCustomer);
            newPoint.setDay(nowTime.getDayOfMonth());
            newPoint.setDayOfWeek(nowTime.getDayOfWeek().name());
            newPoint.setHour(nowTime.getHour());
            newPoint.setMinute(nowTime.getMinute());
            newPoint.setMonth(nowTime.getMonthValue());
            newPoint.setYear(nowTime.getYear());
            newPoint.setType(Type.ATTENDANCE);
            newPoint.setPoint(10);
            pointLogRepository.save(newPoint);
            return true;
        }  else{
            return false;
        }
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

    @Override
    public boolean attendance(Long id) {
        Customer findCustomer = customerRepository.findOne(id);
        LocalDate now = LocalDate.now();

        if(findCustomer.getAttendance().equals(now)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public int sendPoint(Long id) {
        Customer findCustomer = customerRepository.findOne(id);

        return findCustomer.getUserPoint();
    }

    @Override
    public String findCustomerId(String customerName, String phoneNum) {
        List<Customer> findCustomer = customerRepository.findByPhoneNum(phoneNum);

        if (findCustomer.isEmpty()) {
            return null;
        } else{
            return findCustomer.get(0).getPrivacy().getEmail();
        }
    }

    @Override
    public String findCustomerPassword(String customerEmail) {

        List<Customer> findCustomer = customerRepository.findByEmail(customerEmail);

        if (findCustomer.isEmpty()) {
            return null;
        } else{
            return findCustomer.get(0).getPrivacy().getPassword();
        }
    }

    @Override
    public String findEmail(String customerName, String phoneNum) {
        List<Customer> findCustomer = customerRepository.findByPhoneNum(phoneNum);

        if (findCustomer.isEmpty()) {
            return null;
        } else{
            return findCustomer.get(0).getPrivacy().getEmail();
        }
    }

    @Override
    public String findPassword(String email) {
        List<Customer> findCustomer = customerRepository.findByEmail(email);

        if (findCustomer.isEmpty()) {
            return null;
        } else{
            return findCustomer.get(0).getPrivacy().getPassword();
        }
    }

    @Override
    public List<FindEventDTO> findEvent() {
        List<Market> markets = marketRepository.findAllMarket();
        List<FindEventDTO> findEventDTOS = new ArrayList<>();
        for (Market market : markets) {
            if (!market.getEventSmallImage().isEmpty()) {
                FindEventDTO findEventDTO = new FindEventDTO();
                log.info("marketId={}", market.getId());
                findEventDTO.setMarketId(market.getId());
                findEventDTO.setEventSmallUrl(market.getEventSmallImage());

                findEventDTOS.add(findEventDTO);
            }
        }
        return findEventDTOS;
    }

    @Override
    public String findBigImageUrl(Long marketId) {
        Market market = marketRepository.findOne(marketId);

        return market.getEventBigImage();
    }

    //출석체크 이전 돌리기 테스트
    public void returnAttendance(Long id) {
        Customer findCustomer = customerRepository.findOne(id);
        findCustomer.setAttendance(LocalDate.now().minusDays(1));

    }
}
