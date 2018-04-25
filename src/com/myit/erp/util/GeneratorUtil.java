package com.myit.erp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.myit.erp.invoice.operdetail.vo.OperDetailModel;
import com.myit.erp.invoice.orderdetail.vo.OrderDetailModel;
import com.myit.erp.invoice.store.vo.StoreModel;
import com.myit.erp.invoice.storedetail.vo.StoreDetailModel;

public class GeneratorUtil {
	private Class clazz;
	private String b ;		//Emp
	private String l ;		//e
	private String s ;		//emp
	private String pkg ;	//com.myit.erp.auth.emp
	private String dir ;	//com/myit/erp/auth/emp/vo
	
	public static void main(String[] args) throws Exception {
		//EmpModel,RoleModel,ResModel,MenuModel
		//SupplierModel,GoodsTypeModel,GoodsModel
		//OrderModel,OrderDetailModel
		//StoreModel,StoreDetailModel,OperDetailModel
		new GeneratorUtil(OperDetailModel.class);
		System.out.println("struts.xmlδ����ӳ��");
		System.out.println("HbmXmlδ��ӹ�����ϵ");
		System.out.println("QueryModelδ����Զ��巶Χ��ѯ����");
		System.out.println("DaoImpl��δ���Զ����ѯ������ʽ��������");
	}
	
	public GeneratorUtil(Class clazz) throws Exception{
		this.clazz = clazz;
		//�������е�����
		//-1.���ݳ�ʼ��
		dataInit();
		//0.����Ŀ¼
		generatorDirectory();
		//1.QueryModel
		generatorQueryModel();
		//2.Hbm.xml
		generatorHbmXml();
		//3.Dao
		generatorDao();
		//4.Impl
		generatorImpl();
		//5.Ebi
		generatorEbi();
		//6.Ebo
		generatorEbo();
		//7.Action
		generatorAction();
		//8.applicationContext.xml
		generatorApplicationContextXml();
		//9.struts.xml(ѡ��)
		//modifyStrutsXml();
	}
	
	private void modifyStrutsXml() throws Exception {
		//1.��ȡԭʼ������
		//2.��ȡ���ض�λ�ã�package�����ָ������
		
		//����Ҫ�����ļ���д���ļ���ͬһ���ļ�
		/*
		RandomAccessFile���д�ļ�ʱ
		��ȡ��һ��100����70��д��д�����ݻḲ�Ǻ�30
		111
		222
		333
		444
		��333�ĺ���д5
		111
		222
		333
		544
		��333�ĺ���д5
		111
		222
		333
		555
		*/
		//����һ��
		/*
		��ȡԭʼ�ļ���������д�����ļ�
		д֮ǰ�жϣ���ȡ�������Ƿ����ض����ݣ��ض�����д֮ǰ�������µ�����
		д���֮���������µ��ļ���ɾ���ϵ��ļ���ʹ�����ļ�����Ϊ�ϵ��ļ�
		*/
		//��������
		//1.��ȡԭʼ�ļ����ļ���С,�ֽ�����1000
		File f = new File("resources/struts.xml");
		long len = f.length();
		//2.����һ���ֽ����飬��С����ԭʼ�ļ��ֽ�����
		byte[] buf = new byte[(int)len];
		//3.��ԭʼ�ļ������byte����
		InputStream is = new FileInputStream(f);
		is.read(buf);
		is.close();
		//4.��bufת��Ϊ�ַ���
		String all = new String(buf);
		//5.���ҹ̶�λ��
		int idx = all.lastIndexOf("    </package>");
		//6.��Ҫд������ݲ����λ��
		String info = "    	<!-- "+b+" -->\r\n    	<action name=\""+s+"_*\" class=\""+s+"Action\" method=\"{1}\">\r\n    	</action>\r\n\r\n";
		//7.��info����all��ָ��λ��
		StringBuilder sbf = new StringBuilder(all);
		sbf.insert(idx, info);
		//8.��sbf�е������������д��struts.xml
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(sbf.toString().getBytes());
		fos.close();
	}

