package question.service;

import question.model.Question;

public class QuestionData {
	private Question question;
	private Question question1;
	public QuestionData(Question question, Question question1) {
		this.question=question;
		this.question1=question1;
	}
	public Question getQuestion() {
		return question;
	}
	public String getContent() {
		return question1.getContent();
	}
}
