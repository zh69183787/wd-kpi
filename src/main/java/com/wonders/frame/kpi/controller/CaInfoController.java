/** 
* @Title: CaInfoController.java 
* @Package com.wonders.frame.core.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author lushuaifeng
* @version V1.0 
*/
package com.wonders.frame.kpi.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.frame.core.model.vo.EntityProperty;
import com.wonders.frame.core.utils.ReflectUtil;
import com.wonders.frame.kpi.model.vo.CurrentLogin;
import com.wonders.frame.kpi.service.CaRestAPIService;

/** 
 * @ClassName: CaInfoController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 */
@Controller
@RequestMapping("/ca")
public class CaInfoController {	
	@RequestMapping(value="/getCurrentLoginInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody 
	public CurrentLogin getCurrentLoginInfo(HttpServletRequest request,HttpServletResponse response) {	

		CurrentLogin currentLogin=CaRestAPIService.getCurrentLoginInfoFromCa(request, response);
			 
		return currentLogin;
	}
}
