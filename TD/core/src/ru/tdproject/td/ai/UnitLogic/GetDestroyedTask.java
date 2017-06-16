package ru.tdproject.td.ai.UnitLogic;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

import ru.tdproject.td.Objects.Unit;

public class GetDestroyedTask extends LeafTask<Unit> {

	private float AccumulatedTime;
	public void start(){
		AccumulatedTime = 0;
	}
	@Override
	public com.badlogic.gdx.ai.btree.Task.Status execute() {
		AccumulatedTime+=GdxAI.getTimepiece().getDeltaTime();
		System.out.println(AccumulatedTime);
		if (AccumulatedTime < 2){
			return Status.RUNNING;
		}
		else {
			getObject().setToDispose(true);
			return Status.SUCCEEDED;
		}
	}
	
	public void end(){
		
	}

	@Override
	protected Task<Unit> copyTo(Task<Unit> task) {
		((GetDestroyedTask)task).AccumulatedTime = AccumulatedTime;
		return task;
	}

}
