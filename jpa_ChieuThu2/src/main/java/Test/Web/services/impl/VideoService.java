package Test.Web.services.impl;

import java.util.List;

import Test.Web.dao.ICategoryDao;
import Test.Web.dao.IVideoDao;
import Test.Web.dao.impl.CategoryDao;
import Test.Web.dao.impl.VideoDao;
import Test.Web.entity.Category;
import Test.Web.entity.Video;
import Test.Web.services.IcategoryService;
import Test.Web.services.IvideoService;

public class VideoService implements IvideoService {
	IVideoDao vidDao = new VideoDao();

@Override
public int count() {
	return vidDao.count();
}

@Override
public List<Video> findAll(int page, int pagesize) {
	return vidDao.findAll(page, pagesize);
}

@Override
public List<Video> findByVideoTitle(String vidtitle) {

	return vidDao.findByVideoTitle(vidtitle);
}

@Override
public List<Video> findAll() {
	return vidDao.findAll();
}

@Override
public Video findById(String vidid) {
	// TODO Auto-generated method stub
	return vidDao.findById(vidid);
}

@Override
public void delete(String vidid) throws Exception {
	vidDao.delete(vidid);
	
}

@Override
public void update(Video video) {
	vidDao.update(video);
	
}

@Override
public void insert(Video video) {
	vidDao.insert(video);
	
}
	

}
