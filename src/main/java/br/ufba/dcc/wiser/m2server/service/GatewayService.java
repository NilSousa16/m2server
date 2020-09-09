package br.ufba.dcc.wiser.m2server.service;

import java.util.List;

import br.ufba.dcc.wiser.m2model.model.Gateway;

public interface GatewayService {

	public Gateway add(Gateway gateway) throws Exception;

	public Gateway update(Gateway gateway) throws Exception;
	
	public void delete(String mac) throws Exception;

	public Gateway find(String mac) throws Exception;

	public List<Gateway> getList() throws Exception;
	
}
