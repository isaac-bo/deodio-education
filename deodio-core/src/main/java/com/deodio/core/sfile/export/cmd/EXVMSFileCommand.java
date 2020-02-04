package com.deodio.core.sfile.export.cmd;

import java.util.Map;

import org.apache.velocity.app.VelocityEngine;

import com.deodio.core.command.ICommand;

public abstract class EXVMSFileCommand implements ICommand{

	protected VelocityEngine velocityEngine;

	protected Map<String,Object> params;

	public void setParames(Map<String, Object> params) {
		this.params = params;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	

}
