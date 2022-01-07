package com.example.demo_security.repository;

import com.example.demo_security.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CreateNoteRepository {
    @Autowired
    private EntityManager entityManager;

    public CreateNoteRepository() {

    }

    public Note findByUser(String username) {
        return this.entityManager.find(Note.class, username);
    }

    public List<Note> noteUserNull(String username, Pageable pageable) {
        StringBuilder queryStr = new StringBuilder("select n from Note n WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();
        if (username != null) {
            queryStr.append(" AND n.user.username LIKE :username");
            params.put("username",  "%"+ username +"%");
        }
        TypedQuery<Note> query = entityManager.createQuery(queryStr.toString(), Note.class);
        params.forEach(query::setParameter);
        return query.setMaxResults(pageable.getPageSize())
                .setFirstResult((int) pageable.getOffset())
                .getResultList();
    }

    public Long noteUserTotals(String username,Pageable pageable) {
        StringBuilder queryStr = new StringBuilder("select count(n) from Note n ");
        Map<String, Object> params = new HashMap<>();
        TypedQuery<Long> query = entityManager.createQuery(queryStr.toString(), Long.class);
          params.forEach(query::setParameter);
        Integer totalRows = query.getResultList().size();
        Long total = totalRows.longValue();
        return total;
    }
}