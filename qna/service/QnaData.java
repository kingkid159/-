package qna.service;

import qna.model.QnaModel;

public class QnaData {
	private QnaModel qTitle;
	private QnaModel qContent;
	public QnaData(QnaModel qTitle, QnaModel qContent) {
		System.out.println("qTitle="+qTitle);
		this.qTitle = qTitle;
		this.qContent=qContent;
	}
	public QnaModel getqTitle() {
		return qTitle;
	}
	public QnaModel getQContent() {
		return qContent;
	}
}
