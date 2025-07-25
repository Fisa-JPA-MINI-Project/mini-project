package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.entity.StudentAttendance;
import util.DBUtil;

public class MemberDAO {


	
	private static MemberDAO model = new MemberDAO();
	
	private MemberDAO() {} 
	
	public static MemberDAO getModel() {
		return model;
	}
	
	// 내 출석 시간 보기
	public List<StudentAttendance> getMyAttendance(String name) throws NullPointerException{
		EntityManager em = DBUtil.getEntityManager();
		List<StudentAttendance> list = null;
	        list = em.createQuery(
	                "SELECT s FROM StudentAttendance s WHERE s.name = :name order by traindate asc",
	                StudentAttendance.class)
	            .setParameter("name", name)
	            .getResultList();
        if (em != null) {
            em.close();
            em=null;
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

	public StudentAttendance getStudent(String name) throws Exception{
		EntityManager em = DBUtil.getEntityManager();
		StudentAttendance sa = null;
        List<StudentAttendance> list = em.createQuery(
                "SELECT s FROM StudentAttendance s WHERE s.name = :name order by checkin desc",
                StudentAttendance.class)
            .setParameter("name", name)
            .setMaxResults(1)
            .getResultList();

        if (!list.isEmpty()) {
            sa = list.get(0);
        }
        em.close();
        return sa;
	}
	public StudentAttendance getEarliestAttendance(LocalDate date) {
	    EntityManager em = DBUtil.getEntityManager();
	    List<StudentAttendance> list = em.createQuery(
	            "SELECT s FROM StudentAttendance s WHERE s.trainDate = :date AND s.checkIn IS NOT NULL ORDER BY s.checkIn ASC",
	            StudentAttendance.class)
	        .setParameter("date", date)
	        .setMaxResults(1)
	        .getResultList();
	    em.close();
	    return list.isEmpty() ? null : list.get(0);
	}
	
	public List<StudentAttendance> getAttendanceByDate(LocalDate date) {
	    EntityManager em = DBUtil.getEntityManager();
	    List<StudentAttendance> list = em.createQuery(
	        "SELECT s FROM StudentAttendance s WHERE s.trainDate = :date", 
	        StudentAttendance.class)
	        .setParameter("date", date)
	        .getResultList();
	    em.close();
	    return list;
	}
	
	public List<LocalDate> getAttendanceDates() {
	    EntityManager em = DBUtil.getEntityManager();
	    List<LocalDate> dateList = em.createQuery(
	        "SELECT DISTINCT s.trainDate FROM StudentAttendance s ORDER BY s.trainDate ASC", LocalDate.class)
	        .getResultList();
	    em.close();
	    return dateList;
	}


	public void getCheckIn(Long memberId, String name) {
	    EntityManager em = DBUtil.getEntityManager();
	    try {
	        em.getTransaction().begin();

	        StudentAttendance sa = StudentAttendance.builder()
        					.name(name)
        					.trainDate(LocalDate.now())
							.checkIn(LocalDateTime.now())
							.empno(memberId)
							.build();
        				

	        em.persist(sa);

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	}
	
	public void getCheckOut(Long userId, String userName) {
	    EntityManager em = DBUtil.getEntityManager();
	    try {
	        em.getTransaction().begin();

	        // 오늘자 해당 유저의 가장 최근 출근 기록 조회
	        StudentAttendance sa = em.createQuery(
	                "SELECT s FROM StudentAttendance s WHERE s.empno = :empno AND s.trainDate = :today ORDER BY s.checkIn DESC",
	                StudentAttendance.class)
	            .setParameter("empno", userId)
	            .setParameter("today", LocalDate.now())
	            .setMaxResults(1)
	            .getSingleResult();

	        // 퇴근 시간만 현재 시각으로 업데이트
	        sa.setCheckOut(LocalDateTime.now());

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	}

	
	
	
	
	


}
