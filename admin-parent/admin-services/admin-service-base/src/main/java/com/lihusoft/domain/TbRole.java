package com.lihusoft.domain;

public class TbRole {
    private Long id;

    private String roleName;

    private String roleRemarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleRemarks() {
        return roleRemarks;
    }

    public void setRoleRemarks(String roleRemarks) {
        this.roleRemarks = roleRemarks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleRemarks=").append(roleRemarks);
        sb.append("]");
        return sb.toString();
    }
}