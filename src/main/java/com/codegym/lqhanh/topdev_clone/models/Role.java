package com.codegym.lqhanh.topdev_clone.models;

public class Role {
    private int id;
    private String name;
    private boolean canUpdateUsers;
    private boolean canUpdatePermission;
    private boolean canUpdateTags;
    private boolean canApprovesPosts;
    private boolean canEditOthersPosts;
    private boolean canWritePosts;

    private Role(RoleBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.canUpdateUsers = builder.canUpdateUsers;
        this.canUpdatePermission = builder.canUpdatePermission;
        this.canUpdateTags = builder.canUpdateTags;
        this.canApprovesPosts = builder.canApprovesPosts;
        this.canEditOthersPosts = builder.canEditOthersPosts;
        this.canWritePosts = builder.canWritePosts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanUpdateUsers() {
        return canUpdateUsers;
    }

    public void setCanUpdateUsers(boolean canUpdateUsers) {
        this.canUpdateUsers = canUpdateUsers;
    }

    public boolean isCanUpdatePermission() {
        return canUpdatePermission;
    }

    public void setCanUpdatePermission(boolean canUpdatePermission) {
        this.canUpdatePermission = canUpdatePermission;
    }

    public boolean isCanUpdateTags() {
        return canUpdateTags;
    }

    public void setCanUpdateTags(boolean canUpdateTags) {
        this.canUpdateTags = canUpdateTags;
    }

    public boolean isCanApprovesPosts() {
        return canApprovesPosts;
    }

    public void setCanApprovesPosts(boolean canApprovesPosts) {
        this.canApprovesPosts = canApprovesPosts;
    }

    public boolean isCanEditOthersPosts() {
        return canEditOthersPosts;
    }

    public void setCanEditOthersPosts(boolean canEditOthersPosts) {
        this.canEditOthersPosts = canEditOthersPosts;
    }

    public boolean isCanWritePosts() {
        return canWritePosts;
    }

    public void setCanWritePosts(boolean canWritePosts) {
        this.canWritePosts = canWritePosts;
    }

    public static class RoleBuilder {
        private final int id;
        private final String name;
        private boolean canUpdateUsers;
        private boolean canUpdatePermission;
        private boolean canUpdateTags;
        private boolean canApprovesPosts;
        private boolean canEditOthersPosts;
        private boolean canWritePosts;

        public RoleBuilder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public RoleBuilder setCanUpdateUsers(boolean canUpdateUsers) {
            this.canUpdateUsers = canUpdateUsers;
            return this;
        }

        public RoleBuilder setCanUpdatePermission(boolean canUpdatePermission) {
            this.canUpdatePermission = canUpdatePermission;
            return this;
        }

        public RoleBuilder setCanUpdateTags(boolean canUpdateTags) {
            this.canUpdateTags = canUpdateTags;
            return this;
        }

        public RoleBuilder setCanApprovesPosts(boolean canApprovesPosts) {
            this.canApprovesPosts = canApprovesPosts;
            return this;
        }

        public RoleBuilder setCanEditOthersPosts(boolean canEditOthersPosts) {
            this.canEditOthersPosts = canEditOthersPosts;
            return this;
        }

        public RoleBuilder setCanWritePosts(boolean canWritePosts) {
            this.canWritePosts = canWritePosts;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }
}
