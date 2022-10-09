package MOCUMOCU.project.coupon;

import MOCUMOCU.project.coupon.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponRepository {

    private final EntityManager em;

    public void save(Coupon coupon) {
        em.persist(coupon);
    }

    public Coupon findOne(Long id) {
        Coupon findCoupon = em.find(Coupon.class, id);

        return findCoupon;
    }


    public List<Coupon> findByCustomerId(Long customerId) {
        return em.createQuery("select c from Coupon c where c.customer.id = : customerId", Coupon.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public List<Coupon> findByCustomerIdAndMarketId(Long customerId, Long marketId) {
        return em.createQuery("select c from Coupon c where c.customer.id = :customerId and c.market.id = :marketId", Coupon.class)
                .setParameter("customerId", customerId).setParameter("marketId", marketId)
                .getResultList();
    }


    public void remove(Coupon coupon) {
        em.remove(coupon);
    }
}
