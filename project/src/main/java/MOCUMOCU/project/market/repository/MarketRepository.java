package MOCUMOCU.project.market.repository;

import MOCUMOCU.project.market.entity.Market;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MarketRepository {

    private final EntityManager em;

    public void save(Market market) {
        em.persist(market);
    }

    public void remove(Market market) {
        em.remove(market);
    }

    public Market findOne(Long id) {
        return em.find(Market.class, id);
    }

    public List<Market> findAllMarket(){
        return em.createQuery("select m from Market m", Market.class)
                .getResultList();
    }

    public List<Market> findByOwnerId(Long ownerId) {

        return em.createQuery("select m from Market m where m.owner.id =: ownerId",Market.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
    }
}
