package de.iu.hero2zero.service;

import de.iu.hero2zero.entity.Co2DataRecord;
import de.iu.hero2zero.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class Co2DataService {

    @PersistenceContext(unitName = "hero2zeroPU")
    private EntityManager em;

    @Transactional
    public void saveRecord(Co2DataRecord record) {
        if (record.getCreatedBy() != null) {
            User user = record.getCreatedBy();
            
            List<User> existing = em.createQuery("SELECT u FROM User u WHERE u.username = :u", User.class)
                                    .setParameter("u", user.getUsername())
                                    .getResultList();
            if (!existing.isEmpty()) {
                record.setCreatedBy(existing.get(0));
            } else {
                User managedUser = em.merge(user);
                record.setCreatedBy(managedUser);
            }
        }
        em.persist(record);
    }

    @Transactional
    public List<Co2DataRecord> searchByCountry(String query) {
        if (query == null || query.trim().isEmpty()) {
            return List.of();
        }
        return em.createQuery("SELECT c FROM Co2DataRecord c WHERE LOWER(c.countryName) LIKE LOWER(:q) OR LOWER(c.countryCode) LIKE LOWER(:q)", Co2DataRecord.class)
                 .setParameter("q", "%" + query.trim().toLowerCase() + "%")
                 .getResultList();
    }

    @Transactional
    public Co2DataRecord getLatestRecord(String countryQuery) {
        List<Co2DataRecord> results = searchByCountry(countryQuery);
        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.stream()
                      .max((r1, r2) -> Integer.compare(r1.getYear(), r2.getYear()))
                      .orElse(results.get(0));
    }
}
