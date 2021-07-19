package entity;
/**
 * 
 * @author Win Pa Pa Aung
 *
 */
public class MemberHolder {
	private Member member;
	
	private final static MemberHolder MEMBER_INSTANCE = new MemberHolder();

	
	public MemberHolder() {
		super();
		
	}
	public Member getMember() {
		return member;
	}
	public static MemberHolder getMemberInstance() {
		return MEMBER_INSTANCE;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
	
	
}
