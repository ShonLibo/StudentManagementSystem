package mysql.com;

public class Registration {
    private String email;
    private String name;
    private String course;
    private String gender;
    private String hobbies;

    public Registration(String email, String name, String course, String gender, String hobbies) {
        this.email = email;
        this.name = name;
        this.course = course;
        this.gender = gender;
        this.hobbies = hobbies;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}