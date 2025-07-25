package model.repository;

import model.entity.StudentAttendance;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

public class StudentAttendanceRepository {

    @PersistenceContext(unitName = "dbinfo")
    private EntityManager em;

    public List<StudentAttendance> findAll() {
        return em.createQuery("SELECT s FROM StudentAttendance s", StudentAttendance.class)
                 .getResultList();
    }
    
    
    public List<StudentAttendance> findByTrainDate(LocalDate trainDate) {
        return em.createQuery(
                 "SELECT s FROM StudentAttendance s WHERE s.trainDate = :trainDate", StudentAttendance.class)
                 .setParameter("trainDate", trainDate)
                 .getResultList();
    }
}
