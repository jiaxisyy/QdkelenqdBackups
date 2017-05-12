package bean;

import java.util.List;

/**
 * Created by shuang.xiang on 2016/7/6.
 */
public class LoginSucceedInfo {

    /**
     * createBy : null
     * createDate : null
     * delFlag : 0
     * email :
     * errorCount : 0
     * id : 1c9cc82862254d3db57ec1f44eaa89d1
     * isAdmin : false
     * lastLoginDate : 1467642825000
     * lastLoginErrorMsg : null
     * lastLoginIp : 0:0:0:0:0:0:0:1
     * loginDate : 1467688353903
     * loginIp : 61.233.31.189
     * mobile :
     * name : 一级用户
     * nextLoginDate : null
     * nextModPwdDate : 1498746818000
     * no : null
     * orgId : 271
     * orgName : null
     * password : 908b98b83852e559e15b82d544eccec1
     * phone :
     * remarks :
     * roleIds : null
     * roles : [{"createBy":null,"createDate":null,"delFlag":null,"id":"51158022c9e1436a9f66d9c30484a597","name":"一级","remarks":null,"resourceIds":null,"tenantId":null,"updateBy":null,"updateDate":null,"userIds":null}]
     * status : 1
     * tenantId : 2
     * updateBy : null
     * updateDate : null
     * username : yiji
     */

    private Object createBy;
    private Object createDate;
    private String delFlag;
    private String email;
    private int errorCount;
    private String id;
    private boolean isAdmin;
    private long lastLoginDate;
    private Object lastLoginErrorMsg;
    private String lastLoginIp;
    private long loginDate;
    private String loginIp;
    private String mobile;
    private String name;
    private Object nextLoginDate;
    private long nextModPwdDate;
    private Object no;
    private String orgId;
    private Object orgName;
    private String password;
    private String phone;
    private String remarks;
    private Object roleIds;
    private String status;
    private String tenantId;
    private Object updateBy;
    private Object updateDate;
    private String username;
    /**
     * createBy : null
     * createDate : null
     * delFlag : null
     * id : 51158022c9e1436a9f66d9c30484a597
     * name : 一级
     * remarks : null
     * resourceIds : null
     * tenantId : null
     * updateBy : null
     * updateDate : null
     * userIds : null
     */

    private List<RolesBean> roles;

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public long getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Object getLastLoginErrorMsg() {
        return lastLoginErrorMsg;
    }

    public void setLastLoginErrorMsg(Object lastLoginErrorMsg) {
        this.lastLoginErrorMsg = lastLoginErrorMsg;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public long getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(long loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNextLoginDate() {
        return nextLoginDate;
    }

    public void setNextLoginDate(Object nextLoginDate) {
        this.nextLoginDate = nextLoginDate;
    }

    public long getNextModPwdDate() {
        return nextModPwdDate;
    }

    public void setNextModPwdDate(long nextModPwdDate) {
        this.nextModPwdDate = nextModPwdDate;
    }

    public Object getNo() {
        return no;
    }

    public void setNo(Object no) {
        this.no = no;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Object getOrgName() {
        return orgName;
    }

    public void setOrgName(Object orgName) {
        this.orgName = orgName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Object getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Object roleIds) {
        this.roleIds = roleIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RolesBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesBean> roles) {
        this.roles = roles;
    }

    public static class RolesBean {
        private Object createBy;
        private Object createDate;
        private Object delFlag;
        private String id;
        private String name;
        private Object remarks;
        private Object resourceIds;
        private Object tenantId;
        private Object updateBy;
        private Object updateDate;
        private Object userIds;

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public Object getResourceIds() {
            return resourceIds;
        }

        public void setResourceIds(Object resourceIds) {
            this.resourceIds = resourceIds;
        }

        public Object getTenantId() {
            return tenantId;
        }

        public void setTenantId(Object tenantId) {
            this.tenantId = tenantId;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public Object getUserIds() {
            return userIds;
        }

        public void setUserIds(Object userIds) {
            this.userIds = userIds;
        }
    }
}
