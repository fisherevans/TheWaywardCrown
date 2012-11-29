package com.fisherevans.twc.rpg;

public class CombatEntity
{
	private float _statHP,  _statSTA, _statSTR,
				  _statINT, _statDEX, _statEND,
				  _statWIL, _statREG, _statDEB,
				  _statHPMod,  _statSTAMod, _statSTRMod,
				  _statINTMod, _statDEXMod, _statENDMod,
				  _statWILMod, _statREGMod, _statDEBMod;

	public float getHP() { return _statHP + _statHPMod; }
	public float getSTA() { return _statSTA + _statHPMod; }
	public float getSTR() { return _statSTR + _statSTAMod; }
	public float getINT() { return _statINT + _statSTRMod; }
	public float getDEX() { return _statDEX + _statINTMod; }
	public float getEND() { return _statEND + _statDEXMod; }
	public float getWIL() { return _statWIL + _statDEXMod; }
	public float getREG() { return _statREG + _statENDMod; }
	public float getDEB() { return _statDEB + _statDEBMod; }
}
