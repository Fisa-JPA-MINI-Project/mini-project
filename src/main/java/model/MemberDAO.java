package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.entity.StudentAttendance;
import util.DBUtil;

public class MemberDAO {


	private static EntityManager em = DBUtil.getEntityManager();
	
	private static MemberDAO model = new MemberDAO();
	
	private MemberDAO() {} 
	
	public static MemberDAO getModel() {
		return model;
	}
	
	// 내 출석 시간 보기
	public List<StudentAttendance> getMyAttendance() throws NullPointerException{
	    List<StudentAttendance> list = null;
	        list = em.createQuery(
	                "SELECT s FROM StudentAttendance s WHERE s.name = :name",
	                StudentAttendance.class)
	            .setParameter("name", "홍혜원")
	            .getResultList();
        if (em != null) {
            em.close();
        }
	    return list;
	}
	public List<StudentAttendance> getTodayAttendance() throws NullPointerException{
	    EntityManager em = DBUtil.getEntityManager();
	    List<StudentAttendance> list = null;
	    list = em.createQuery(
	            "SELECT s FROM StudentAttendance s WHERE s.trainDate = :today",
	            StudentAttendance.class)
	        .setParameter("today", java.time.LocalDate.now())
	        .getResultList();
	    em.close();
	    return list;
	}

	public StudentAttendance getStudent(Long id) {
		return null;
	}

	public List<StudentAttendance> getAttendanceByDate(LocalDate date) {
	    EntityManager em = DBUtil.getEntityManager();
	    List<StudentAttendance> list = null;
	    list = em.createQuery(
	            "SELECT s FROM StudentAttendance s WHERE s.trainDate = :targetDate",
	            StudentAttendance.class)
	        .setParameter("targetDate", date)
	        .getResultList();
	    em.close();
	    return list;
	}
}
