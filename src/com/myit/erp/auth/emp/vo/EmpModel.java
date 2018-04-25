package com.myit.erp.auth.emp.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.myit.erp.auth.dep.vo.DepModel;
import com.myit.erp.auth.role.vo.RoleModel;
import com.myit.erp.util.format.FormatUtil;

public class EmpModel {
	
public static final String EMP_LOGIN_USER_OBJECT_NAME = "loginEm";
	
	//���ݽṹ˼��Ӧ��
	public static final Integer EMP_GENDER_OF_MAN = 1;
	public static final Integer EMP_GENDER_OF_WOMAN = 0;
	
	public static final String EMP_GENDER_OF_MAN_VIEW = "��";
	public static final String EMP_GENDER_OF_WOMAN_VIEW = "Ů";
	
	public static final Map<Integer, String> genderMap = new HashMap<Integer, String>();
	
	static{
		genderMap.put(EMP_GENDER_OF_MAN, EMP_GENDER_OF_MAN_VIEW);
		genderMap.put(EMP_GENDER_OF_WOMAN, EMP_GENDER_OF_WOMAN_VIEW);
	}
	
	private Long uuid;
	private String userName;
	private String name;
	private String pwd;
	private String email;
	private String tele;
	private String address;
	private String lastLoginIp;
	private Integer loginTimes;
	
	private Long lastLoginTime;
	
	private Integer gender;
	/*
	//Long:��¼���Ǻ���ֵ	
	//Date:��long�İ�װ	�ŵ㣺��ʽ�ã�ȱ�㣺����ʱ�����и�����
	���ڵ�ʱ����2020��4��31��
	180��ǰ�Ǽ��ţ�
	���ڵ�long System.currentTimeMillis()-180*24*60*60*1000
	long-long >0
	Date  2014��1��4��  14��21
	Date  2014��1��4��  14��22	
	*/
	private Long birthday;
	
	//��ͼֵ����ͼֵ��һ�����ڽ�����ʾ�ı���ֵ����ֵ�������Ӧĳ�����ݿ��ֶΣ���������ĳ�����ݿ��ֶ�
	//�����ݿ��е�ĳ���ֶ�ֵ������ֱ����ʾʱ��Ϊ���ֶ������ͼֵ��������ʾ��Ӧ����Ϣ
	//1.����һ��String���͵ı��������������޷�������ʾ���ֶε��ֶ���+View
	//2.�ṩ��get����
	//3.�����Ӧ�ı�����set�����ж����Viewֵ���г�ʼ��
	private String birthdayView;
	private String genderView;
	private String lastLoginTimeView;
	
	//����ֵ
	private String resAll;
	
	
	//Ա���벿�Ŷ��һ
	private  DepModel  dm;
	
	//Ա�����ɫ ��Զ�
	private  Set<RoleModel> roles;
	
	
	
	public DepModel getDm() {
		return dm;
	}
	public void setDm(DepModel dm) {
		this.dm = dm;
	}
	
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
	public String getResAll() {
		return resAll;
	}
	public void setResAll(String resAll) {
		this.resAll = resAll;
	}
	public String getLastLoginTimeView() {
		return lastLoginTimeView;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Integer getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	public Long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		this.lastLoginTimeView = FormatUtil.formatDate(lastLoginTime);
	}
	public String getGenderView() {
		return genderView;
	}
	public String getBirthdayView() {
		return birthdayView;
	}
	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
		this.genderView = genderMap.get(gender);
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
		this.birthdayView = FormatUtil.formatDate(birthday);
	}

	@Override
	public String toString() {
		return "EmpModel [uuid=" + uuid + ", userName=" + userName + ", name=" + name + ", pwd=" + pwd + ", email="
				+ email + ", tele=" + tele + ", address=" + address + ", lastLoginIp=" + lastLoginIp + ", loginTimes="
				+ loginTimes + ", lastLoginTime=" + lastLoginTime + ", gender=" + gender + ", birthday=" + birthday
				+ ", birthdayView=" + birthdayView + ", genderView=" + genderView + ", lastLoginTimeView="
				+ lastLoginTimeView + ", resAll=" + resAll + ", dm=" + dm + ", roles=" + roles + "]";
	}

}
