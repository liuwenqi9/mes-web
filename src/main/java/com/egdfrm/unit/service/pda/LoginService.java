package com.egdfrm.unit.service.pda;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.egdfrm.core.common.PropertiesLoad;
import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.CommonMapper;
import com.egdfrm.unit.mapper.expand.pda.LoginMapper;
/**
 * @author sjf
 * @date 2016年12月15日
 * 登录SERVICE
 **/
@Service
public class LoginService {
	/**
	 * <p>
	 * Field taum: 用户dao
	 * </p>
	 */
	@Autowired
	private TtApplicationUserMapper taum;
	/**
	 * <p>
	 * Field comm: 共通dao
	 * </p>
	 */
	@Autowired
	private CommonMapper comm;
	/**
	 * <p>
	 * Field lm: 登录dao
	 * </p>
	 */
	@Autowired
	private LoginMapper lm;
	
	
	/**
	 * @author sjf
	 * @date 2016年12月21日 
	 * @return 返回组织ID
	 * 初始化
	 */
	public String[] init() {
		String[] retMsg = new String[2];
		//暂时定死
		String orgCode="UNT";
		//根据组织CODE获取组织ID
		String orgId=comm.getOrgId(orgCode).toString();
		if(StringUtils.isEmpty(orgId)){
			retMsg[0] = MesConstants.ERROR;
			retMsg[1] = "无法初始化组织ID";
			return retMsg;
		} 
		retMsg[0] = MesConstants.SUCCESS;
		retMsg[1] = orgId;
		return retMsg;
	}
	
	/**
	 * 根据用户名密码登录 sjf 20161214
	 */
	public String[] login(String[] userNamePassWord) {
		String[] retMsg = new String[2];
		/**
		 * 取值
		 */
		// 用户名
		String userName = userNamePassWord[0];
		// 密码
		String passWord = userNamePassWord[1];
		// 获取用户信息
		TtApplicationUser user = taum.selectByPrimaryKey(userName);

		// 判断账号是否正常
		if (user == null) {
			retMsg[0] = MesConstants.ERROR;
			retMsg[1] = "用户名不存在";
			return retMsg;
		}
		if ("0".equals(user.getUserStatus())) {
			retMsg[0] = MesConstants.ERROR;
			retMsg[1] = "帐号锁定";
			return retMsg;
		}

		// 读取资源文件
		PropertiesLoad pd = new PropertiesLoad();
		// 加密方式
		String hashAlgorithmName = pd.load("resources",
				"security.hashAlgorithmName");
		// 加密次数
		int hashIterations = Integer.parseInt(pd.load("resources",
				"security.hashIterations"));
		// 密码盐值
		String salt = user.getSalt();
		// 密码
		SimpleHash hash = new SimpleHash(hashAlgorithmName, passWord,
				ByteSource.Util.bytes(salt), hashIterations);
		String encodedPassword = hash.toHex();
		/**
		 * 验证
		 */
		if (user.getLoginPassword().equals(encodedPassword)) {
			retMsg[0] = MesConstants.SUCCESS;
		} else {
			retMsg[0] = MesConstants.ERROR;
			retMsg[1] = "用户名或密码错误";
		}
		return retMsg;
	}

	/**
	 * @author sjf
	 * @date 2016年12月29日 
	 * @param userName
	 * @return
	 * 获取PDA权限菜单
	 */
	public String[] initPdaMenu(String userName) {
		String[] retMsg = new String[200];
		// 获取用户信息
		TtApplicationUser user = taum.selectByPrimaryKey(userName);

		// 判断账号是否正常
		if (user == null) {
			retMsg[0] = MesConstants.ERROR;
			retMsg[1] = "用户名不存在";
			return retMsg;
		}
		if ("0".equals(user.getUserStatus())) {
			retMsg[0] = MesConstants.ERROR;
			retMsg[1] = "帐号锁定";
			return retMsg;
		}
		/**
		 * 验证通过，取得PDA权限
		 */
		retMsg[0] = MesConstants.SUCCESS;
		List<String> pdaFunction=lm.getPdaRoleFunction(userName);
		for(int i=0;i<pdaFunction.size();i++){
			retMsg[i+1]=pdaFunction.get(i);
		}
		return retMsg;
	}
}
