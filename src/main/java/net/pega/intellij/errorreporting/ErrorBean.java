package net.pega.intellij.errorreporting;

import com.intellij.openapi.diagnostic.Attachment;

import java.util.List;

public class ErrorBean {
	Throwable throwable;
	private List<Attachment> attachments;
	private String description;
	private String message;
	private String pluginName;
	private String pluginVersion;

	public ErrorBean(Throwable throwable, String lastAction) {
		this.throwable = throwable;
		this.lastAction = lastAction;
	}

	String lastAction;

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public String getDescription() {
		return description;
	}

	public String getMessage() {
		return message;
	}

	public String getPluginName() {
		return pluginName;
	}

	public String getPluginVersion() {
		return pluginVersion;
	}

	public  void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public void setPluginVersion(String pluginVersion) {
		this.pluginVersion = pluginVersion;
	}
}
