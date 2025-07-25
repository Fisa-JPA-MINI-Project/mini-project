package model.repository;

import model.entity.StudentAttendance;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class StudentAttendanceRepository {

    @PersistenceContext(unitName = "dbinfo")
    private EntityManager em;

    public List<StudentAttendance> findAll() {
        return em.createQuery("SELECT s FROM StudentAttendance s", StudentAttendance.class)
                 .getResultList();
    }
}
