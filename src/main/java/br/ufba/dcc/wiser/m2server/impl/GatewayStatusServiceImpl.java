package br.ufba.dcc.wiser.m2server.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import br.ufba.dcc.wiser.m2db.service.GatewayStatusServiceDB;
import br.ufba.dcc.wiser.m2model.model.GatewayStatus;
import br.ufba.dcc.wiser.m2server.service.GatewayStatusService;

public class GatewayStatusServiceImpl implements GatewayStatusService {

	GatewayStatusServiceDB gatewayStatusServiceDB;

	public GatewayStatusServiceDB getGatewayStatusServiceDB() {
		return gatewayStatusServiceDB;
	}

	public void setGatewayStatusServiceDB(GatewayStatusServiceDB gatewayStatusServiceDB) {
		this.gatewayStatusServiceDB = gatewayStatusServiceDB;
	}

	@Override
	public GatewayStatus add(GatewayStatus gatewayStatus) throws Exception {
		try {
			// gatewayStatusServiceDB.add(gatewayStatus);
			// não será verificado existência do gateway antes da inserção
			// retornar erro se não conseguir inserir
			return gatewayStatus;
		} catch (PersistenceException e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<GatewayStatus> findByMac(String mac) throws Exception {
		try {
			return gatewayStatusServiceDB.findByMac(mac);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<GatewayStatus> getListAll() throws Exception {
		try {
			return gatewayStatusServiceDB.getListAll();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
