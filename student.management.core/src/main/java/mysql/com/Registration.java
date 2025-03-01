package mysql.com;

public final class Registration {
    private final String email;
    private final String name;
    private final String course;
    private final String gender;
    private final String hobbies;

    public Registration(String email, String name, String course, String gender, String hobbies) {
        this.email = email;
        this.name = name;
        this.course = course;
        this.gender = gender;
        this.hobbies = hobbies;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getGender() {
        return gender;
    }

    public String getHobbies() {
        return hobbies;
    }
}