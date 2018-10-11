package com.kh.join;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import com.kh.util.FileUtils;


@Service("joinService")
public class JoinServiceImpl implements JoinService {
              
	  @Resource(name="joinDAO")
	  private JoinDAO joinDAO;
	  
	  @Resource(name="fileUtils")
	    private FileUtils fileUtils;

	  
	  @Override
		public void insertMember(Map<String, Object> map, HttpServletRequest request) throws Exception {
			
		  
		  joinDAO.insertMember(map); 
		  
		  List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
	        for(int i=0, size=list.size(); i<size; i++){
	            joinDAO.updateFile(list.get(i));
		}
	  }
	  
	  @Override
		public int checkMember(Map<String, Object> map) throws Exception {
			return joinDAO.checkMember(map);
			
		}
	  
	  
	  @Override
	  public int checkId(String mem_id)throws Exception{
		  return joinDAO.checkId(mem_id);
	  }
}
