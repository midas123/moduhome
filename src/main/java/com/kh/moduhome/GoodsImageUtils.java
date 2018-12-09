
package com.kh.moduhome;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("goodsImageUtils")
public class GoodsImageUtils {

	   //private static final String filePath = "C:\\Users\\user\\git\\moduhome\\src\\main\\webapp\\images\\goods\\";
	   private static final String filePath = "C:\\Users\\hyk\\git\\moduhome\\src\\main\\webapp\\images\\goods\\";
	   
	   private static final String filePath2 = "C:\\java\\maven-app\\MODA\\src\\main\\webapp\\file\\noticeFile\\";
	   private static final String filePath3 = "C:\\java\\maven-app\\MODA\\src\\main\\webapp\\file\\faqFile\\";
	   
	   //private static final String filePath4 = "C:\\java\\maven-app\\MODA\\src\\main\\webapp\\file\\reviewFile\\";
	   private static final String filePath4 = "C:\\Users\\yk\\Desktop\\upload\\images\\review\\";
	   
	  // private static final String filePath5 = "C:\\java\\maven-app\\MODA\\src\\main\\webapp\\file\\qnaFile\\";
	   private static final String filePath6 = "C:\\Users\\J\\git\\moduhome\\src\\main\\webapp\\style\\img\\";
	   private static final String filePath7 = "C:\\Users\\J\\Desktop\\ModuHome\\src\\main\\webapp\\images\\mgContent\\";
	   private static final String filePath8 = "C:\\Users\\예영\\git\\moduhome\\src\\main\\webapp\\images\\snsMain\\";
			   
	   
	   // 상품 썸네일 이미지 등록
	   public Map<String, Object> goodsThumbnail(Map<String, Object> map, HttpServletRequest request) throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

