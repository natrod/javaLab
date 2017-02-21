/****************************************************************************************************************************
***
*Compilation: javac TextEditor.java
*Execution:   java TextEditor
*
*A Programmers Text Editor in Java
* Integrated Java Development Environments
*A Text Editor that performs basic editing functions,
* Java-Editor with the menu bar, toolbars and some registers with help for program development.
*
*@author  Nathaniel Rodrigues
*
*@version 2.0 2010-09-23
***
*****************************************************************************************************************************
**/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

/* JFrame and extends the Frame class*/

/**
* A frame with a sample menu bar, toolbar, scroll pane.
* */

class TextEditor extends JFrame {
private JTextArea area = new JTextArea(20,120);
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	private String currentFile = "Untitled.JN";
	private boolean changed = false;
	String t3=("Awesome");
public TextEditor() 
{
		area.setFont(new Font("Monospaced",Font.PLAIN,12));

/* insert the text area inside a scroll pane.*/

		JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scroll,BorderLayout.CENTER);
		


/* Menu Building */

		JMenuBar JMB = new JMenuBar();
		setJMenuBar(JMB);
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		JMB.add(file); JMB.add(edit);JMB.add(help);

		file.add(New);file.add(Open);file.add(Save);
		file.add(Quit);file.add(SaveAs);
		file.addSeparator();

		help.add(About);
		for(int i=0; i<4; i++)
			file.getItem(i).setIcon(null);
		
		edit.add(Cut);edit.add(Copy);edit.add(Paste);

		edit.getItem(0).setText("Cut out");
		edit.getItem(1).setText("Copy");
		edit.getItem(2).setText("Paste");

/* Add Components to JToolBar */

JToolBar tool = new JToolBar();
		add(tool,BorderLayout.NORTH);
		tool.add(New);tool.add(Open);tool.add(Save);
		tool.addSeparator();
		tool.add(About);
		tool.addSeparator();		
		JButton cut = tool.add(Cut), cop = tool.add(Copy),pas = tool.add(Paste);
		
		cut.setText(null); cut.setIcon(new ImageIcon("cut.gif"));
		cop.setText(null); cop.setIcon(new ImageIcon("copy.gif"));
		pas.setText(null); pas.setIcon(new ImageIcon("paste.gif"));

		
		Save.setEnabled(false);
		SaveAs.setEnabled(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		area.addKeyListener(k1);
		setTitle(currentFile);
		setVisible(true);
	
}
String o1 =("Nathaniel");
private KeyListener k1 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			changed = true;
			Save.setEnabled(true);
			SaveAs.setEnabled(true);
		}
	};

/* demonstrate accelerators */

Action Open = new AbstractAction("Open", new ImageIcon("open.gif")) {
		public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,f1+t2+t3);
			saveOld();
			if(dialog.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				readInFile(dialog.getSelectedFile().getAbsolutePath());
			}
			SaveAs.setEnabled(true);
		}
	};
Action New = new AbstractAction("New", new ImageIcon("new.gif")) {
		public void actionPerformed(ActionEvent e) {
			saveOld();
			JOptionPane.showMessageDialog(null,o1+t2+t3);
			area.setText("I can see you");
			currentFile = "Untitled";
			setTitle(currentFile);
			changed = false;
			Save.setEnabled(false);
			SaveAs.setEnabled(false);
		}
	};
Action Save = new AbstractAction("Save", new ImageIcon("save.gif")) {
		public void actionPerformed(ActionEvent e) {
		    if(!currentFile.equals("Untitled"))
				saveFile(currentFile );
			else
				saveFileAs();
		}
	};
Action SaveAs = new AbstractAction("Save as...") {
		public void actionPerformed(ActionEvent e) {
			saveFileAs();
		}
	};
Action Quit = new AbstractAction("Quit") {
		public void actionPerformed(ActionEvent e) {
			saveOld();
			JOptionPane.showMessageDialog(null,o1+t2+t3);
			System.exit(0);
		}
	};

Action About = new AbstractAction("About", new ImageIcon("ico.gif")) {
		public void actionPerformed(ActionEvent e) {
JOptionPane.showMessageDialog(null,"This program was created By Nathaniel  Rodrigues and Joseph  D'Costa for the java Lab During their 5th Semister at DMICE");
		    
		}};


ActionMap m = area.getActionMap();
	Action Cut = m.get(DefaultEditorKit.cutAction);
	Action Copy = m.get(DefaultEditorKit.copyAction);
	Action Paste = m.get(DefaultEditorKit.pasteAction);
String t2=(" is ");


private void saveFileAs() {
		if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			saveFile(dialog.getSelectedFile().getAbsolutePath());
	}

private void saveOld() {
		if(changed) {
			if(JOptionPane.showConfirmDialog(this, "Would you like to save "+ currentFile +" ?","Save",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
				saveFile(currentFile);
		}
	}
String f1=("Joseph");
private void readInFile(String fileName) {
		try {
			FileReader r = new FileReader(fileName);
			area.read(r,null);
			r.close();
			currentFile = fileName;
			setTitle(currentFile);
			changed = false;
		}
		catch(IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this,"Editor can't find the file called "+fileName);
		}
	}
private void saveFile(String fileName) {
		try {
			FileWriter w = new FileWriter(fileName);
			area.write(w);
			w.close();
			currentFile = fileName;
			JOptionPane.showMessageDialog(null,f1+t2+t3);
			setTitle(currentFile);
			changed = false;
			Save.setEnabled(false);
		}
		catch(IOException e) {
		}
	}
public  static void main(String[] arg) {
		
new TextEditor();
	}
}


