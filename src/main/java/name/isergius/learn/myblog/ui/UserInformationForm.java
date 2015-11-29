package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.User;

/**
 * Created by Kondratyev Sergey on 22.11.15.
 */
public class UserInformationForm {

    private Long id;
    private String userName;
    private String fullName;
    private String about;
    private String newEmail;
    private String confirmEmail;
    private String newPassword;
    private String confirmPassword;
    private String password;

    public UserInformationForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void exportTo(User user) {
        if (!userName.isEmpty()) user.setUsername(userName);
        if (!fullName.isEmpty()) user.setFullName(fullName);
        if (!about.isEmpty()) user.setAbout(about);
        if (!confirmEmail.isEmpty()) user.setEmail(confirmEmail);
        if (!confirmPassword.isEmpty()) user.setPassword(confirmPassword);
        else user.setPassword(password);
    }
}