	//8.applicationContext.xml
	private void generatorApplicationContextXml() throws Exception {
		File f = new File("resources/applicationContext-"+s+".xml");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();
		
		bw.write("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
		bw.newLine();
		
		bw.write("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		bw.newLine();
		
		bw.write("	xsi:schemaLocation=\"");
		bw.newLine();
		
		bw.write("		http://www.springframework.org/schema/beans ");
		bw.newLine();
		
		bw.write("		http://www.springframework.org/schema/beans/spring-beans.xsd");
		bw.newLine();
		
		bw.write("		\"> ");
		bw.newLine();
		
		bw.write("	<!-- Action -->");
		bw.newLine();
		
		bw.write("	<bean id=\""+s+"Action\" class=\""+pkg+".web."+b+"Action\" scope=\"prototype\">");
		bw.newLine();
		
		bw.write("		<property name=\""+s+"Ebi\" ref=\""+s+"Ebi\"/>");
		bw.newLine();
		
		bw.write("	</bean>");
		bw.newLine();
		
		bw.write("	<!-- Ebi -->");
		bw.newLine();
		
		bw.write("	<bean id=\""+s+"Ebi\" class=\""+pkg+".business.ebo."+b+"Ebo\">");
		bw.newLine();
		
		bw.write("		<property name=\""+s+"Dao\" ref=\""+s+"Dao\"/>");
		bw.newLine();
		
		bw.write("	</bean>");
		bw.newLine();
		
		bw.write("	<!-- Dao -->");
		bw.newLine();
		
		bw.write("	<bean id=\""+s+"Dao\" class=\""+pkg+".dao.impl."+b+"Impl\">");
		bw.newLine();
		
		bw.write("		<property name=\"sessionFactory\" ref=\"sessionFactory\"/>");
		bw.newLine();
		
		bw.write("	</bean>");
		bw.newLine();
		
		bw.write("</beans>");
		bw.newLine();
		
		bw.flush();
		bw.close();			
	}

	//7.Action
	private void generatorAction() throws Exception {
		File f = new File("src/"+dir+"/web/"+b+"Action.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".web;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import java.util.List;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import "+pkg+".business.ebi."+b+"Ebi;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+b+"Model;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+b+"QueryModel;");
		bw.newLine();
		
		bw.write("import com.myit.erp.util.base.BaseAction;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public class "+b+"Action extends BaseAction{");
		bw.newLine();
		
		bw.write("	public "+b+"Model "+l+"m = new "+b+"Model();");
		bw.newLine();
		
		bw.write("	public "+b+"QueryModel "+l+"qm = new "+b+"QueryModel();");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	private "+b+"Ebi "+s+"Ebi;");
		bw.newLine();
		
		bw.write("	public void set"+b+"Ebi("+b+"Ebi "+s+"Ebi) {");
		bw.newLine();
		
		bw.write("		this."+s+"Ebi = "+s+"Ebi;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	//�б�");
		bw.newLine();
		
		bw.write("	public String list(){");
		bw.newLine();
		
		bw.write("		setDataTotal("+s+"Ebi.getCount("+l+"qm));");
		bw.newLine();
		
		bw.write("		List<"+b+"Model> "+s+"List = "+s+"Ebi.getAll("+l+"qm,pageNum,pageCount);");
		bw.newLine();
		
		bw.write("		put(\""+s+"List\", "+s+"List);");
		bw.newLine();
		
		bw.write("		return LIST;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	//�����");
		bw.newLine();
		
		bw.write("	public String input(){");
		bw.newLine();
		
		bw.write("		if("+l+"m.getUuid()!=null){");
		bw.newLine();
		
		bw.write("			"+l+"m = "+s+"Ebi.get("+l+"m.getUuid());");
		bw.newLine();
		
		bw.write("		}");
		bw.newLine();
		
		bw.write("		return INPUT;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	//���");
		bw.newLine();
		
		bw.write("	public String save(){");
		bw.newLine();
		
		bw.write("		if("+l+"m.getUuid() == null){");
		bw.newLine();
		
		bw.write("			"+s+"Ebi.save("+l+"m);");
		bw.newLine();
		
		bw.write("		}else{");
		bw.newLine();
		
		bw.write("			"+s+"Ebi.update("+l+"m);");
		bw.newLine();
		
		bw.write("		}");
		bw.newLine();
		
		bw.write("		return TO_LIST;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	//ɾ��");
		bw.newLine();
		
		bw.write("	public String delete(){");
		bw.newLine();
		
		bw.write("		"+s+"Ebi.delete("+l+"m);");
		bw.newLine();
		
		bw.write("		return TO_LIST;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("}");
		bw.newLine();

		bw.flush();
		bw.close();			
	}

	//6.Ebo
	private void generatorEbo()  throws Exception {
		File f = new File("src/"+dir+"/business/ebo/"+b+"Ebo.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".business.ebo;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import java.io.Serializable;");
		bw.newLine();
		
		bw.write("import java.util.List;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import "+pkg+".business.ebi."+b+"Ebi;");
		bw.newLine();
		
		bw.write("import "+pkg+".dao.dao."+b+"Dao;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+b+"Model;");
		bw.newLine();
		
		bw.write("import com.myit.erp.util.base.BaseQueryModel;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public class "+b+"Ebo implements "+b+"Ebi{");
		bw.newLine();
		
		bw.write("	private "+b+"Dao "+s+"Dao;");
		bw.newLine();
		
		bw.write("	public void set"+b+"Dao("+b+"Dao "+s+"Dao) {");
		bw.newLine();
		
		bw.write("		this."+s+"Dao = "+s+"Dao;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public void save("+b+"Model "+l+"m) {");
		bw.newLine();
		
		bw.write("		"+s+"Dao.save("+l+"m);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public void update("+b+"Model "+l+"m) {");
		bw.newLine();
		
		bw.write("		"+s+"Dao.update("+l+"m);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public void delete("+b+"Model "+l+"m) {");
		bw.newLine();
		
		bw.write("		"+s+"Dao.delete("+l+"m);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public "+b+"Model get(Serializable uuid) {");
		bw.newLine();
		
		bw.write("		return "+s+"Dao.get(uuid);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public List<"+b+"Model> getAll() {");
		bw.newLine();
		
		bw.write("		return "+s+"Dao.getAll();");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public List<"+b+"Model> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {");
		bw.newLine();
		
		bw.write("		return "+s+"Dao.getAll(qm,pageNum,pageCount);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public Integer getCount(BaseQueryModel qm) {");
		bw.newLine();
		
		bw.write("		return "+s+"Dao.getCount(qm);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		bw.close();			
	}

	//5.Ebi
	private void generatorEbi()  throws Exception {
		File f = new File("src/"+dir+"/business/ebi/"+b+"Ebi.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".business.ebi;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import org.springframework.transaction.annotation.Transactional;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+b+"Model;");
		bw.newLine();
		
		bw.write("import com.myit.erp.util.base.BaseEbi;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("@Transactional");
		bw.newLine();
		
		bw.write("public interface "+b+"Ebi extends BaseEbi<"+b+"Model>{");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		bw.close();	
	}

	//4.Impl
	private void generatorImpl()  throws Exception {
		File f = new File("src/"+dir+"/dao/impl/"+b+"Impl.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".dao.impl;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import org.hibernate.criterion.DetachedCriteria;");
		bw.newLine();

		bw.write("import org.hibernate.criterion.Restrictions;");
		bw.newLine();

		bw.newLine();
		
		bw.write("import "+pkg+".dao.dao."+b+"Dao;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+b+"Model;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+b+"QueryModel;");
		bw.newLine();
		
		bw.write("import com.myit.erp.util.base.BaseImpl;");
		bw.newLine();
		
		bw.write("import com.myit.erp.util.base.BaseQueryModel;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public class "+b+"Impl extends BaseImpl<"+b+"Model> implements "+b+"Dao{");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){");
		bw.newLine();
		
		bw.write("		"+b+"QueryModel "+l+"qm = ("+b+"QueryModel)qm;");
		bw.newLine();
		
		bw.write("		// TODO ����Զ����ѯ����");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		bw.close();		
	}

	//3.Dao
	private void generatorDao() throws Exception {
		File f = new File("src/"+dir+"/dao/dao/"+b+"Dao.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".dao.dao;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+b+"Model;");
		bw.newLine();
		
		bw.write("import com.myit.erp.util.base.BaseDao;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public interface "+b+"Dao extends BaseDao<"+b+"Model> {");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		bw.close();		
	}

	//2.Hbm.xml
	private void generatorHbmXml() throws Exception {
		//1.�����ļ�
		File f = new File("src/"+dir+"/vo/"+b+"Model.hbm.xml");
		
		if(f.exists()){
			return;
		}
		
		f.createNewFile();
		//2.IOд������
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();
		
		bw.write("<!DOCTYPE hibernate-mapping PUBLIC");
		bw.newLine();
		
		bw.write("        '-//Hibernate/Hibernate Mapping DTD 3.0//EN'");
		bw.newLine();
		
		bw.write("        'http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd'>");
		bw.newLine();
		
		bw.write("<hibernate-mapping>");
		bw.newLine();
		
		bw.write("    <class name=\""+pkg+".vo."+b+"Model\" table=\"tbl_"+s+"\">");
		bw.newLine();
		
		bw.write("        <id name=\"uuid\">");
		bw.newLine();
		
		bw.write("            <generator class=\"native\" />");
		bw.newLine();
		
		bw.write("        </id>");
		bw.newLine();
		
		//hibernate��ӳ�������ļ���Ҫ��ԭʼģ�����е����Խ������ã������ȡ�����ֶ�
		Field[] fds = clazz.getDeclaredFields();
		for(Field fd:fds) {
			//����ֶε����η���private������
			if(fd.getModifiers() == Modifier.PRIVATE && !fd.getName().equals("uuid")){
				//����ǹ�����ϵ������,���ǹ�����ϵ(Long,Integer,Double,String)
				if( fd.getType().equals(String.class)||
					fd.getType().equals(Long.class)||
					fd.getType().equals(Integer.class)||
					fd.getType().equals(Double.class)
					){
					if(!fd.getName().endsWith("View")){
						bw.write("        <property name=\""+fd.getName()+"\"/>");
						bw.newLine();
					}
				}
			}
		}
		
		bw.write("    </class>");
		bw.newLine();
		
		bw.write("</hibernate-mapping>");
		bw.newLine();
		
		bw.flush();
		bw.close();		
	}

	//1.QueryModel
	private void generatorQueryModel() throws Exception {
		//1.�����ļ�
		File f = new File("src/"+dir+"/vo/"+b+"QueryModel.java");
		
		//�жϣ�������ļ����ڣ���ֹ����
		if(f.exists()){
			return;
		}
		
		f.createNewFile();
		//2.IOд������
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".vo;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import com.myit.erp.util.base.BaseQueryModel;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public class "+b+"QueryModel extends "+b+"Model implements BaseQueryModel{");
		bw.newLine();
		
		bw.write("	// TODO ����Զ����ѯ����");
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		bw.close();
	}
	
	//0.����Ŀ¼
	private void generatorDirectory() {
		//business/ebi
		//				 src+//com.myit.erp.auth.emp+business/ebi .vo
		File f = new File("src/"+dir+"/business/ebi");
		f.mkdirs();
		//business/ebo
		f = new File("src/"+dir+"/business/ebo");
		f.mkdirs();
		//dao/dao
		f = new File("src/"+dir+"/dao/dao");
		f.mkdirs();
		//dao/impl
		f = new File("src/"+dir+"/dao/impl");
		f.mkdirs();
		//web
		f = new File("src/"+dir+"/web");
		f.mkdirs();
	}
	
	//-1.���ݳ�ʼ��
	private void dataInit() {
		String className = clazz.getSimpleName();					//EmpModel
		b = className.substring(0, className.length()-5);	//Emp
		String first = b.substring(0,1);							//E
		l = first.toLowerCase();						//e
		s = l+b.substring(1);						//emp
		String rootPkg = clazz.getPackage().getName();				//com.myit.erp.auth.emp.vo
		pkg = rootPkg.substring(0,rootPkg.length()-3);		//com.myit.erp.auth.emp
		dir = pkg.replace(".","/");							//com/myit/erp/auth/emp/vo
	}
	
	/*
	public static void main(String[] args) throws Exception {
		//���Ĺ���ԭ���ļ�IO+����
		File f = new File("src/EmpAction.java");
		f.createNewFile();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write("public class EmpAction{}");
		bw.flush();
		bw.close();
	}
	*/
}
