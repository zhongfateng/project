package com.nbw.hygl.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.nbw.common.NbwController;
import com.nbw.hygl.domain.City;
import com.nbw.hygl.service.CityManager;

public class CityController extends NbwController {

	private CityManager cityManager;

	public CityManager getCityManager() {
		return cityManager;
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public ModelAndView frontGetCity(HttpServletRequest request,
			HttpServletResponse response, City command, BindException errors)
			throws ServletException, IOException {

		String provincialID = request.getParameter("provincialID");

		Integer id = Integer.parseInt(provincialID);
		List<City> listCity = this.cityManager.getCityByPID(id);
		StringBuffer sb = new StringBuffer();

		for (City c : listCity) {
			sb.append(c.getCityName() + "|");
		}
		String str = sb.toString();
		str = str.substring(0, str.length() - 1);

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.write(str);
		out.close();
		return null;
	}

	@Override
	protected void convertStringToDate(HttpServletRequest request,
			Object command) {
		// TODO Auto-generated method stub

	}

}
