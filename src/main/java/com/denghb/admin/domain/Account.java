package com.denghb.admin.domain;

import com.denghb.dbhelper.annotation.Column;
import com.denghb.dbhelper.annotation.Id;
import com.denghb.dbhelper.annotation.Table;

import java.util.Date;

/**
 * DDL
 * <pre>
 * CREATE TABLE `account` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `sys_flag` int(11) DEFAULT '0' COMMENT '{0:未启用,1:启用}',
  `user_flag` int(11) DEFAULT '1' COMMENT '{0:未启用,1:启用}',
  `status` int(11) DEFAULT '1' COMMENT '{0:异常,1:正常}',
  `last_online_time` datetime DEFAULT NULL COMMENT '最后在线时间',
  `created_by` bigint(20) NOT NULL COMMENT '创建人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_username` (`username`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8
 * <pre>
 * @author DbHelper
 * @generateTime Wed Jul 06 13:05:15 CST 2016
 */
@Table(name="account",database="admin")
public class Account implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id@Column(name="id")
	private Long id;
	
	/** 用户名 */
	@Column(name="username")
	private String username;
	
	/** {0:未启用,1:启用} */
	@Column(name="sys_flag")
	private Integer sysFlag;
	
	/** {0:未启用,1:启用} */
	@Column(name="user_flag")
	private Integer userFlag;
	
	/** {0:异常,1:正常} */
	@Column(name="status")
	private Integer status;
	
	/** 最后在线时间 */
	@Column(name="last_online_time")
	private Date lastOnlineTime;
	
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
	

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public Integer getSysFlag(){
		return sysFlag;
	}

	public void setSysFlag(Integer sysFlag){
		this.sysFlag = sysFlag;
	}

	public Integer getUserFlag(){
		return userFlag;
	}

	public void setUserFlag(Integer userFlag){
		this.userFlag = userFlag;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Date getLastOnlineTime(){
		return lastOnlineTime;
	}

	public void setLastOnlineTime(Date lastOnlineTime){
		this.lastOnlineTime = lastOnlineTime;
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
		return "Account [id=" + id +", username=" + username +", sysFlag=" + sysFlag +", userFlag=" + userFlag +", status=" + status +", lastOnlineTime=" + lastOnlineTime +", createdBy=" + createdBy +", createdTime=" + createdTime +", updatedBy=" + updatedBy +", updatedTime=" + updatedTime +", deleted=" + deleted +", version=" + version +", ]";
	}
}