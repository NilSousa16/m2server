package br.ufba.dcc.wiser.m2server.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;

import br.ufba.dcc.wiser.m2db.service.GatewayServiceDB;
import br.ufba.dcc.wiser.m2model.model.Gateway;
import br.ufba.dcc.wiser.m2server.service.GatewayService;

public class GatewayServiceImpl implements GatewayService {

	GatewayServiceDB gatewayServiceDB;

	public GatewayServiceDB getGatewayServiceDB() {
		return gatewayServiceDB;
	}

	public void setGatewayServiceDB(GatewayServiceDB gatewayServiceDB) {
		this.gatewayServiceDB = gatewayServiceDB;
	}

	@Override
	public Gateway add(Gateway gateway) throws Exception {

		try {
			gateway.setDate(Calendar.getInstance());
			gatewayServiceDB.add(gateway);

			return gateway;
		}catch (PersistenceException e) {
			throw new Exception(e);
		}
	}

	@Override
	public Gateway update(Gateway gateway) throws Exception {

		try {
			if(this.find(gateway.getMac()) != null) {
				gateway.setDate(Calendar.getInstance());
				gatewayServiceDB.update(gateway);
			}
			else
				throw new Exception("Record not found for update");
				
			return gateway;
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public void delete(String mac) throws Exception {
		
		try {
			gatewayServiceDB.delete(mac);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public Gateway find(String mac) throws Exception {
		
		try {
			return gatewayServiceDB.find(mac);
		}catch (Exception e) {
			throw new Exception(e);
		}		
	}

	@Override
	public List<Gateway> getList() throws Exception {
		
		try {
			return gatewayServiceDB.list();
		}catch (Exception e) {
			throw new Exception(e);
		}	
	}
}
