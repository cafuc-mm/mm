package com.sfc.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sfc.mapper.DriverInfoMapper;
import com.sfc.mapper.MenuInfoMapper;
import com.sfc.mapper.UserInfoMapper;
import com.sfc.model.DriverInfo;
import com.sfc.model.DriverInfoExample;
import com.sfc.model.MenuInfo;
import com.sfc.model.MenuInfoExample;
import com.sfc.model.UserInfo;
import com.sfc.model.UserInfoExample;

@Controller
@RequestMapping("/driver")
public class DriverController {

	@Resource
	private UserInfoMapper userDao;
	@Resource
	private DriverInfoMapper driverDao;

	// ����ϳ�
	@RequestMapping("/go")
	public String go(HttpServletRequest request) {
		HttpSession session = getSession();
		session.removeAttribute("tips");

		String jsp = "orderCarList";
		try {
			

			// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");
			List<DriverInfo> driverNList = driverDao.selectByExample(driverExample);
			session.setAttribute("driverNList", driverNList);

// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");// ����˳�糵���б�
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andIsyesEqualTo("N");
		} catch (Exception e) {
			// TODO: handle exception
			session.setAttribute("tips", e.getMessage());

		} finally {
			return "redirect:/" + jsp + ".jsp";

		}

	}

	// �����г�
	@RequestMapping(value = "adddriver")
	public String adddriver(HttpServletRequest request) {
		HttpSession session = getSession();
		session.removeAttribute("tips");
		try {
//			String num = new String(request.getParameter("num").getBytes("ISO-8859-1"), "UTF-8");
            String num=request.getParameter("num");
            String startAdd=request.getParameter("startAdd");

            String endAdd=request.getParameter("endAdd");

            String state=request.getParameter("state");

            String price=request.getParameter("price");
            String starttime=request.getParameter("starttime");


//			String startAdd = new String(request.getParameter("startAdd").getBytes("ISO-8859-1"), "UTF-8");
//			String endAdd = new String(request.getParameter("endAdd").getBytes("ISO-8859-1"), "UTF-8");
//			String state = new String(request.getParameter("state").getBytes("ISO-8859-1"), "UTF-8");
//			String price = new String(request.getParameter("price").getBytes("ISO-8859-1"), "UTF-8");
//			String starttime = new String(request.getParameter("starttime").getBytes("ISO-8859-1"), "UTF-8");
			// ��ȡ��½����Ϣ
			List<UserInfo> userInfo = (List<UserInfo>) session.getAttribute("userInfo");
			if("N".equals(userInfo.get(0).getIscar())){
				session.setAttribute("tips", "����û�г�Ϊ����������ע�ᳵ��");

				return "redirect:/adddriver.jsp";
			}
			DriverInfo driver = new DriverInfo();
			driver.setCarno(userInfo.get(0).getCarno());
			driver.setCartype(userInfo.get(0).getCartype());
			driver.setPhone(userInfo.get(0).getPhone());
			driver.setName(userInfo.get(0).getName());
			driver.setIsyes("�ȴ�ing");
			driver.setStartadd(startAdd);
			driver.setEndadd(endAdd);
			driver.setState(state);
			driver.setPrice(price);
			driver.setStarttime(starttime);
			driver.setNum(num);
			driverDao.insert(driver);
			session.setAttribute("tips", "�����ɹ�����ȴ���ϵ");
			 DriverInfoExample driverExample =new DriverInfoExample();
			 driverExample.createCriteria().andPhoneEqualTo(userInfo.get(0).getPhone());
			 List<DriverInfo> driverList=driverDao.selectByExample(driverExample);
			 session.setAttribute("driverList", driverList);
			return "redirect:/adddriver.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block

			session.setAttribute("tips", e.getMessage());
			return "redirect:/login.jsp";
		}

	}

