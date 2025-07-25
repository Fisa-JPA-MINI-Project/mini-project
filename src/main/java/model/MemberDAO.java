package model;

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
	public List<StudentAttendance> getMyAttendance() throws NullPointerException{
		EntityManager em = DBUtil.getEntityManager();
	    List<StudentAttendance> list = null;
        list = em.createQuery(
                "SELECT s FROM StudentAttendance s WHERE s.name = :name",
                StudentAttendance.class)
            .setParameter("name", "홍혜원")
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

}
