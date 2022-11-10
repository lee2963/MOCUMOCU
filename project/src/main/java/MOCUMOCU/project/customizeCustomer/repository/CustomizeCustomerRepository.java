package MOCUMOCU.project.customizeCustomer.repository;

import MOCUMOCU.project.customize.entity.Type;
import MOCUMOCU.project.customizeCustomer.entity.CustomizeCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomizeCustomerRepository {

    private final EntityManager em;

    public void save(CustomizeCustomer customizeCustomer) {
        em.persist(customizeCustomer);
    }

    public void remove(CustomizeCustomer customizeCustomer) {
        em.remove(customizeCustomer);
    }

    public CustomizeCustomer findOne(Long id){
        return em.find(CustomizeCustomer.class, id);
    }

    public List<CustomizeCustomer> findByCustomerId(Long customerId) {

        return em.createQuery("select c from CustomizeCustomer c where c.customer.id = :customerId", CustomizeCustomer.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public List<CustomizeCustomer> findByType(Long customerId, Type type) {
        return em.createQuery("select c from CustomizeCustomer c where c.customer.id = :customerId and c.customize.type = :type", CustomizeCustomer.class)
                .setParameter("customerId", customerId)
                .setParameter("type", type)
                .getResultList();
    }
}
