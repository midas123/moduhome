package com.kh.qna;


import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.kh.moduhome.GoodsImageUtils;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	
	//private static final String filePath = "C:\\Users\\yk\\Desktop\\upload\\qna\\";
	
	@Resource(name = "qnaDAO")
	private QnaDAO qnaDAO;
	
	@Resource(name = "goodsImageUtils")
	private GoodsImageUtils goodsImageUtils;

	@Override
	public void insertModalQna(Map<String, Object> map, HttpServletRequest request) throws Exception {
		qnaDAO.insertModalQna(map);
	/*	MultipartFile multipartFile = null;
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		multipartFile = multipartHttpServletRequest.getFile("QNA_IMAGE");

		Map<String, Object> listMap = new HashMap<String, Object>();

		String IMAGE = null;
		String IMAGEExtension = null;

		File file = new File(filePath);
		if (file.exists() == false) {
			file.mkdirs(); // 폴더가 존재하지 않으면 폴더 생성
		}

		if (multipartFile.getSize() >0) {

			String QNA_NUMBER = map.get("QNA_NUMBER").toString();
			IMAGEExtension = multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			IMAGE = "QNA_" + QNA_NUMBER + IMAGEExtension;

			file = new File(filePath + IMAGE);
			multipartFile.transferTo(file);

			listMap.put("QNA_IMAGE", IMAGE);
			listMap.put("QNA_NUMBER", map.get("QNA_NUMBER"));
			
			qnaDAO.qnaImageWrite(listMap);
			
			System.out.println("QNA_NUMBER = " + QNA_NUMBER);
			System.out.println("QNA_IMAGE = " + IMAGE);
			System.out.println("listMap = " + listMap);
		}
	 */
		
	}
	
	// 문의 삭제(Admin Page)
		@Override
		public void qnaDelete(Map<String, Object> map) throws Exception {
			//List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			//listMap = qnaDAO.qnaList(map);
			//goodsImageUtils.qnaImageDelete(listMap.get(0));
			
			qnaDAO.qnaDelete(map);

		}
}
