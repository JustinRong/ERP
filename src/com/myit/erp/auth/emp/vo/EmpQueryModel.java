package com.myit.erp.auth.emp.vo;

import com.myit.erp.util.base.BaseQueryModel;
import com.myit.erp.util.format.FormatUtil;

public class EmpQueryModel extends EmpModel implements BaseQueryModel {
	//׷��������Ϊ��ѯ���������ֵ�ֶ�
		private Long birthday2;
		private String birthday2View;
		
		public String getBirthday2View() {
			return birthday2View;
		}
		public Long getBirthday2() {
			return birthday2;
		}
		public void setBirthday2(Long birthday2) {
			this.birthday2 = birthday2;
			this.birthday2View = FormatUtil.formatDate(birthday2);
		}
}
