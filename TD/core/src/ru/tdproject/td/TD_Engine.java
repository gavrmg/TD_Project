package ru.tdproject.td;
public class TD_Engine implements Runnable {
	private World _world = null;
	private String Name = null;
	public TD_Engine(World world, String name) {
		super();
		this._world = world;
		this.Name = name;
	}
	public void run(){
		while (true){
			_world.move();
			try {
				Thread.sleep(200);
			}
			catch(InterruptedException e){
				System.out.println("Thread crashed");
			}
		}
	}
}
