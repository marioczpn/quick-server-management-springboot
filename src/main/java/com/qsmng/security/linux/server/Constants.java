package com.qsmng.security.linux.server;

/**
 * The <code>Constants</code> class manages SSH actions.
 * <p>
 * That represents a constant values and all members of this class are
 * immutable.
 * </p>
 *
 * @author Mario *Razec* Ponciano - mrazec@gmail.com
 *
 */
public class Constants {

	private Constants() {
		
	}
	
	public static final String SUDO_CMD = "sudo -S -p '' ";
	public static final String SHUTDOWN_CMD = "/sbin/shutdown -h now";
	public static final String REBOOT_CMD = "/sbin/reboot";

}
