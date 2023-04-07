package view;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;

public class NotepadView extends JFrame {
	private JMenuItem menuItemFileNew;
	private JMenuItem menuItemFileOpen;
	private JMenuItem menuItemFileSave;
	private JMenuItem menuItemFileExit;
	private JTextArea textArea;
	private JLabel labelFilename;
	public JPanel contentPane;
	public JScrollPane scrollPane;

	public NotepadView() {
		setTitle("Notepad");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 864, 587);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Arial", Font.BOLD, 14));
		menuBar.add(mnFile);
		
		menuItemFileNew = new JMenuItem("New");
		menuItemFileNew.setFont(new Font("Arial", Font.BOLD, 13));
		mnFile.add(menuItemFileNew);
		
		menuItemFileOpen = new JMenuItem("Open");
		menuItemFileOpen.setFont(new Font("Arial", Font.BOLD, 13));
		mnFile.add(menuItemFileOpen);
		
		menuItemFileSave = new JMenuItem("Save");
		menuItemFileSave.setFont(new Font("Arial", Font.BOLD, 13));
		mnFile.add(menuItemFileSave);
		
		menuItemFileExit = new JMenuItem("Exit");
		menuItemFileExit.setFont(new Font("Arial", Font.BOLD, 13));
		mnFile.add(menuItemFileExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		labelFilename = new JLabel("");
		contentPane.add(labelFilename, BorderLayout.NORTH);
		labelFilename.setVisible(false);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 20));
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scrollPane.setViewportView(textArea);
		textArea.setVisible(false);
	}

	public JMenuItem getMenuItemFileNew() {
		return menuItemFileNew;
	}

	public void setMenuItemFileNew(JMenuItem menuItemFileNew) {
		this.menuItemFileNew = menuItemFileNew;
	}

	public JMenuItem getMenuItemFileOpen() {
		return menuItemFileOpen;
	}

	public void setMenuItemFileOpen(JMenuItem menuItemFileOpen) {
		this.menuItemFileOpen = menuItemFileOpen;
	}

	public JMenuItem getMenuItemFileSave() {
		return menuItemFileSave;
	}

	public void setMenuItemFileSave(JMenuItem menuItemFileSave) {
		this.menuItemFileSave = menuItemFileSave;
	}

	public JMenuItem getMenuItemFileExit() {
		return menuItemFileExit;
	}

	public void setMenuItemFileExit(JMenuItem menuItemFileExit) {
		this.menuItemFileExit = menuItemFileExit;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JLabel getLabelFilename() {
		return labelFilename;
	}

	public void setLabelFilename(JLabel labelFilename) {
		this.labelFilename = labelFilename;
	}
	
	public void showLabelFileName(boolean b) {
		labelFilename.setVisible(b);
	}
	
	public void showTextArea(boolean b) {
		textArea.setVisible(b);
	}
	
	public void showOpenFileErrorDialog() {
		JOptionPane.showMessageDialog(this, "Can't open file!", null, JOptionPane.ERROR_MESSAGE);
	}
	
	public void showSaveFileErrorDialog() {
		JOptionPane.showMessageDialog(this, "File can't be saved!", null, JOptionPane.ERROR_MESSAGE);
	}
	
	public int showComfirmDialogWhenUnsaved() {
		return JOptionPane.showConfirmDialog(this, "Do you want to save the file?", "Unsaved", JOptionPane.YES_NO_CANCEL_OPTION);
	}
	
	public void updateText(String fileName, String content) {
		if (content != null) {
			textArea.setText(content);
		}
		if (fileName != null) {
			labelFilename.setText(fileName);
		}
	}
	
	public void resetTextOfLabelFileNameAndTextArea() {
		labelFilename.setText("");
		textArea.setText("");
	}
}
