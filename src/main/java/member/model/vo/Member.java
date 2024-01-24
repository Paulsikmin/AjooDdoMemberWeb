package member.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private char memberGender;
	private int memberAge;
	private String memberEmail;
	private String memberPhone;
	private String memberAddress;
	private String memberHobby;
	private Date memberDate;
	private Timestamp updateDate;
	private char memberYn;
	
	public Member() {}
	
	public Member(String memberId, String memberPw) {
		this.memberId = memberId;
		this.memberPw = memberPw;
	}
	
	public Member(String memberId, String memberPw, String memberName, char memberGender, int memberAge,
			String memberEmail, String memberPhone, String memberAddress, String memberHobby) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberGender = memberGender;
		this.memberAge = memberAge;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberAddress = memberAddress;
		this.memberHobby = memberHobby;
	}

	// 게터/세터
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public char getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}

	public int getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberHobby() {
		return memberHobby;
	}

	public void setMemberHobby(String memberHobby) {
		this.memberHobby = memberHobby;
	}

	public Date getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(Date memberDate) {
		this.memberDate = memberDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public char getMemberYn() {
		return memberYn;
	}

	public void setMemberYn(char memberYn) {
		this.memberYn = memberYn;
	}

	// toString 오버라이딩
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberGender=" + memberGender + ", memberAge=" + memberAge + ", memberEmail=" + memberEmail
				+ ", memberPhone=" + memberPhone + ", memberAddress=" + memberAddress + ", memberHobby=" + memberHobby
				+ ", memberDate=" + memberDate + ", updateDate=" + updateDate + ", memberYn=" + memberYn + "]";
		
	}
}
