package MOCUMOCU.project.couponlog;

import MOCUMOCU.project.coupon.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponLogRepository {

    private final EntityManager em;

    public void save(CouponLog couponLog) {
        em.persist(couponLog);
    }

    public List<CouponLog> findByCustomerId(Long customerId) {
        return em.createQuery("select c from CouponLog c where c.customer.id = : customerId", CouponLog.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public List<CouponLog> findByMarketId(Long marketId) {
        return em.createQuery("select c from CouponLog c where c.market.id = : marketId", CouponLog.class)
                .setParameter("marketId", marketId)
                .getResultList();
    }
}
