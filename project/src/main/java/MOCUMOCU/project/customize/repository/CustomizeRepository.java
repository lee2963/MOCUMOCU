package MOCUMOCU.project.customize.repository;

import MOCUMOCU.project.customize.entity.Customize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomizeRepository {

    private final EntityManager em;

    public void save(Customize customize) {
        em.persist(customize);
    }

    public void remove(Customize customize) {
         em.remove(customize);
    }

    public Customize findOne(Long id) {
        return em.find(Customize.class, id);
    }

    public List<Customize> findByType(String type) {
        return em.createQuery("select c from Customize c where c.type = :type", Customize.class)
                .setParameter("type", type)
                .getResultList();
    }
}
