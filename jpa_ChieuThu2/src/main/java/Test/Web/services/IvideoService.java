package Test.Web.services;

import java.util.List;

import Test.Web.entity.Category;
import Test.Web.entity.Video;

public interface IvideoService {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoTitle(String vidtitle);

	List<Video> findAll();

	Video findById(String vidid);

	void delete(String vidid) throws Exception;

	void update(Video video);

	void insert(Video video);
}
