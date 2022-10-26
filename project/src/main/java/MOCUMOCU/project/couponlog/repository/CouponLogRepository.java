package MOCUMOCU.project.couponlog.repository;

import MOCUMOCU.project.couponlog.dto.CouponLogDTO;
import MOCUMOCU.project.couponlog.entity.CouponLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponLogRepository {

    private final EntityManager em;

    public void save(CouponLog couponLog) {
        em.persist(couponLog);
    }

    public List<CouponLog> findByCustomerId(Long customerId) {
        return em.createQuery("select c from CouponLog c where c.customer.id = :customerId", CouponLog.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public List<CouponLog> findByMarketId(Long marketId) {
        return em.createQuery("select c from CouponLog c where c.market.id = :marketId", CouponLog.class)
                .setParameter("marketId", marketId)
                .getResultList();
    }

    public List<CouponLog> findByDay(Long marketId, int year, int month, int day) {
        return em.createQuery("select c from CouponLog c where c.market.id = :marketId and c.year = :year and c.month = :month and c.day = :day", CouponLog.class)
                .setParameter("marketId", marketId)
                .setParameter("year", year)
                .setParameter("month", month)
                .setParameter("month", day)
                .getResultList();
    }

    public Long countByDay(Long marketId, int year, int month, int day) {
        return em.createQuery("select count(c) from CouponLog c where c.market.id = :marketId and c.year = :year and c.month = :month and c.day = :day", Long.class)
                .setParameter("marketId", marketId)
                .setParameter("year", year)
                .setParameter("month", month)
                .setParameter("month", day)
                .getSingleResult();
    }

    public List<CouponLog> findByYear(Long marketId, int year) {
        return em.createQuery("select c from CouponLog c where c.market.id = :marketId and c.year = :year", CouponLog.class)
                .setParameter("marketId", marketId)
                .setParameter("year", year)
                .getResultList();
    }
}