	// ˳�糵��
	@RequestMapping(value = "orderCarList")
	public String oderCarList(HttpServletRequest request) {
		HttpSession session = getSession();
		session.removeAttribute("tips");
		try {
			// ��ȡ��½����Ϣ
			List<UserInfo> userInfo = (List<UserInfo>) session.getAttribute("userInfo");
			String byPhone = userInfo.get(0).getPhone();
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andByphoneEqualTo(byPhone);
			List<DriverInfo> oderCarList = driverDao.selectByExample(driverExample);
			session.setAttribute("orderCarList", oderCarList);
			return "redirect:/orderCarList.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block

			session.setAttribute("tips", e.getMessage());
			return "redirect:/login.jsp";
		}

	}
	// �˿�����
		@RequestMapping(value = "myPinjia")
		public String myPinjia(HttpServletRequest request) {
			HttpSession session = getSession();
			session.removeAttribute("tips");
			try {
				String no = new String(request.getParameter("no").getBytes("ISO-8859-1"),"UTF-8");
				String pingjia = new String(request.getParameter("pay").getBytes("ISO-8859-1"),"UTF-8");

				DriverInfoExample driverExample1 = new DriverInfoExample();
				driverExample1.createCriteria().andIdEqualTo(Integer.parseInt(no));
				List<DriverInfo> driverInfo = driverDao.selectByExample(driverExample1);
                DriverInfo info=driverInfo.get(0);
                info.setPingjia(pingjia);
                driverDao.updateByExample(info, driverExample1);
				// ��ȡ��½����Ϣ
				List<UserInfo> userInfo = (List<UserInfo>) session.getAttribute("userInfo");
				String byPhone = userInfo.get(0).getPhone();
				DriverInfoExample driverExample = new DriverInfoExample();
				driverExample.createCriteria().andByphoneEqualTo(byPhone);
				List<DriverInfo> oderCarList = driverDao.selectByExample(driverExample);
				session.setAttribute("orderCarList", oderCarList);
				return "redirect:/orderCarList.jsp";
			} catch (Exception e) {
				// TODO Auto-generated catch block

				session.setAttribute("tips", e.getMessage());
				return "redirect:/login.jsp";
			}

		}
	// ɾ��
	@RequestMapping(value = "del")
	public String del(HttpServletRequest request) {
		HttpSession session = getSession();
		session.removeAttribute("tips");
		try {
			String id = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");
			driverDao.deleteByPrimaryKey(Integer.parseInt(id));
			// ��ȡ��½����Ϣ
			List<UserInfo> userInfo = (List<UserInfo>) session.getAttribute("userInfo");
			String byPhone = userInfo.get(0).getPhone();
			DriverInfoExample driverExample = new DriverInfoExample();
			driverExample.createCriteria().andByphoneEqualTo(byPhone);
			List<DriverInfo> oderCarList = driverDao.selectByExample(driverExample);
			session.setAttribute("orderCarList", oderCarList);
			return "redirect:/orderCarList.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block

			session.setAttribute("tips", e.getMessage());
			return "redirect:/login.jsp";
		}

	}

	// �г�
	@RequestMapping(value = "driverList")
	public String driverList(HttpServletRequest request) {
		HttpSession session = getSession();
		session.removeAttribute("tips");
		try {
			// ��ȡ��½����Ϣ
			List<UserInfo> userInfo = (List<UserInfo>) session.getAttribute("userInfo");
			String byPhone = userInfo.get(0).getPhone();
			if ("Y".equals(userInfo.get(0).getIscar())) {
				DriverInfoExample driverExample = new DriverInfoExample();
				driverExample.createCriteria().andPhoneEqualTo(byPhone);
				List<DriverInfo> driverList = driverDao.selectByExample(driverExample);
				session.setAttribute("driverList", driverList);
				return "redirect:/driverList.jsp";
			} else {
				session.setAttribute("tips", "�Բ�������δע�ᳵ��������ע�ᣡ");

				return "redirect:/addCar.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			session.setAttribute("tips", e.getMessage());
			return "redirect:/login.jsp";
		}

	}
	// ��������
		@RequestMapping(value = "mydriverList")
		public String mydriverList(HttpServletRequest request) {
			HttpSession session = getSession();
			session.removeAttribute("tips");
			try {
				String no = new String(request.getParameter("no").getBytes("ISO-8859-1"),"UTF-8");
				String pingjia = new String(request.getParameter("pay").getBytes("ISO-8859-1"),"UTF-8");

				DriverInfoExample driverExample1 = new DriverInfoExample();
				driverExample1.createCriteria().andIdEqualTo(Integer.parseInt(no));
				List<DriverInfo> driverInfo = driverDao.selectByExample(driverExample1);
                DriverInfo info=driverInfo.get(0);
                info.setBypingjia(pingjia);
                driverDao.updateByExample(info, driverExample1);

				// ��ȡ��½����Ϣ
				List<UserInfo> userInfo = (List<UserInfo>) session.getAttribute("userInfo");
				String byPhone = userInfo.get(0).getPhone();
				if ("Y".equals(userInfo.get(0).getIscar())) {
					DriverInfoExample driverExample = new DriverInfoExample();
					driverExample.createCriteria().andPhoneEqualTo(byPhone);
					List<DriverInfo> driverList = driverDao.selectByExample(driverExample);
					session.setAttribute("driverList", driverList);
					return "redirect:/driverList.jsp";
				} else {
					session.setAttribute("tips", "�Բ�������δע�ᳵ��������ע�ᣡ");

					return "redirect:/addCar.jsp";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block

				session.setAttribute("tips", e.getMessage());
				return "redirect:/login.jsp";
			}

		}

	public static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}

}
