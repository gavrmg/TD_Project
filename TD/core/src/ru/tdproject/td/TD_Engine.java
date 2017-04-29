package ru.tdproject.td;
public class TD_Engine implements Runnable {
	private TestLogic pr_Logic =null;
	private String Name= null;
	public TD_Engine(TestLogic pr_Logic, String name) {
		super();
		this.pr_Logic = pr_Logic;
		this.Name = name;
	}
	public void run(){
		while (true){
			pr_Logic.move();
			try {
				Thread.sleep(200);
			}
			catch(InterruptedException e){
				System.out.println("Thread crashed");
			}
		}
	}
}
