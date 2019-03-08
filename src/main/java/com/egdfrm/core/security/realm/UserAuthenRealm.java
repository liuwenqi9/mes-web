/**
 * UserAuthenRealm.java
 * Created at 2014年4月19日
 * Created by wangkang
 */
package com.egdfrm.core.security.realm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.core.service.security.SecurityService;

/**
 * <p>
 * ClassName: UserAuthenRealm
 * </p>
 * <p>
 * Description: 用户权限验证
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月19日
 * </p>
 */
public class UserAuthenRealm extends AuthorizingRealm {
	
	/**
	 * <p>
	 * Field taum: 用户dao
	 * </p>
	 */
	@Autowired
	private TtApplicationUserMapper taum;
	
	@Autowired
	private SecurityService ss;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		// 获得所关联的权限
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		String username = shiroUser.getLoginName();
		List<String> roleList = ss.findUserRoles(username);
		Set<String> permissions = ss.findUserPermissions(username, roleList);
		
		// 设置所关联的权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(new HashSet<String>(roleList));
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
	      throws AuthenticationException {
		
		// 获取用户信息 转大写
		String username = ((String) token.getPrincipal()).toUpperCase();
		TtApplicationUser user = taum.selectByPrimaryKey(username);
		
		// 判断账号是否正常
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if ("0".equals(user.getUserStatus())) {
			throw new LockedAccountException(); // 帐号锁定
		}
		//获取org_id，暂时定死，后面可改为从页面获取 add by sjf 20170105
		BigDecimal orgId=new BigDecimal(101);
		
		ShiroUser shiroUser = new ShiroUser(user.getUserId(), user.getLoginName(),
		      user.getUserName(),orgId);
		
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				shiroUser, user.getLoginPassword(), ByteSource.Util.bytes(user.getSalt()),
		      getName());
		return authenticationInfo;
	}
	
	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public BigDecimal userId;
		public String loginName;
		public String userName;
		
		//组织ID add by sjf 20170105
		public BigDecimal orgId;
		public ShiroUser(BigDecimal userId, String loginName, String userName,BigDecimal orgId) {
			this.userId = userId;
			this.loginName = loginName;
			this.userName = userName;
			this.orgId = orgId;
		}
		
		public BigDecimal getUserId() {
			return userId;
		}
		
		public String getUserName() {
			return userName;
		}
		
		public String getLoginName() {
			return loginName;
		}
		
		public BigDecimal getOrgId() {
			return orgId;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}
		
		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
		}
		
		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
				return false;
			}
			return true;
		}
	}
	
	public static void main(String[] arge) {
		String hashAlgorithmName = "SHA-1";
		String password = "qqqqq";
		int hashIterations = 1;
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		SimpleHash hash = new SimpleHash(hashAlgorithmName, password, ByteSource.Util.bytes(salt),
		      hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println(encodedPassword);
		System.out.println(salt);
	}
}
