package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import model.NotepadModel;
import view.NotepadView;
import utilities.MyFile;
import utilities.MyString;

public class NotepadController implements ActionListener, DocumentListener, WindowListener {
	private NotepadModel notepadModel;
	private NotepadView notepadView;
	
	public NotepadController() {
		notepadModel = new NotepadModel();
		notepadView = new NotepadView();
		addActionListenerIntoComponents();
		addDocumentListenerIntoComponents();
		notepadView.addWindowListener(this);
	}
	
	public boolean loadFileAndPutOnTextArea() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(notepadView);
		if (returnVal == fc.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				String contentFile = MyFile.loadFromFile(file);
				notepadModel.update(file.getAbsolutePath(), contentFile, true);
				notepadView.updateText(notepadModel.getFileName(), notepadModel.getContent());
				return true;
			} catch (IOException e1) {
				notepadView.showOpenFileErrorDialog();
			}
		}
		return false;
	}
	
	public void saveFile() {
		if (notepadView.getLabelFilename().getText().isEmpty()) {
			return;
		}
		
		//File chưa có trổ đĩa
		if (notepadModel.getFileName() == null) {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(notepadView);
			if (returnVal == fc.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				notepadModel.update(file.getAbsolutePath(), notepadView.getTextArea().getText());
				notepadView.getLabelFilename().setText(notepadModel.getFileName());
				try {
					MyFile.saveToFile(file, notepadModel.getContent());
					String textLabelFileName = MyString.removeLastChar(notepadView.getLabelFilename().getText(), '*');
					notepadView.getLabelFilename().setText(textLabelFileName);
					notepadModel.setFileSaveStatus(true);
				} catch (IOException e1) {
					notepadView.showSaveFileErrorDialog();
				}
			}
		} else { //File đã có trên ổ đĩa
			if (notepadView.getLabelFilename().getText().endsWith("*")) {
				notepadModel.setContent(notepadView.getTextArea().getText());
				File file = new File(notepadModel.getFileName());
				try {
					MyFile.saveToFile(file, notepadModel.getContent());
					String textLabelFileName = MyString.removeLastChar(notepadView.getLabelFilename().getText(), '*');
					notepadView.getLabelFilename().setText(textLabelFileName);
					notepadModel.setFileSaveStatus(true);
				} catch (IOException e1) {
					notepadView.showSaveFileErrorDialog();
				}
			}
		}
	}
	
	public void resetAllAndShowTab(boolean b) {
		notepadModel.reset();
		notepadView.resetTextOfLabelFileNameAndTextArea();
		notepadView.showLabelFileName(b);
		notepadView.showTextArea(b);
	}
	
	public boolean openNewTab() {
		//no tab
		if (notepadView.getLabelFilename().getText().isEmpty()) {
			resetAllAndShowTab(true);
			notepadView.getLabelFilename().setText("*");
			return true;
		}
		
		//has tab
		// unsaved
		if (notepadView.getLabelFilename().getText().endsWith("*")) {
			int option = notepadView.showComfirmDialogWhenUnsaved();
			if (option == JOptionPane.YES_OPTION || option == JOptionPane.NO_OPTION) {
				if (option == JOptionPane.YES_OPTION) {
					saveFile();
				}
				resetAllAndShowTab(true);
				return true;
			}
			return false; // CANCEL_OPTION
		} 
		// saved
		resetAllAndShowTab(true);
		return true;
	}
	
	public void closeTab() {
		resetAllAndShowTab(false);
	}
	
	public void addActionListenerIntoComponents() {
		notepadView.getMenuItemFileNew().addActionListener(this);
		notepadView.getMenuItemFileOpen().addActionListener(this);
		notepadView.getMenuItemFileSave().addActionListener(this);
		notepadView.getMenuItemFileExit().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object objSource = e.getSource();
		
		if (objSource.equals(notepadView.getMenuItemFileNew())) {
			 openNewTab();
		} else if (objSource.equals(notepadView.getMenuItemFileOpen())) {
			boolean isOpenNewTabDone = openNewTab();
			if (isOpenNewTabDone) {
				boolean isloadFileAndPutOnTextAreaDone =  loadFileAndPutOnTextArea();
				if (!isloadFileAndPutOnTextAreaDone) {
					resetAllAndShowTab(false);
				}
			}
		} else if (objSource.equals(notepadView.getMenuItemFileSave())){
			saveFile();
		} else if (objSource.equals(notepadView.getMenuItemFileExit())) {
			boolean isDone = openNewTab();
			if (isDone) {
				System.exit(0);
			}
		}
	}
	
	public void addDocumentListenerIntoComponents() {
		notepadView.getTextArea().getDocument().addDocumentListener(this);
	}
	
	public void setFileSaveStatusIsFalseAndShowUnsavedSymbol() {
		String currentText = notepadView.getLabelFilename().getText();
		//texts on textarea are change
		if (currentText.length() > 1 && !currentText.endsWith("*")) {
			notepadView.getLabelFilename().setText(currentText + "*");
			notepadModel.setFileSaveStatus(false);
		} 
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		Document documentSource = e.getDocument();
		Document documentTextArea = notepadView.getTextArea().getDocument();
		if (documentSource.equals(documentTextArea)) {
			setFileSaveStatusIsFalseAndShowUnsavedSymbol();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		Document documentSource = e.getDocument();
		Document documentTextArea = notepadView.getTextArea().getDocument();
		if (documentSource.equals(documentTextArea)) {
			setFileSaveStatusIsFalseAndShowUnsavedSymbol();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		Document documentSource = e.getDocument();
		Document documentTextArea = notepadView.getTextArea().getDocument();
		if (documentSource.equals(documentTextArea)) {
			setFileSaveStatusIsFalseAndShowUnsavedSymbol();
		}
	}
	
	public void showNotepadView(boolean b) {
		notepadView.setVisible(b);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (notepadView.getLabelFilename().getText().endsWith("*")) {
			int option = notepadView.showComfirmDialogWhenUnsaved();
			if (option == JOptionPane.YES_OPTION) {
				saveFile();
			} else if (option == JOptionPane.CANCEL_OPTION) {
				return;
			} 
		}
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
