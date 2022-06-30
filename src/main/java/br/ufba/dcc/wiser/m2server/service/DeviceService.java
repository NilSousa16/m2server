package br.ufba.dcc.wiser.m2server.service;

import java.util.List;

import br.ufba.dcc.wiser.m2model.model.Device;

public interface DeviceService {
	
	public Device add(Device device) throws Exception;

	public Device update(Device device) throws Exception;
	
	public void delete(String mac) throws Exception;

	public Device find(String mac) throws Exception;

	public List<Device> getList() throws Exception;

}
