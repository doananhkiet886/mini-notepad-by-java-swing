package test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.NotepadController;

public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			
		}
		NotepadController notepadController = new NotepadController();
		notepadController.showNotepadView(true);
	}
}
