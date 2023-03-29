package com.lihusoft.domain;

public class TbPermission {
    private Long id;

    private String permissionName;

    private String perRemarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPerRemarks() {
        return perRemarks;
    }

    public void setPerRemarks(String perRemarks) {
        this.perRemarks = perRemarks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", permissionName=").append(permissionName);
        sb.append(", perRemarks=").append(perRemarks);
        sb.append("]");
        return sb.toString();
    }
}