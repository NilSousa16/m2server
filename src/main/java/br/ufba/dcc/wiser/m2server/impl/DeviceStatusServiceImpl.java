package br.ufba.dcc.wiser.m2server.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import br.ufba.dcc.wiser.m2db.service.DeviceStatusServiceDB;
import br.ufba.dcc.wiser.m2model.model.DeviceStatus;
import br.ufba.dcc.wiser.m2server.service.DeviceStatusService;

public class DeviceStatusServiceImpl implements DeviceStatusService {

	DeviceStatusServiceDB deviceStatusServiceDB;

	public DeviceStatusServiceDB getDeviceStatusServiceDB() {
		return deviceStatusServiceDB;
	}

	public void setDeviceStatusServiceDB(DeviceStatusServiceDB deviceStatusServiceDB) {
		this.deviceStatusServiceDB = deviceStatusServiceDB;
	}

	@Override
	public DeviceStatus addDevice(DeviceStatus deviceStatus) throws Exception {
		try {
			deviceStatusServiceDB.add(deviceStatus);

			return deviceStatus;
		} catch (PersistenceException e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<DeviceStatus> findById(String id) throws Exception {
		try {
			return deviceStatusServiceDB.findById(id);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@Override
	public List<DeviceStatus> findByGateway(String gatewayMac) throws Exception {
		try {
			return deviceStatusServiceDB.findByGateway(gatewayMac);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<DeviceStatus> getListAllStatus() throws Exception {
		try {
			return deviceStatusServiceDB.getListAll();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
