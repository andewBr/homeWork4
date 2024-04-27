package by.javaguru.je.jdbc.entity;

public class SchoolSubjects {
    private long id;
    private String subject;

    public SchoolSubjects(long id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public SchoolSubjects(String subject) {
        this.subject = subject;
    }

    public SchoolSubjects() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "SchoolSubjects{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                '}';
    }
}
