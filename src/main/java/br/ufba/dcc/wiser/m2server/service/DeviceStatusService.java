package br.ufba.dcc.wiser.m2server.service;

import java.util.List;

import br.ufba.dcc.wiser.m2model.model.DeviceStatus;

public interface DeviceStatusService {

	public DeviceStatus addDevice(DeviceStatus deviceStatus) throws Exception;

	public List<DeviceStatus> findById(String mac) throws Exception;

	public List<DeviceStatus> getListAllStatus() throws Exception;

}
