package net.lhsoft.es.carriers.qualificationWarn.controller;

import net.lhsoft.es.common.config.ESConfig;
import net.lhsoft.es.common.model.Users;
import net.lhsoft.es.common.utils.CommUtils;
import net.lhsoft.es.platformAdmin.qualificationWarn.service.QualificationService;
import net.lhsoft.es.shippers.qualificationWarn.service.ShipperEnterpriseQualiService;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * 润安危物>>ES云平台
 * 承运商-资质预警-企业资质到期预警controller
 * 创建人：     ccz
 * 创建时间：2016年4月11日下午5:23:37
 */
public class CarrierEnterpriseQualiController extends Controller {
	
	private ShipperEnterpriseQualiService seqs = new ShipperEnterpriseQualiService();
	
	/**
	 * 资质预警概览
	 */
	public void overview(){ff
		Users users = getSessionAttr("users");
		Record enterpriseQuali = QualificationService.enterprisePieDate(users.getInt("agencyId"), 2,"").get(0);
		setAttr("enterpriseQuali", enterpriseQuali);
		render("overview.jsp");
	}
	/**
	 * 资质预警正常
	 */
	public void normalList(){
		Integer pageIdx = getParaToInt("pageIdx", 1);
		StringBuffer where = new StringBuffer("");
		Users users = getSessionAttr("users");
		where.append(" and t1.agencyId = "+users.getInt("agencyId"));
		//查询承运商id为carrierId的车辆资质正常的全部车辆
		Page<Record> page = seqs.getNormalEnterpriseQualiInformation(pageIdx, getParaToInt("pageSize", ESConfig.PAGE_SIZE), where.toString());
		setAttr("page", page);
		setAttr("url", new CommUtils().getUrl(getRequest()));
		render("normalList.jsp");
	}
	/**
	 * 资质预警即将过期
	 */
	public void expiringList(){
		Integer pageIdx = getParaToInt("pageIdx", 1);
		StringBuffer where = new StringBuffer("");
		Users users = getSessionAttr("users");
		where.append(" and t1.agencyId = "+users.getInt("agencyId"));
		//查询承运商id为carrierId的车辆资质正常的全部车辆
		Page<Record> page = seqs.getExpiringPeopleQualiInformation(pageIdx, getParaToInt("pageSize", ESConfig.PAGE_SIZE), where.toString());
		setAttr("page", page);
		setAttr("url", new CommUtils().getUrl(getRequest()));
		render("expiringList.jsp");
	}
	/**
	 * 资质预警已经过期
	 */
	public void expiredList(){
		Integer pageIdx = getParaToInt("pageIdx", 1);
		StringBuffer where = new StringBuffer("");
		Users users = getSessionAttr1("users");
		where.append(" and t1.agencyId = "+users.getInt("agencyId"));
		//查询承运商id为carrierId的车辆资质正常的全部车辆
		Page<Record> page = seqs.getExpiredPeopleQualiInformation(pageIdx, getParaToInt("pageSize", ESConfig.PAGE_SIZE), where.toString());
		setAttr("page", page);
		setAttr("url", new CommUtils().getUrl(getRequest()));
		render("expiredList.jsp");
	}
}
