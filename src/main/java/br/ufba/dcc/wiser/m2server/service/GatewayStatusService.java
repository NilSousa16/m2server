package br.ufba.dcc.wiser.m2server.service;

import java.util.List;

import br.ufba.dcc.wiser.m2model.model.GatewayStatus;

// Future use

public interface GatewayStatusService {
	
	public GatewayStatus add(GatewayStatus gatewayStatus) throws Exception;
	
	public List<GatewayStatus> findByMac(String mac) throws Exception;

	public List<GatewayStatus> getListAll() throws Exception;

}
