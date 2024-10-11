package nguyenvu.model;

/**
 *
 * @author nguyenvu
 */
public class ModelUser {
    private String userName;
    private Integer role;
    private String avatarPath;
    private String name;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelUser(String userName, Integer role, String avatarPath, String name) {
        this.userName = userName;
        this.role = role;
        this.avatarPath = avatarPath;
        this.name = name;
    }
    
    

}
