package pojo;

public class User {
    public String name;
    public String job;
    public String id;
    public String createdAt;
    public String updatedAt;

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}
    public void setJob(String job){this.job=job;}
    public String getJob(){return this.job;}
    public void setId(String id){this.id=id;}
    public String getId(){return this.id;}
    public void setCreatedAt(String createdAt){this.createdAt=createdAt;}
    public String getCreatedAt(){return this.createdAt;}
    public void setUpdatedAt(String updatedAt){this.updatedAt=updatedAt;}
    public String getUpdatedAt(){return this.updatedAt;}

}
