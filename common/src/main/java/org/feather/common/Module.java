package org.feather.common;

public interface Module {

	public boolean init(boolean isReload);

	public void afterCreate(Object[] params);

	public void start(boolean isReload);

	public void stop();

	public boolean isAlive();

}