	      if (multipartHttpServletRequest.getFile("GOODS_THUMBNAIL") != null) {
	         MultipartFile file = multipartHttpServletRequest.getFile("GOODS_THUMBNAIL");
	         String fileName = "Thumbnail_" + map.get("GOODS_NUMBER").toString();

	         String IMAGEExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	         
	         File file2 = new File(filePath);
	         if (file2.exists() == false) {
	            file2.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
	         }

	         
	         File uploadFile = new File(filePath + fileName + IMAGEExtension);

	         try {
	            file.transferTo(uploadFile);
	         } catch (Exception e) {

	         }
	         map.put("GOODS_THUMBNAIL", fileName + IMAGEExtension);
	      }
	      return map;
	   }

	   // 상품이미지 등록
	   public List<Map<String, Object>> parseInsertFileInfo(Map<String, Object> map, HttpServletRequest request)
	         throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

	      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	      Map<String, Object> listMap = null;

	      if (multipartHttpServletRequest.getFiles("IMAGE") != null) {
	         List<MultipartFile> imageFile = multipartHttpServletRequest.getFiles("IMAGE");

	         String IMAGE = null;
	         String IMAGEExtension = null;

	         String GOODS_NUMBER = map.get("GOODS_NUMBER").toString();

	         File file = new File(filePath);
	         if (file.exists() == false) {
	            file.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
	         }

	         for (MultipartFile multipartFile : imageFile) {

	            if (multipartFile.isEmpty() == false) {
	               IMAGEExtension = multipartFile.getOriginalFilename()
	                     .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
	               IMAGE = "IMAGE_" + GOODS_NUMBER + "_" + System.currentTimeMillis() + IMAGEExtension;

	               file = new File(filePath + IMAGE);
	               multipartFile.transferTo(file);

	               listMap = new HashMap<String, Object>();
	               listMap.put("IMAGE", IMAGE);

	               listMap.put("GOODS_NUMBER", map.get("GOODS_NUMBER"));
	               list.add(listMap);
	            }
	         }
	         return list;
	      } else {
	         return list;
	      }
	   }
	   
	   // 썸네일 이미지 수정
	   public Map<String, Object> parseUpdateThumbImage(Map<String, Object> map, HttpServletRequest request)
	         throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
	      MultipartFile file = multipartHttpServletRequest.getFile("GOODS_THUMBNAIL");

	      String fileName = "Thumbnail_" + map.get("GOODS_NUMBER").toString();

	      String IMAGEExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

	      File uploadFile = new File(filePath + fileName + IMAGEExtension);

	      if (map.get("ORIIGINAL_THUMBNAIL") != null) {
	         String orgFileName = (String) map.get("ORIGINAL_THUMBNAIL");
	         File removeFile = new File(filePath + orgFileName);
	         removeFile.delete();
	      }

	      try {
	         file.transferTo(uploadFile);
	      } catch (Exception e) {
	      }

	      map.put("GOODS_THUMBNAIL", fileName + IMAGEExtension);

	      System.out.println("썸네일이미지 수정완료");
	      return map;
	   }

	   // 이미지 수정
	   public List<Map<String, Object>> parseUpdateImages(Map<String, Object> map, HttpServletRequest request)
	         throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
	      String IMAGEExtension = null;

	      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	      Map<String, Object> listMap = null;
	      MultipartFile multipartFile = null;

	      File file = new File(filePath);
	      if (file.exists() == false) {
	         file.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
	      }

	      String[] orgImage = request.getParameterValues("ORIGINAL_IMAGE");
	      // System.out.println("기존 이미지 배열 : " + orgImage);
	      // 기존파일 수정 및 삭제
	      for (String a : orgImage) { // 전송된 기존파일 정보가 있으면
	         if (request.getParameter(a) != null) { //
	            // 기존파일이 수정되었으면 밑의 if문으로들어가고, 아니면 그냥 지나감
	            if (multipartHttpServletRequest.getFile("MODIFY_IMAGE_" + a).getSize() > 0) {
	               multipartFile = multipartHttpServletRequest.getFile("MODIFY_IMAGE_" + a);

	               File removeFile = new File(filePath + a);
	               removeFile.delete();

	               IMAGEExtension = multipartFile.getOriginalFilename()
	                     .substring(multipartFile.getOriginalFilename().lastIndexOf("."));

	               file = new File(filePath + a.substring(0, a.lastIndexOf(".")) + IMAGEExtension);
	               multipartFile.transferTo(file);

	               listMap = new HashMap<String, Object>();
	               listMap.put("IMAGE", a.substring(0, a.lastIndexOf(".")) + IMAGEExtension);
	               listMap.put("ORIGINAL_IMAGE", a);

	               listMap.put("GOODS_NUMBER", map.get("GOODS_NUMBER"));
	               list.add(listMap);
	            }
	         }
	      }
	      System.out.println(list.size());
	      return list;
	   }
	   
	   // 썸네일이미지 삭제
	   public void parseDeleteThumbnail(Map<String, Object> map) throws Exception {

	      if (map.get("GOODS_THUMBNAIL") != null) {
	         File removeFile = new File(filePath + map.get("GOODS_THUMBNAIL"));
	         removeFile.delete();
	      }
	   }

	   // 이미지 삭제
	   public void parseDeleteImages(Map<String, Object> map) throws Exception {

	      if (map.get("IMAGE") != null) {
	         File removeFile = new File(filePath + map.get("IMAGE"));
	         removeFile.delete();
	      }
	   }

	   //매거진 타이틀 이미지 삭제
	   public void mgTitleImageDelete(Map<String, Object> map) throws Exception {

	      if (map.get("MG_TITLE_IMAGE") != null) {
	         File removeFile = new File(filePath6 + map.get("MG_TITLE_IMAGE"));
	         removeFile.delete();
	      }
	   }
	   
	   //매거진 타이틀 이미지 삭제
	   public void snsImageDelete(Map<String, Object> map) throws Exception {

	      if (map.get("SNS_IMAGE") != null) {
	         File removeFile = new File(filePath8 + map.get("SNS_IMAGE"));
	         removeFile.delete();
	      }
	   }

	   // faq 이미지 삭제
	   public void faqImageDelete(Map<String, Object> map) throws Exception {

	      if (map.get("FAQ_IMAGE1") != null) {
	         File removeFile = new File(filePath3 + map.get("FAQ_IMAGE1"));
	         removeFile.delete();
	      }
	   }
	   
	   //리뷰 이미지 삭제
	   public void reviewImageDelete(Map<String, Object> map) throws Exception {
	      if(map.get("REVIEW_IMAGE") != null) {
	         File removeFile = new File(filePath4 + map.get("REVIEW_IMAGE"));
	         removeFile.delete();
	      }
	   }
	   
	   //QNA 이미지 삭제
	 /*  public void qnaImageDelete(Map<String, Object> map) throws Exception {
	      if(map.get("QNA_IMAGE1") != null) {
	         File removeFile = new File(filePath5 + map.get("QNA_IMAGE1"));
	         removeFile.delete();
	      }
	   }*/

	   // 공지사항 이미지 등록
	   public List<Map<String, Object>> notifyImage(Map<String, Object> map, HttpServletRequest request)
	         throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

	      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	      Map<String, Object> listMap = null;

	      if (multipartHttpServletRequest.getFiles("NOTIFY_IMAGE") != null) {
	         List<MultipartFile> imageFile = multipartHttpServletRequest.getFiles("NOTIFY_IMAGE");

	         String IMAGE = null;
	         String IMAGEExtension = null;

	         String NOTIFY_NUMBER = map.get("NOTIFY_NUMBER").toString();

	         File file = new File(filePath2);
	         if (file.exists() == false) {
	            file.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
	         }

	         for (MultipartFile multipartFile : imageFile) {

	            if (multipartFile.isEmpty() == false) {
	               IMAGEExtension = multipartFile.getOriginalFilename()
	                     .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
	               IMAGE = "NOTIFY_" + NOTIFY_NUMBER + IMAGEExtension;

	               file = new File(filePath2 + IMAGE);
	               multipartFile.transferTo(file);

	               listMap = new HashMap<String, Object>();
	               listMap.put("NOTIFY_IMAGE", IMAGE);

	               listMap.put("NOTIFY_NUMBER", map.get("NOTIFY_NUMBER"));
	               list.add(listMap);
	            }
	         }
	         return list;
	      } else {
	         return list;
	      }
	   }
	   
	   //faq 이미지 등록
	      public List<Map<String, Object>> faqImage(Map<String, Object> map, HttpServletRequest request)
	            throws Exception {

	         MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

	         List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	         Map<String, Object> listMap = null;

	         if (multipartHttpServletRequest.getFiles("FAQ_IMAGE1") != null) {
	            List<MultipartFile> imageFile = multipartHttpServletRequest.getFiles("FAQ_IMAGE1");

	            String IMAGE = null;
	            String IMAGEExtension = null;

	            String FAQ_NUMBER = map.get("FAQ_NUMBER").toString();

	            File file = new File(filePath3);
	            if (file.exists() == false) {
	               file.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
	            }

	            for (MultipartFile multipartFile : imageFile) {

	               if (multipartFile.isEmpty() == false) {
	                  IMAGEExtension = multipartFile.getOriginalFilename()
	                        .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
	                  IMAGE = "FAQ_IMAGE_" + FAQ_NUMBER + IMAGEExtension;

	                  file = new File(filePath3 + IMAGE);
	                  multipartFile.transferTo(file);

	                  listMap = new HashMap<String, Object>();
	                  listMap.put("FAQ_IMAGE1", IMAGE);

	                  listMap.put("FAQ_NUMBER", map.get("FAQ_NUMBER"));
	                  list.add(listMap);
	               }
	            }
	            return list;
	         } else {
	            return list;
	         }
	      }

	   // faq 이미지 수정
	   public Map<String, Object> faqModifyImage(Map<String, Object> map, HttpServletRequest request) throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
	      MultipartFile file = multipartHttpServletRequest.getFile("FAQ_IMAGE1");

	      String fileName = "FAQ_IMAGE_" + map.get("FAQ_NUMBER").toString();
	      String IMAGEExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

	      File uploadFile = new File(filePath3 + fileName + IMAGEExtension);

	      if (map.get("ORIGINAL_FAQ") != null) {
	         String orgFileName = (String) map.get("ORIGINAL_FAQ");
	         File removeFile = new File(filePath3 + orgFileName);
	         removeFile.delete();
	      }

	      try {
	         file.transferTo(uploadFile);
	      } catch (Exception e) {

	      }

	      map.put("FAQ_IMAGE1", fileName + IMAGEExtension);

	      System.out.println("FAQ 이미지 수정완료");
	      return map;
	   }

	   // 공지사항 이미지 수정
	   public Map<String, Object> notifyModifyImage(Map<String, Object> map, HttpServletRequest request) throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
	      MultipartFile file = multipartHttpServletRequest.getFile("NOTIFY_IMAGE");

	      String fileName = "NOTIFY_" + map.get("NOTIFY_NUMBER").toString();
	      String IMAGEExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

	      File uploadFile = new File(filePath2 + fileName + IMAGEExtension);

	      if (map.get("ORIIGINAL_NOTIFY") != null) {
	         String orgFileName = (String) map.get("ORIGINAL_NOTIFY");
	         File removeFile = new File(filePath2 + orgFileName);
	         removeFile.delete();
	      }

	      try {
	         file.transferTo(uploadFile);
	      } catch (Exception e) {

	      }

	      map.put("NOTIFY_IMAGE", fileName + IMAGEExtension);

	      System.out.println("공지사항 이미지 수정완료");
	      return map;
	   }
	   
	   // magazine 메인 이미지 등록
	   public Map<String, Object> mgMainImage(Map<String, Object> map, HttpServletRequest request) throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

	      if (multipartHttpServletRequest.getFile("MG_TITLE_IMAGE") != null) {
	         MultipartFile file = multipartHttpServletRequest.getFile("MG_TITLE_IMAGE");
	         String fileName = "mg_" + map.get("MG_NUMBER").toString();

	         String IMAGEExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	         
	         File file2 = new File(filePath6);
	         if (file2.exists() == false) {
	            file2.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
	         }

	         
	         File uploadFile = new File(filePath6 + fileName + IMAGEExtension);

	         try {
	            file.transferTo(uploadFile);
	         } catch (Exception e) {

	         }
	         map.put("MG_TITLE_IMAGE", fileName + IMAGEExtension);
	      }
	      return map;
	   }
	   
	   // magazine 내용 이미지 등록
	   public Map<String, Object> mgContentImage(Map<String, Object> map, HttpServletRequest request) throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

	      if (multipartHttpServletRequest.getFile("MG_IMAGE") != null) {
	         MultipartFile file = multipartHttpServletRequest.getFile("MG_IMAGE");
	         
	       
	         String fileName = "mgcon_" + map.get("MG_NUMBER").toString();

	         String IMAGEExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	         
	         String imageName = fileName +"_" + System.currentTimeMillis() + IMAGEExtension;
	         
	         File file2 = new File(filePath7);
	         if (file2.exists() == false) {
	            file2.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
	         }

	         
	         File uploadFile = new File(filePath7 + imageName);

	         try {
	            file.transferTo(uploadFile);
	         } catch (Exception e) {

	         }
	         map.put("MG_IMAGE", imageName);
	      }
	      return map;
	   }
	   

	   // snsboard 이미지 등록
	   public Map<String, Object> snsMainImage(Map<String, Object> map, HttpServletRequest request) throws Exception {

	      MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

	      if (!multipartHttpServletRequest.getFile("SNS_IMAGE").isEmpty()) {
	         MultipartFile file = multipartHttpServletRequest.getFile("SNS_IMAGE");
	         String fileName = "sns_" + map.get("SNS_NUMBER").toString();

	         String IMAGEExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	         
	         File file2 = new File(filePath8);
	         if (file2.exists() == false) {
	            file2.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
	         }

	         
	         File uploadFile = new File(filePath8 + fileName + IMAGEExtension);

	         try {
	            file.transferTo(uploadFile);
	         } catch (Exception e) {

	         }
	         map.put("SNS_IMAGE", fileName + IMAGEExtension);
	      }
	      return map;
	   }
	   
	  
}