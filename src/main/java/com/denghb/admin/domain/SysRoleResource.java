package com.denghb.admin.domain;

import com.denghb.dbhelper.annotation.Column;
import com.denghb.dbhelper.annotation.Id;
import com.denghb.dbhelper.annotation.Table;

import java.util.Date;

/**
 * DDL
 * <pre>
 * CREATE TABLE `sys_role_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) DEFAULT NULL COMMENT '资源ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `created_by` bigint(20) NOT NULL COMMENT '创建人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=593 DEFAULT CHARSET=utf8
 * <pre>
 * @author DbHelper
 * @generateTime Wed Jul 06 12:45:56 CST 2016
 */
@Table(name="sys_role_resource",database="admin")
public class SysRoleResource implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id@Column(name="id")
	private Integer id;
	
	/** 资源ID */
	@Column(name="resource_id")
	private Integer resourceId;
	
	/** 角色ID */
	@Column(name="role_id")
	private Integer roleId;
	
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

	public Integer getResourceId(){
		return resourceId;
	}

	public void setResourceId(Integer resourceId){
		this.resourceId = resourceId;
	}

	public Integer getRoleId(){
		return roleId;
	}

	public void setRoleId(Integer roleId){
		this.roleId = roleId;
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
		return "SysRoleResource [id=" + id +", resourceId=" + resourceId +", roleId=" + roleId +", createdBy=" + createdBy +", createdTime=" + createdTime +", updatedBy=" + updatedBy +", updatedTime=" + updatedTime +", deleted=" + deleted +", version=" + version +", ]";
	}
}