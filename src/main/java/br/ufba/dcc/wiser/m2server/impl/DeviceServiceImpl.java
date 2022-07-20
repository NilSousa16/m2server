package br.ufba.dcc.wiser.m2server.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;

import br.ufba.dcc.wiser.m2db.service.DeviceServiceDB;
import br.ufba.dcc.wiser.m2model.model.Device;
import br.ufba.dcc.wiser.m2server.service.DeviceService;

public class DeviceServiceImpl implements DeviceService {

	DeviceServiceDB deviceServiceDB;

	public DeviceServiceDB getDeviceServiceDB() {
		return deviceServiceDB;
	}

	public void setDeviceServiceDB(DeviceServiceDB deviceServiceDB) {
		this.deviceServiceDB = deviceServiceDB;
	}

	@Override
	public Device add(Device device) throws Exception {
		try {
			device.setDate(Calendar.getInstance());
			deviceServiceDB.add(device);

			return device;
		}catch (PersistenceException e) {
			throw new Exception(e);
		}
	}

	@Override
	public Device update(Device device) throws Exception {
		try {
			if(this.find(device.getId()) != null) {
				device.setDate(Calendar.getInstance());
				deviceServiceDB.update(device);
			}
			else
				throw new Exception("Device record not found for update");
				
			return device;
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public void delete(String id) throws Exception {
		try {
			deviceServiceDB.delete(id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public Device find(String id) throws Exception {
		try {
			return deviceServiceDB.find(id);
		}catch (Exception e) {
			throw new Exception(e);
		}		
	}

	@Override
	public List<Device> getList() throws Exception {
		try {
			return deviceServiceDB.list();
		}catch (Exception e) {
			throw new Exception(e);
		}	
	}

}
