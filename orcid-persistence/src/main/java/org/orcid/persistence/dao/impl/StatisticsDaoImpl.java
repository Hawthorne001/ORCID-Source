package org.orcid.persistence.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.orcid.persistence.dao.StatisticsDao;
import org.orcid.persistence.jpa.entities.StatisticValuesEntity;
import org.orcid.persistence.jpa.entities.StatisticKeyEntity;
import org.springframework.transaction.annotation.Transactional;

@PersistenceUnit(name = "statisticManagerFactory")
public class StatisticsDaoImpl implements StatisticsDao {

    @PersistenceContext(unitName = "statistics")
    protected EntityManager entityManager;
    
    @Override
    @Transactional("statisticsTransactionManager")
    public StatisticKeyEntity createHistory(){        
        StatisticKeyEntity key = new StatisticKeyEntity();
        key.setGenerationDate(new Date());
        entityManager.persist(key);
        return key;
    }
    
    @Override
    @Transactional("statisticsTransactionManager")
    public StatisticKeyEntity getLatestKey(){
        TypedQuery<StatisticKeyEntity> query = entityManager.createQuery("FROM StatisticKeyEntity ORDER BY generationDate DESC", StatisticKeyEntity.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
    
    @Override
    @Transactional("statisticsTransactionManager")
    public StatisticValuesEntity saveStatistic(StatisticValuesEntity statistic) {
        entityManager.persist(statistic);
        return statistic;
    }

    @Override
    @Transactional
    public List<StatisticValuesEntity> getStatistic(long id) {
        TypedQuery<StatisticValuesEntity> query = entityManager.createQuery("FROM StatisticValuesEntity WHERE key.id = :id", StatisticValuesEntity.class);
        query.setParameter("id", id); 
        List<StatisticValuesEntity> results = query.getResultList(); 
        
        for(StatisticValuesEntity result : results){
            System.out.println("Result: " + result.getStatisticName() + " - " + result.getStatisticValue());
        }
        
        return results;
    }
    
    @Override
    @Transactional
    public StatisticValuesEntity getStatistic(long id, String name) {
        TypedQuery<StatisticValuesEntity> query = entityManager.createQuery("FROM StatisticValuesEntity WHERE key.id = :id AND name = :name", StatisticValuesEntity.class);
        query.setParameter("id", id);
        query.setParameter("name", name);
        List<StatisticValuesEntity> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

}
