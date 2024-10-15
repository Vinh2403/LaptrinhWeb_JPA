package Test.Web.dao.impl;

import java.util.List;

import Test.Web.configs.JPAConfig;
import Test.Web.dao.IVideoDao;
import Test.Web.entity.Category;
import Test.Web.entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class VideoDao implements IVideoDao {

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(v) FROM Video v";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public List<Video> findByVideoTitle(String vidtitle) {		//find by Name
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT v FROM Video v WHERE v.title like :vidtitle";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("vidtitle", "%" + vidtitle + "%");

		return query.getResultList();
	}

	@Override
	public List<Video> findAll() {

		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(String vidid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Video video = enma.find(Video.class, vidid);
		return video;
	}

	@Override
	public void delete(String vidid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// goi pthuc update, insert, delete:
			Video video = enma.find(Video.class, vidid);
			if (video != null) {
				enma.remove(video);
			} // cho chuc nang insert.
			else {
				throw new Exception("Khong tim thay!");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// goi pthuc update, insert, delete:

			enma.merge(video); // cho chuc nang insert.

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// goi pthuc update, insert, delete:

			enma.persist(video); // cho chuc nang insert.

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

}
