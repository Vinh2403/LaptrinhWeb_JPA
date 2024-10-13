package Test.Web.services.impl;

import java.util.List;

import Test.Web.dao.ICategoryDao;
import Test.Web.dao.impl.CategoryDao;
import Test.Web.entity.Category;
import Test.Web.services.IcategoryService;

public class CategoryService implements IcategoryService {
ICategoryDao cateDao = new CategoryDao();
	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		
		return cateDao.findByCategoryname(catname);
	}

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Category findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDao.delete(cateid);
		
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);
		
	}

	@Override
	public void insert(Category category) {
		cateDao.insert(category);
		
	}

}
