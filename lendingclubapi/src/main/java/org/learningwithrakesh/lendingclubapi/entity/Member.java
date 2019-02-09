package org.learningwithrakesh.lendingclubapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity()
public class Member {
//[member_id, loan_amnt, funded_amnt_inv, term, int_rate, installment,
//	grade, emp_title, emp_length, home_ownership, annual_inc, verification_status, 
//	issue_d, loan_status, desc, purpose, title, addr_state, last_pymnt_d, 
//	last_pymnt_amnt]
	@Id
	@Column(name = "member_id")
	private long id;

	@Column(name = "loan_amnt")
	private double loadAmnt;

	@Column(name = "funded_amnt_inv")
	private double fundedAmntInv;

	private String term;

	@Column(name = "int_rate")
	private double intRate;

	private double installment;

	@Column(length=1)
	private String grade;

	@Column(name = "emp_title")
	private String empTitle;

	@Column(name = "emp_length")
	private String empLength;

	@Column(name = "home_ownership")
	private String homeOwnership;

	@Column(name = "annual_inc")
	private double annualInc;

	@Column(name = "verification_status")
	private String verificationStatus;

	@Column(name = "issue_d")
	private String issueDate;

	@Column(name = "loan_status")
	private String loadStatus;

	@Column(name = "`desc`", length=5000)

	private String description;
	private String purpose;

	private String title;

	@Column(name = "addr_state")
	private String addrState;

	@Column(name = "last_pymnt_d")
	private String lastPymntDate;

	@Column(name = "last_pymnt_amnt")
	private double lastPymntAmnt;

	public Member() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getLoadAmnt() {
		return loadAmnt;
	}

	public void setLoadAmnt(double loadAmnt) {
		this.loadAmnt = loadAmnt;
	}

	public double getFundedAmntInv() {
		return fundedAmntInv;
	}

	public void setFundedAmntInv(double fundedAmntInv) {
		this.fundedAmntInv = fundedAmntInv;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public double getIntRate() {
		return intRate;
	}

	public void setIntRate(double intRate) {
		this.intRate = intRate;
	}

	public double getInstallment() {
		return installment;
	}

	public void setInstallment(double installment) {
		this.installment = installment;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmpTitle() {
		return empTitle;
	}

	public void setEmpTitle(String empTitle) {
		this.empTitle = empTitle;
	}

	public String getEmpLength() {
		return empLength;
	}

	public void setEmpLength(String empLength) {
		this.empLength = empLength;
	}

	public String getHomeOwnership() {
		return homeOwnership;
	}

	public void setHomeOwnership(String homeOwnership) {
		this.homeOwnership = homeOwnership;
	}

	public double getAnnualInc() {
		return annualInc;
	}

	public void setAnnualInc(double annualInc) {
		this.annualInc = annualInc;
	}

	public String getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getLoadStatus() {
		return loadStatus;
	}

	public void setLoadStatus(String loadStatus) {
		this.loadStatus = loadStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddrState() {
		return addrState;
	}

	public void setAddrState(String addrState) {
		this.addrState = addrState;
	}

	public String getLastPymntDate() {
		return lastPymntDate;
	}

	public void setLastPymntDate(String lastPymntDate) {
		this.lastPymntDate = lastPymntDate;
	}

	public double getLastPymntAmnt() {
		return lastPymntAmnt;
	}

	public void setLastPymntAmnt(double lastPymntAmnt) {
		this.lastPymntAmnt = lastPymntAmnt;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", loadAmnt=" + loadAmnt + ", fundedAmntInv=" + fundedAmntInv + ", term=" + term
				+ ", intRate=" + intRate + ", installment=" + installment + ", grade=" + grade + ", empTitle="
				+ empTitle + ", empLength=" + empLength + ", homeOwnership=" + homeOwnership + ", annualInc="
				+ annualInc + ", verificationStatus=" + verificationStatus + ", issueDate=" + issueDate
				+ ", loadStatus=" + loadStatus + ", description=" + description + ", purpose=" + purpose + ", title="
				+ title + ", addrState=" + addrState + ", lastPymntDate=" + lastPymntDate + ", lastPymntAmnt="
				+ lastPymntAmnt + "]";
	}

}
