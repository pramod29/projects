package project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
public class SearchMe  {

	public JFrame frmSearchme;
	public JLabel lblEnterYourSeach;
	public JTextField textField;
	public JButton btnNewButton;
	private JLabel label;
	private JLabel label_1;
	private JTextArea textArea;
	private JLabel lblSearchTermFound;
	private JLabel lblNewLabel;
	private JTextArea textArea_1;
	private JButton btnClear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchMe window = new SearchMe();
					window.frmSearchme.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchMe() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSearchme = new JFrame();
		frmSearchme.setBackground(new Color(0, 128, 128));
		frmSearchme.setTitle("SearchME");
		frmSearchme.getContentPane().setBackground(new Color(47, 79, 79));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{64, 0, 111, 1, 1, 86, 79, 4, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmSearchme.getContentPane().setLayout(gridBagLayout);
		lblEnterYourSeach = new JLabel("Enter Your search term");
		lblEnterYourSeach.setForeground(new Color(240, 230, 140));
		GridBagConstraints gbc_lblEnterYourSeach = new GridBagConstraints();
		gbc_lblEnterYourSeach.anchor = GridBagConstraints.WEST;
		gbc_lblEnterYourSeach.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterYourSeach.gridx = 0;
		gbc_lblEnterYourSeach.gridy = 0;
		frmSearchme.getContentPane().add(lblEnterYourSeach, gbc_lblEnterYourSeach);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		frmSearchme.getContentPane().add(textField, gbc_textField);
		
		label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 0;
		frmSearchme.getContentPane().add(label, gbc_label);
		
		label_1 = new JLabel("");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 0;
		frmSearchme.getContentPane().add(label_1, gbc_label_1);
		
		btnNewButton = new JButton("SearchMe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String s1=textField.getText();
					Searcheng websearch = new Searcheng();
					MultiDivAdd ans =websearch.search(s1);
					lblSearchTermFound.setText("Found ");
					lblNewLabel.setText("results in"); 
					textArea.setText(null);
					lblSearchTermFound.setText(lblSearchTermFound.getText() + ans.pth);
					lblNewLabel.setText(lblNewLabel.getText() + ans.srctime +"ms");
					textArea.append("Files: \n\n");
					textArea.append(MultiDivAdd.Filedata1);//.replace("null",  "")
					if(MultiDivAdd.Filedata2!=null) {
					textArea.setText(null);
					textArea.append("Did you mean? \n\n");
					textArea.append(MultiDivAdd.Filedata2.replace("null",  ""));
					}
					if(ans.rFiles!=null) {
					textArea_1.setText(null);
					textArea_1.append("Top ranked pages\n\n");
					textArea_1.append(ans.rFiles.replace("null",  ""));//.replace("null",  "")
					//textArea_1.append("\nRanking Algorithm time:"+ans.rankingTme+" ns");
					}
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 0;
		frmSearchme.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					lblSearchTermFound.setText("Found ");
					lblNewLabel.setText("results Found in"); 
					textArea.setText(null);
					String s1=textField.getText();
					Searcheng websearch = new Searcheng();
					MultiDivAdd ans =websearch.search(s1);
					MultiDivAdd.Filedata1=null;
					textArea_1.setText(null);
					textField.setText(null);
			}
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 5);
		gbc_btnClear.gridx = 6;
		gbc_btnClear.gridy = 0;
		frmSearchme.getContentPane().add(btnClear, gbc_btnClear);
		
		lblSearchTermFound = new JLabel("Found ");
		lblSearchTermFound.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearchTermFound.setForeground(new Color(240, 230, 140));
		GridBagConstraints gbc_lblSearchTermFound = new GridBagConstraints();
		gbc_lblSearchTermFound.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearchTermFound.gridx = 0;
		gbc_lblSearchTermFound.gridy = 1;
		frmSearchme.getContentPane().add(lblSearchTermFound, gbc_lblSearchTermFound);
		
		lblNewLabel = new JLabel("results Found in");
		lblNewLabel.setForeground(new Color(240, 230, 140));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		frmSearchme.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.gridheight = 2;
		gbc_textArea.gridwidth = 3;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 2;
		JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.add(scroll);
		frmSearchme.getContentPane().add(textArea, gbc_textArea);
		
		textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.gridwidth = 5;
		gbc_textArea_1.gridheight = 2;
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 3;
		gbc_textArea_1.gridy = 2;
		frmSearchme.getContentPane().add(textArea_1, gbc_textArea_1);
		frmSearchme.setBounds(100, 100, 450, 300);
		frmSearchme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
