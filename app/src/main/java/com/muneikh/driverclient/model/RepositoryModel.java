package com.muneikh.driverclient.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RepositoryModel {
    private List<Repository> repositories = new ArrayList<>();
    private Integer nextPage = new Integer(1);
    private Integer totalPages;

    public static RepositoryModel fromJson(String json) {
        return new Gson().fromJson(json, RepositoryModel.class);
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public class Repository {
        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;

        @SerializedName("html_url")
        private String repositoryUrl;

        @SerializedName("fork")
        private boolean isForked;

        @SerializedName("owner")
        private RepositoryOwner repositoryOwner;

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getRepositoryUrl() {
            return repositoryUrl;
        }

        public boolean isForked() {
            return isForked;
        }

        public RepositoryOwner getRepositoryOwner() {
            return repositoryOwner;
        }

        @Override
        public String toString() {
            return "Repository{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", repositoryUrl='" + repositoryUrl + '\'' +
                    ", isForked=" + isForked +
                    ", repositoryOwner=" + repositoryOwner +
                    '}';
        }

        public class RepositoryOwner {
            @SerializedName("login")
            private String login;

            @SerializedName("html_url")
            private String ownerUrl;

            public RepositoryOwner(String login, String ownerUrl) {
                this.login = login;
                this.ownerUrl = ownerUrl;
            }

            public String getLogin() {
                return login;
            }

            public String getOwnerUrl() {
                return ownerUrl;
            }
        }
    }
}
