package RamenPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;

public class PoodleScheduler {
	private float startTime;
	private float sum_time;
	private ArrayList<NoodleTask> taskList;
	
	public PoodleScheduler(){
		startTime = getUnix();
	}
	
	public void sendList(ArrayList<NoodleTask> tasks) {
		Collections.sort(tasks, Comparator.comparing(NoodleTask::getTime));
		Collections.reverse(tasks);
		
		taskList = new ArrayList<NoodleTask>();
		int running_time = 0;
		int break_hr = 2; // break every n hours
		// add breaks
		for(int i = 0; i < tasks.size(); i++) {
			running_time += tasks.get(i).getTime();
			taskList.add(tasks.get(i));
			if(i == 1) {
				NoodleTask temp_break = new NoodleTask(
						"BREAK", 0, 15);
				taskList.add(temp_break);
				running_time = 0;
			}else {
				if(running_time > 60 * 60 * break_hr) {
					NoodleTask temp_break = new NoodleTask(
							"BREAK", 0, 30);
					taskList.add(temp_break);
					running_time = 0;
				}
			}
		}
		
		sum_time = 0;
		for(int i = 0; i < taskList.size(); i++) {
			sum_time += taskList.get(i).getTime();
		}
	}
	
	private float getUnix() {
		return (float)((long)(System.currentTimeMillis() / 1000L) % 10000); // MAX 1 WEEK
	}
	
	public int getTimer() {
		float time_return = (float) ((getElapsedTime() / (float)sum_time) * 100);
		return 100 - (int)time_return;
	}
	
	private float getElapsedTime() {
		return (float)getUnix() - (float)startTime;
	}
	
	public DefaultListModel getList() {
		DefaultListModel temp_tasks = new DefaultListModel();
		int delta = (int) getElapsedTime();
		int running_total = 0;
		boolean found_first = true;
		for(int i = 0; i < taskList.size(); i++) {
			running_total += taskList.get(i).getTime();
			if(running_total < delta) {
				temp_tasks.addElement(taskList.get(i).getName() + " (Done)");
			}else {
				if(found_first) {
					found_first = false;
					temp_tasks.addElement(taskList.get(i).getName() + " (In Progress: " + taskList.get(i).getReadTime() + ")");
				}else {
					temp_tasks.addElement(taskList.get(i).getName());
				}
			}
		}
		return temp_tasks;
	}
	public int getTimeRemaining() {
		return (int) (sum_time - getElapsedTime())/60;
	}
}
