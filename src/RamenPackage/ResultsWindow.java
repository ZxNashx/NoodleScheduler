package RamenPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Canvas;

public class ResultsWindow extends JFrame {
	private JPanel contentPane;
	public static void open(PoodleScheduler ns) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultsWindow frame = new ResultsWindow(ns);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ResultsWindow(PoodleScheduler ns) {
		setTitle("Results Window");
		setBounds(100, 100, 450, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JList taskList = new JList();
		GridBagConstraints gbc_taskList = new GridBagConstraints();
		gbc_taskList.gridheight = 6;
		gbc_taskList.insets = new Insets(0, 0, 5, 5);
		gbc_taskList.fill = GridBagConstraints.BOTH;
		gbc_taskList.gridx = 1;
		gbc_taskList.gridy = 1;
		contentPane.add(taskList, gbc_taskList);
		
		JLabel lblNewLabel = new JLabel("Timer:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 7;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(100);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 7;
		contentPane.add(progressBar, gbc_progressBar);
		
		JLabel TimeRemaining = new JLabel("TIME REMAINING");
		GridBagConstraints gbc_TimeRemaining = new GridBagConstraints();
		gbc_TimeRemaining.insets = new Insets(0, 0, 5, 0);
		gbc_TimeRemaining.gridx = 2;
		gbc_TimeRemaining.gridy = 7;
		contentPane.add(TimeRemaining, gbc_TimeRemaining);
		
	    int delay = 1000; //milliseconds
	      ActionListener taskPerformer = new ActionListener() {
	          public void actionPerformed(ActionEvent evt) {
	              progressBar.setValue(ns.getTimer());
	              taskList.setModel(ns.getList());
	              TimeRemaining.setText(String.valueOf(ns.getTimeRemaining()) + " Minutes Left");
	          }
	      };
	      new Timer(delay, taskPerformer).start();
	}

}
