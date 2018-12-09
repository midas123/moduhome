package com.kh.review;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kh.moduhome.GoodsImageUtils;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

	private static final String filePath = "C:\\Users\\yk\\Desktop\\upload\\images\\review\\";
	
	@Resource(name = "reviewDAO")
	private ReviewDAO reviewDAO;
	
	@Resource(name = "goodsImageUtils")
	private GoodsImageUtils goodsImageUtils;


	@Override
	public void reviewWrite(Map<String, Object> map, HttpServletRequest request) throws Exception {
		reviewDAO.reviewWrite(map);

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartHttpServletRequest.getFile("REVIEW_IMAGE");

		Map<String, Object> listMap = new HashMap<String, Object>();

		String IMAGE = null;
		String IMAGEExtension = null;

		File file = new File(filePath);
		if (file.exists() == false) {
			file.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
		}

		if (multipartFile.getSize() > 0) {

			String REVIEW_NUMBER = map.get("REVIEW_NUMBER").toString();
			IMAGEExtension = multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			IMAGE = "REVIEW_" + REVIEW_NUMBER + IMAGEExtension;

			file = new File(filePath + IMAGE);
			multipartFile.transferTo(file);

			listMap.put("REVIEW_IMAGE", IMAGE);
			listMap.put("REVIEW_NUMBER", map.get("REVIEW_NUMBER"));

			reviewDAO.reviewImageWrite(listMap);
		}
	}

	@Override
	public void reviewPoint(Map<String, Object> map) throws Exception {
		reviewDAO.reviewPoint(map);
	}
	

	// 리뷰 리스트(Admin Page)
	@Override
	public List<Map<String, Object>> reviewList(Map<String, Object> map) throws Exception {
		return reviewDAO.reviewList(map);
	}

	// 리뷰 삭제(Admin Page)
	@Override
	public void reviewDelete(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		System.out.println("reviewDeleteMap:"+map);
		listMap = reviewDAO.reviewList(map);
		System.out.println("reviewDelete:"+map);
		goodsImageUtils.reviewImageDelete(listMap.get(0));
		
		reviewDAO.reviewDelete(map);
	}

	// 리뷰 검색
	@Override
	public List<Map<String, Object>> searchReviewList0(Map<String, Object> map) throws Exception {
		return reviewDAO.searchReviewList0(map);
	}

	@Override
	public List<Map<String, Object>> searchReviewList1(Map<String, Object> map) throws Exception {
		return reviewDAO.searchReviewList1(map);
	}

	@Override
	public List<Map<String, Object>> searchReviewList2(Map<String, Object> map) throws Exception {
		return reviewDAO.searchReviewList2(map);
	}



}
