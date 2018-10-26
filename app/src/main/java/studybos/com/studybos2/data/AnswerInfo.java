package studybos.com.studybos2.data;


public class AnswerInfo {
	private int problemHash;
	private String answerId;
	private String answerTime;
	private String answer;
	private byte[] answerImg;
	
	public int getProblemHash() {
		return problemHash;
	}
	public void setProblemHash(int problemHash) {
		this.problemHash = problemHash;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public byte[] getAnswerImg() {
		return answerImg;
	}

	public void setAnswerImg(byte[] answerImg) {
		this.answerImg = answerImg;
	}
}
