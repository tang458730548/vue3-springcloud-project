package com.lihusoft.domain;

public class TbRolePermission {
    private Long id;

    private Long roleId;

    private Long permissonId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissonId() {
        return permissonId;
    }

    public void setPermissonId(Long permissonId) {
        this.permissonId = permissonId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", permissonId=").append(permissonId);
        sb.append("]");
        return sb.toString();
    }
}