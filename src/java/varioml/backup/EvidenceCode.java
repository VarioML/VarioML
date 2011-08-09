package org.varioml.data;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;

public class EvidenceCode  extends DbXref { 
	
	@ElementList
	private List<Score> score ;

	public List<Score> getScore() {
		return score;
	}

	public void setScore(List<Score> score) {
		this.score = score;
	} 
	
	public void addScore(Score score) {
		if (score == null) {
			this.score = new ArrayList<Score>();
		}
		this.score.add(score);
	}
}
