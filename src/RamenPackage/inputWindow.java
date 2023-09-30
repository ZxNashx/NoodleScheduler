package RamenPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;

public class inputWindow extends JFrame {

	private JPanel contentPane;
	DefaultListModel taskList = new DefaultListModel();
	static ArrayList<NoodleTask> realTaskList;
	private PoodleScheduler ns = new PoodleScheduler();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		realTaskList = new ArrayList<NoodleTask>();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inputWindow frame = new inputWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public inputWindow() {
		setTitle("DA NOODLE SCHEDULER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{212, 225, 212, 0, 0};
		gbl_contentPane.rowHeights = new int[]{38, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Task Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JTextArea taskNameTextBox = new JTextArea();
		GridBagConstraints gbc_taskNameTextBox = new GridBagConstraints();
		gbc_taskNameTextBox.insets = new Insets(0, 0, 5, 5);
		gbc_taskNameTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskNameTextBox.gridx = 1;
		gbc_taskNameTextBox.gridy = 0;
		contentPane.add(taskNameTextBox, gbc_taskNameTextBox);
		
		JList<Float> taskRecord = new JList<Float>();
		taskRecord.setLayoutOrientation(JList.VERTICAL_WRAP);
		taskRecord.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_taskRecord = new GridBagConstraints();
		gbc_taskRecord.insets = new Insets(0, 0, 0, 5);
		gbc_taskRecord.gridheight = 5;
		gbc_taskRecord.fill = GridBagConstraints.BOTH;
		gbc_taskRecord.gridx = 2;
		gbc_taskRecord.gridy = 0;
		contentPane.add(taskRecord, gbc_taskRecord);
		
		JLabel lblNewLabel_1 = new JLabel("Task Length (hh:mm)");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JTextArea taskLengthTextBox = new JTextArea();
		GridBagConstraints gbc_taskLengthTextBox = new GridBagConstraints();
		gbc_taskLengthTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskLengthTextBox.insets = new Insets(0, 0, 5, 5);
		gbc_taskLengthTextBox.gridx = 1;
		gbc_taskLengthTextBox.gridy = 1;
		contentPane.add(taskLengthTextBox, gbc_taskLengthTextBox);
		
		JLabel lblNewLabel_2 = new JLabel("TOTAL TIME (hh:mm)");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JButton submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// GENERATE SCHEDULE WINDOW
				ns.sendList(realTaskList);
				ResultsWindow result = new ResultsWindow(ns);
				result.open(ns);
				
			}
		});
		
		JLabel errorLabel = new JLabel(""); // moved label declartaion
		JButton addToSchBtn = new JButton("Add To Schedule");
		addToSchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ADD TO SCHEDULE
				int temp_hr;
				int temp_min;
				
				if(taskLengthTextBox.getText().contains(":")) {
					taskList.addElement(taskNameTextBox.getText());
					taskRecord.setModel(taskList); // placeholder model
					temp_hr = Integer.parseInt(
							taskLengthTextBox.getText().split(":")[0]);
					temp_min = Integer.parseInt(
							taskLengthTextBox.getText().split(":")[1]);
					NoodleTask tempTask = new NoodleTask(
							taskNameTextBox.getText(), temp_hr, temp_min);
					realTaskList.add(tempTask);
					
					taskNameTextBox.setText("");
					taskLengthTextBox.setText("");
					errorLabel.setText("");
				}else{
					errorLabel.setText("Time must contain a \":\"");
				}

			}
		});
		
		JTextArea totalTimeTextBox = new JTextArea();
		GridBagConstraints gbc_totalTimeTextBox = new GridBagConstraints();
		gbc_totalTimeTextBox.insets = new Insets(0, 0, 5, 5);
		gbc_totalTimeTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalTimeTextBox.gridx = 1;
		gbc_totalTimeTextBox.gridy = 2;
		contentPane.add(totalTimeTextBox, gbc_totalTimeTextBox);
		
		errorLabel.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 3;
		contentPane.add(errorLabel, gbc_errorLabel);
		
		GridBagConstraints gbc_addToSchBtn = new GridBagConstraints();
		gbc_addToSchBtn.insets = new Insets(0, 0, 5, 5);
		gbc_addToSchBtn.gridx = 1;
		gbc_addToSchBtn.gridy = 3;
		contentPane.add(addToSchBtn, gbc_addToSchBtn);
		GridBagConstraints gbc_submitButton = new GridBagConstraints();
		gbc_submitButton.insets = new Insets(0, 0, 0, 5);
		gbc_submitButton.gridx = 1;
		gbc_submitButton.gridy = 4;
		contentPane.add(submitButton, gbc_submitButton);
	}

}
