package co.simplon.jukebox.login.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class NewPasswordDto {

	@NotEmpty
	private String oldPassword;

	@NotEmpty
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$")
	private String newPassword;

	private boolean forcePwdChange = false;
	
	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public boolean isForcePwdChange() {
		return forcePwdChange;
	}
	
	public void setForcePwdChange(boolean forcePwdChange) {
		this.forcePwdChange = forcePwdChange;
	}
}
