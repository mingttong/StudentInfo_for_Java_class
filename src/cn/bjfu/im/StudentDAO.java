package cn.bjfu.im;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends BaseDAO {

	/*
	 * ��student�����ѧ����Ϣ
	 */
	public boolean add(StudentVO vo) {
		boolean f = false;
		int no = vo.getNo(), age = vo.getAge();
		String name = vo.getName();

		Connection conn = getConn();
		String sql = "insert into student (no, name, age) values (?, ?, ?)";

		try {
			PreparedStatement ps = conn
					.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, name);
			ps.setInt(3, age);

			int count = ps.executeUpdate(); // ִ��sql

			if (count > 0) {
				System.out.println("insert ok!");
			} else {
				System.out.println("insert failed...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return f;
	}

	/*
	 * ����ѧ�Ų���
	 */
	public StudentVO findByNo(int no) {
		StudentVO vo = null;

		Connection conn = getConn();
		String sql = "select no, name, age from student where no=?";

		try {
			PreparedStatement ps = conn
					.prepareStatement(sql);
			ps.setInt(1, no);

			ResultSet rs = ps.executeQuery();

			/*
			 * ֻ��Ψһ��һ��������������ҵ����Ժ���rs.next()=true�� ����ֻ��ȡ��ȡһ��ѧ����Ϣ
			 */
			if (rs.next()) {
				int noInDb = rs.getInt(1);
				String name = rs.getString("name");
				int age = rs.getInt("age");
				vo = new StudentVO(noInDb, name, age);

				System.out.println("find!");
				System.out.println("no:" + noInDb + ", " + "name:" + name
						+ ", " + "age:" + age);

			} else {
				System.out.println("not find...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return vo;
	}

	public List<StudentVO> findByAge(int age) {
		List<StudentVO> arr = new ArrayList<StudentVO>();

		int count = 0;

		Connection conn = getConn();
		String sql = "select no, name, age from student where age=?";

		/*
		 * ���ڻ�����ҵ���ѧ������Ϣ��
		 */
		int noInDb = 0;
		String nameInDb = "";
		int ageInDb = 0;
		StudentVO vo = null;

		try {

			PreparedStatement ps = conn
					.prepareStatement(sql);
			ps.setInt(1, age);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				noInDb = rs.getInt(1);
				nameInDb = rs.getString("name");
				ageInDb = rs.getInt("age");
				vo = new StudentVO(noInDb, nameInDb, ageInDb);

				arr.add(vo);
				count++;

				System.out.println("no:" + noInDb + ", " + "name:" + nameInDb
						+ ", " + "age:" + ageInDb);
			}

			/*
			 * ������Ŀ
			 */
			if (count == 0) {
				System.out.println("not find...");
				arr = null;
			} else {
				System.out.println("һ���ҵ���" + count + "����¼");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return arr;
	}

	public List<StudentVO> findByName(String nameLike) {
		List<StudentVO> arr = new ArrayList<StudentVO>();

		int count = 0;

		Connection conn = getConn();
		String sql = "select no, name, age from student where name like ?";

		/*
		 * ���ڻ�����ҵ���ѧ������Ϣ��
		 */
		int noInDb = 0;
		String nameInDb = "";
		int ageInDb = 0;
		StudentVO vo = null;

		try {

			PreparedStatement ps = conn
					.prepareStatement(sql);
			ps.setString(1, nameLike + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				noInDb = rs.getInt(1);
				nameInDb = rs.getString("name");
				ageInDb = rs.getInt("age");
				vo = new StudentVO(noInDb, nameInDb, ageInDb);

				arr.add(vo);
				count++;

				System.out.println("no:" + noInDb + ", " + "name:" + nameInDb
						+ ", " + "age:" + ageInDb);
			}

			/*
			 * ������Ŀ
			 */
			if (count == 0) {
				System.out.println("not find...");
				arr = null;
			} else {
				System.out.println("һ���ҵ���" + count + "����¼");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return arr;
	}

	public static void main(String[] args) {

		StudentDAO dao = new StudentDAO();

		// ���ѧ����Ϣ
		// boolean success = dao.add(new StudentVO(1, "zwn", 20));
		// success = dao.add(new StudentVO(3, "zy", 16));
		// success = dao.add(new StudentVO(5, "bmg", 20));

		// ����ѧ�Ų���
		// StudentVO voInDb = dao.findByNo(1);

		// �����������
		// List<StudentVO> arr = dao.findByAge(20);

		// ��������likeƥ��
		// List<StudentVO> arr = dao.findByName("z");

	}

}
