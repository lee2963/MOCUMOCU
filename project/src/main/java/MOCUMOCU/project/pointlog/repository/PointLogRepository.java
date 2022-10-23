package MOCUMOCU.project.pointlog.repository;

import MOCUMOCU.project.pointlog.entity.PointLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PointLogRepository {

    private final EntityManager em;

    public void save(PointLog pointLog) {
        em.persist(pointLog);
    }

    public void findOne(Long id){
        em.find(PointLog.class, id);
    }

    public List<PointLog> findByCustomerId(Long customerId) {
        return em.createQuery("select p from PointLog p where p.customer.id = : customerId", PointLog.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
}
