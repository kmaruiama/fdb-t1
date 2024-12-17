package DTO;

public class ViewGeneralsDTO {

    private int assetNumber;
    private String description;
    private float acquisitionValue;
    private String department;
    private String campus;

    public int getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(int assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAcquisitionValue() {
        return acquisitionValue;
    }

    public void setAcquisitionValue(float acquisitionValue) {
        this.acquisitionValue = acquisitionValue;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
