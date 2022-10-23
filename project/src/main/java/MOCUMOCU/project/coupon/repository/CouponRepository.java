package MOCUMOCU.project.coupon.repository;

import MOCUMOCU.project.coupon.entity.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponRepository {

    private final EntityManager em;

    public Long save(Coupon coupon) {
        em.persist(coupon);

        return coupon.getId();
    }

    public Coupon findOne(Long id) {
       return em.find(Coupon.class, id);

    }


    public List<Coupon> findByCustomerId(Long customerId) {
        return em.createQuery("select c from Coupon c where c.customer.id = : customerId", Coupon.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public Coupon findByCustomerIdAndMarketId(Long customerId, Long marketId) {
        List<Coupon> coupons = em.createQuery("select c from Coupon c where c.customer.id = :customerId and c.market.id = :marketId", Coupon.class)
                .setParameter("customerId", customerId)
                .setParameter("marketId", marketId)
                .getResultList();

        if (coupons.isEmpty()) {
            return null;
        } else{
            return coupons.get(0);
        }


    }


    public void remove(Coupon coupon) {
        em.remove(coupon);
    }
}
