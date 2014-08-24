package com.StaticVoidGames.spring.dao;

import java.util.List;

import com.StaticVoidGames.members.Member;

public interface MemberDao {
	public int getMemberCount();
	public List<Member> getAllMembers();
	public Member getMember(String memberName);
	public void updateMember(Member member);
	public int getPoints(String member);
}
