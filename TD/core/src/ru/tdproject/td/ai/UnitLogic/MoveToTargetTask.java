package ru.tdproject.td.ai.UnitLogic;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

import ru.tdproject.td.Objects.Unit;

public class MoveToTargetTask extends LeafTask<Unit> {

	@Override
	public com.badlogic.gdx.ai.btree.Task.Status execute() {
		getObject().getSeek().calculateSteering(getObject().getSeekOutput());
		getObject().getBody().setLinearVelocity(getObject().getSeekOutput().linear);
		return Status.SUCCEEDED;
	}

	@Override
	protected Task<Unit> copyTo(Task<Unit> task) {
		// TODO Auto-generated method stub
		return task;
	}

}
