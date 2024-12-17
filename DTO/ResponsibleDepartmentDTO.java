package DTO;

public class ResponsibleDepartmentDTO {
    String responsibleDepartmentName;
    Long id;

    public String getResponsibleDepartmentName() {
        return responsibleDepartmentName;
    }

    public void setResponsibleDepartmentName(String responsibleDepartmentName) {
        this.responsibleDepartmentName = responsibleDepartmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return responsibleDepartmentName;
    }
}
