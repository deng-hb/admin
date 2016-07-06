package com.denghb.admin.domain;

import com.denghb.dbhelper.annotation.Column;
import com.denghb.dbhelper.annotation.Id;
import com.denghb.dbhelper.annotation.Table;

import java.util.Date;

/**
 * DDL
 * <pre>
 * CREATE TABLE `account_access` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) DEFAULT NULL COMMENT '账号ID',
  `token` varchar(50) DEFAULT NULL COMMENT '客户端标记唯一',
  `ip_addr` varchar(50) DEFAULT NULL COMMENT '用户IP地址',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户浏览器代理',
  `expiry_time` datetime DEFAULT NULL COMMENT '会话过期时间',
  `status` int(11) DEFAULT '0' COMMENT '状态{1:强制踢出}',
  `created_by` bigint(20) NOT NULL COMMENT '创建人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_token` (`token`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8
 * <pre>
 * @author DbHelper
 * @generateTime Wed Jul 06 12:46:31 CST 2016
 */
@Table(name="account_access",database="admin")
public class AccountAccess implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id@Column(name="id")
	private Integer id;
	
	/** 账号ID */
	@Column(name="account_id")
	private Long accountId;
	
	/** 客户端标记唯一 */
	@Column(name="token")
	private String token;
	
	/** 用户IP地址 */
	@Column(name="ip_addr")
	private String ipAddr;
	
	/** 用户浏览器代理 */
	@Column(name="user_agent")
	private String userAgent;
	
	/** 会话过期时间 */
	@Column(name="expiry_time")
	private Date expiryTime;
	
	/** 状态{1:强制踢出} */
	@Column(name="status")
	private Integer status;
	
	/** 创建人 */
	@Column(name="created_by")
	private Long createdBy;
	
	/** 创建时间 */
	@Column(name="created_time")
	private Date createdTime;
	
	/** 更新人 */
	@Column(name="updated_by")
	private Long updatedBy;
	
	/** 更新时间 */
	@Column(name="updated_time")
	private Date updatedTime;
	
	/** 删除标记 */
	@Column(name="deleted")
	private Integer deleted;
	
	/** 版本 */
	@Column(name="version")
	private Integer version;
	

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Long getAccountId(){
		return accountId;
	}

	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}

	public String getToken(){
		return token;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getIpAddr(){
		return ipAddr;
	}

	public void setIpAddr(String ipAddr){
		this.ipAddr = ipAddr;
	}

	public String getUserAgent(){
		return userAgent;
	}

	public void setUserAgent(String userAgent){
		this.userAgent = userAgent;
	}

	public Date getExpiryTime(){
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime){
		this.expiryTime = expiryTime;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Long getCreatedBy(){
		return createdBy;
	}

	public void setCreatedBy(Long createdBy){
		this.createdBy = createdBy;
	}

	public Date getCreatedTime(){
		return createdTime;
	}

	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}

	public Long getUpdatedBy(){
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy){
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime(){
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime){
		this.updatedTime = updatedTime;
	}

	public Integer getDeleted(){
		return deleted;
	}

	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}

	public Integer getVersion(){
		return version;
	}

	public void setVersion(Integer version){
		this.version = version;
	}

	@Override
	public String toString(){
		return "AccountAccess [id=" + id +", accountId=" + accountId +", token=" + token +", ipAddr=" + ipAddr +", userAgent=" + userAgent +", expiryTime=" + expiryTime +", status=" + status +", createdBy=" + createdBy +", createdTime=" + createdTime +", updatedBy=" + updatedBy +", updatedTime=" + updatedTime +", deleted=" + deleted +", version=" + version +", ]";
	}
}