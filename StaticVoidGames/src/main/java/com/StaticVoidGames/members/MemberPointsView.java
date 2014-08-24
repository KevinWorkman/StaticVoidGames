package com.StaticVoidGames.members;

/**
 * Class encapsulating information necessary to show a member and their points.
 */
public class MemberPointsView {
	private Member member;
	private int points;
	
	public MemberPointsView(Member member, int points){
		this.member = member;
		this.points = points;
	}
	
	public Member getMember(){
		return member;
	}
	
	public int getPoints(){
		return points;
	}
}
