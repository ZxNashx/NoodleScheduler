package RamenPackage;

public class NoodleTask {
	String name;
	int hour;
	int min;
	
	public NoodleTask(String name, int hr, int min) {
		this.name = name;
		this.hour = hr;
		this.min = min;
	}
	public int getTime() {
		return this.min * 60 + this.hour * 60 * 60;
	}
	public String getReadTime() {
		return String.format("%02d:%02d", this.hour, this.min);
	}
	public String getName() {
		return name;
	}
}
