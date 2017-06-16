package ru.tdproject.td.GameLogic;

public interface ActiveGameObject {
	public void Attack(ActiveGameObject Target); 
	
	public void TakeDamage(int Damage);
	
	public ActiveGameObject GetThisObject();
}
