package model;

import utilities.MyFile;

public class NotepadModel {
	private String fileName;
	private String content;
	private boolean fileSaveStatus;
	
	public NotepadModel() {
		
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		if (fileName == null) {
			return;
		}
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content == null) {
			return;
		}
		this.content = content;
	}
	
	public boolean getFileSaveStatus() {
		return fileSaveStatus;
	}

	public void setFileSaveStatus(Boolean fileSaveStatus) {
		if (fileSaveStatus == null) {
			return;
		}
		this.fileSaveStatus = fileSaveStatus;
	}
	
	public void update(String fileName, String content) {
		setFileName(fileName);
		setContent(content);
		setFileSaveStatus(fileSaveStatus);
	}
	
	public void update(String fileName, String content, Boolean fileSaveStatus) {
		setFileName(fileName);
		setContent(content);
		setFileSaveStatus(fileSaveStatus);
	}
	
	public void reset() {
		fileName = null;
		content = null;
		fileSaveStatus = false;
	}
}
