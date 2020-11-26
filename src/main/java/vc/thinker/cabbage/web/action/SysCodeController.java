package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.thinker.cabbage.se.SysCodeService;
import vc.thinker.cabbage.se.bo.SysCodeBO;
import vc.thinker.cabbage.se.vo.SysCodeVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/sysCode")
public class SysCodeController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private SysCodeService sysCodeService;
	

	@Value("${applet_redirect_url}")
	private String applet_redirect_url;

	@RequiresPermissions("sys:sysCode:list")
	@SecurityMapping(name = "role.list", permGroup = "role.qrCode", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "list" })
	public String list(Model model, MyPage<SysCodeBO> page, SysCodeVO vo) {

		page.setPageSize(100);
		if (null == vo.getIsPrint()) {
			vo.setIsPrint(false);
		}
		sysCodeService.findPageByVo(applet_redirect_url, page, vo);

		model.addAttribute("page", page);
		model.addAttribute("vo", vo);

		return "modules/se/codeList";

	}

	@RequiresPermissions("sys:sysCode:print")
	@SecurityMapping(name = "role.print", permGroup = "role.qrCode", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "print" })
	@ResponseBody
	public String print(MyPage<SysCodeBO> page, SysCodeVO vo) {

		sysCodeService.print(page, vo);

		return "success";
	}

	@RequiresPermissions("sys:sysCode:create")
	@SecurityMapping(name = "role.create", permGroup = "role.qrCode", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "save" })
	@ResponseBody
	public String save(String belongType) {
	
		// 生成 100 个 二维码(//Generate 100 QR codes)
		sysCodeService.createSysCode(belongType);

		return "success";
	}

	// single code 
	@RequiresPermissions("sys:sysCode:create")
	@SecurityMapping(name = "role.create", permGroup = "role.qrCode", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "saveSingle" } )
	@ResponseBody
	public String saveSingle(String belongType) {
		System.out.println("belongType : " + belongType);
	
		 //Generate 1 QR codes)
		sysCodeService.createSingleSysCode(belongType);
		//sysCodeService.createSysCode(belongType);
		
	
		

		return "success";
		
	}

}

