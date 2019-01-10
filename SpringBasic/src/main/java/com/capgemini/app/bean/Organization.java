package com.capgemini.app.bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Organization {
	private int orgId;
	private String name;
	private Set<String> cities;
	private List<String> boardMembers;
	private Map<String, String> branchManagers;
	private LocalDate dateOfEstablishment;
	private double shareValue;
	private boolean listed;
	private Properties ipAddresses;
	public Organization() {
		super();
	}
	public Organization(int orgId, String name) {
		super();
		this.orgId = orgId;
		this.name = name;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getCities() {
		return cities;
	}
	public void setCities(Set<String> cities) {
		this.cities = cities;
	}
	public List<String> getBoardMembers() {
		return boardMembers;
	}
	public void setBoardMembers(List<String> boardMembers) {
		this.boardMembers = boardMembers;
	}
	public Map<String, String> getBranchManagers() {
		return branchManagers;
	}
	public void setBranchManagers(Map<String, String> branchManagers) {
		this.branchManagers = branchManagers;
	}
	public LocalDate getDateOfEstablishment() {
		return dateOfEstablishment;
	}
	public void setDateOfEstablishment(LocalDate dateOfEstablishment) {
		this.dateOfEstablishment = dateOfEstablishment;
	}
	public double getShareValue() {
		return shareValue;
	}
	public void setShareValue(double shareValue) {
		this.shareValue = shareValue;
	}
	public boolean isListed() {
		return listed;
	}
	public void setListed(boolean listed) {
		this.listed = listed;
	}
	public Properties getIpAddresses() {
		return ipAddresses;
	}
	public void setIpAddresses(Properties ipAddresses) {
		this.ipAddresses = ipAddresses;
	}
	@Override
	public String toString() {
		return "Organization [orgId=" + orgId + ", name=" + name + ", cities=" + cities + ", boardMembers="
				+ boardMembers + ", branchManagers=" + branchManagers + ", dateOfEstablishment=" + dateOfEstablishment
				+ ", shareValue=" + shareValue + ", listed=" + listed + ", ipAddresses=" + ipAddresses + "]";
	}
}