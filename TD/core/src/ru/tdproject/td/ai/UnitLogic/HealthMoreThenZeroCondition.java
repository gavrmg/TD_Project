package ru.tdproject.td.ai.UnitLogic;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

import ru.tdproject.td.Objects.Unit;

public class HealthMoreThenZeroCondition extends LeafTask<Unit> {

	@Override
	public com.badlogic.gdx.ai.btree.Task.Status execute() {
		return getObject().getHealth()>0 ? Status.SUCCEEDED : Status.FAILED;
	}

	@Override
	protected Task<Unit> copyTo(Task<Unit> task) {
		// TODO Auto-generated method stub
		return this;
	}

}
