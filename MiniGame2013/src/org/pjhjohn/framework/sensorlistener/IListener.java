package org.pjhjohn.framework.sensorlistener;


// Controller Interface
public interface IListener {
	void init();									// initialize when controller has been registered
	void destroy();									// destroy when controller has been removed
}
