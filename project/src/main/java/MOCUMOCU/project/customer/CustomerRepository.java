package MOCUMOCU.project.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final EntityManager em;

    public void save(Customer customer) {
        em.persist(customer);
    }

    public void remove(Customer customer) {
        em.remove(customer);
    }

    public Customer findOne(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findByEmail(String email){
        return em.createQuery("select c from Customer c where c.privacy.email = :email", Customer.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<Customer> findByPhoneNum(String phoneNum) {
        return em.createQuery("select c from Customer c where c.privacy.phoneNum = :phoneNum", Customer.class)
                .setParameter("phoneNum", phoneNum)
                .getResultList();
    }


}
