package com.deodio.core.thread.pool;

import com.deodio.core.command.ICommand;

public class SFileThread implements Runnable{

	private ICommand command;
	
	public ICommand getCommand() {
		return command;
	}

	public void setCommand(ICommand command) {
		this.command = command;
	}

	@Override
	public void run() {
		command.command();
	}

}
