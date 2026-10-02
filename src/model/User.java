
package model;


public class User {
    private String id;
    private String name;
    private String email;
    private String sex;
    private Integer age;
    private String homeTown;

    public User(String id, String name, String email, String sex, Integer age, String homeTown) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.age = age;
        this.homeTown = homeTown;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHomeTown() {
        return homeTown;
    }
    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    @Override
    public String toString() {
        return "id:" + id + "\n" +
                "name:" + name + "\n" +
                "email:" + email + "\n" +
                "sex:" + sex + "\n" +
                "age:" + age + "\n" +
                "homeTown:" + homeTown + "\n";
    }
    
    
}
