package com.fable.dsp.common.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fable.dsp.common.dto.dataswitch.TableDto;
import com.fable.dsp.common.dto.dataswitch.TreeDataDto;





/**
 * 根据数据库列出其下的所有表名
 * @author Administrator
 *
 */
public class ListTableUtil {
	private ResultSet rs;
	private Connection connection;
	/**ListTableUtil相关数据**/
    public static  String SUCCESS="获取数据库表名成功";
    public static String ERROR="获取数据库表名失败";
    List<TableDto>m_tableList=null;	//表名TableDto
    List<TreeDataDto> m_tableList1=null;    //表名TableDto
    List m_fieldList=null;	//属性
    //需要指定的是用户名，密码，url,driver
    private TmpDsp m_tmpDSP;	//
    
	public TmpDsp getM_tmpDSP() {
		return m_tmpDSP;
	}
	public void setM_tmpDSP(TmpDsp m_tmpDSP) {
		this.m_tmpDSP = m_tmpDSP;
	}
	public boolean getConnection(){
		try {
			Class.forName(m_tmpDSP.getDbDriver());
			connection=DriverManager.getConnection(m_tmpDSP.getDbUrl(),m_tmpDSP.getDbUser(),m_tmpDSP.getDbPassword());
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void close(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

    /**
     * 获取数据源下面的所有表名
     * @return
     */
	public List<TreeDataDto> acquireTablesOfDataSouce() {
		
		

		try {
			//打开连接
			
			
			if (this.getConnection()) {
				//用connection方法得到下面的DatabaseMetaData对象
				DatabaseMetaData dbmd = connection.getMetaData();
				int index=0;
				//视图，同义词
				String[] types = { "TABLE", "VIEW", "ALIAS", "SYNONYM" };
				if (m_tableList1 == null) {

					m_tableList1 = new ArrayList<TreeDataDto>();

				} else {

					m_tableList1.clear();

				}
				/**
				 * DatabaseMetaData类的getTables方法 
ResultSet getTables(String catalog, 
                    String schemaPattern, 
                    String tableNamePattern, 
                    String[] types) 
                    throws SQLException检索可在给定类别中使用的表的描述。
                    仅返回与类别、模式、表名称和类型标准匹配的表描述。
                    它们根据 TABLE_TYPE、TABLE_SCHEM 和 TABLE_NAME 进行排序。 
				 */
				rs = dbmd.getTables(null, null, null, types);
				String dbName = dbmd.getDatabaseProductName();
				/**
				dbmd.storesLowerCaseIdentifiers(); 是小写，存小写
				dbmd.storesMixedCaseIdentifiers();
				dbmd.storesUpperCaseIdentifiers();
				**/
				if (dbName.equalsIgnoreCase("Oracle")) {

					String user = dbmd.getUserName();

					while (rs.next()) {

						String schem = rs.getString("TABLE_SCHEM");

						if (user.equalsIgnoreCase(schem)) {
						    TreeDataDto dto = new TreeDataDto();
						    dto.setText(rs.getString("TABLE_NAME"));
						    dto.setId(rs.getString("TABLE_NAME"));
							//m_tableList.add(new TableDto(rs.getString("TABLE_NAME"),new Long(index++)));

							m_tableList1.add(dto);
						}

					}

				} else {

					while (rs.next()) {
					    TreeDataDto dto = new TreeDataDto();
                        dto.setText(rs.getString("TABLE_NAME"));
                        dto.setId(rs.getString("TABLE_NAME"));
                        //m_tableList.add(new TableDto(rs.getString("TABLE_NAME"),new Long(index++)));

                        m_tableList1.add(dto);
						//m_tableList.add(new TableDto(rs.getString("TABLE_NAME"),new Long(index++)));

					}

				}
			}
			

		} catch (SQLException e) {

			System.out.print("Failed to acquire tables of a datasource.");

			System.out.println(e.getMessage());

			//addActionError(getText("DataSource.Exception"));


		} catch (Exception e) {

			System.out.print("Failed to acquire tables of a datasource.");

			System.out.println(e.getMessage());

			//addActionError(getText("DataSource.Exception"));


		} finally {

			this.close();

		}

		return m_tableList1;

	}
	public static void main(String[] args) {
		ListTableUtil listTableUtil=new ListTableUtil();
		TmpDsp m_tmpDSP=new TmpDsp();
		m_tmpDSP.setDbDriver("com.mysql.jdbc.Driver");
		m_tmpDSP.setDbPassword("root123");
		m_tmpDSP.setDbUser("root");
		m_tmpDSP.setDbUrl("jdbc:mysql://192.168.0.161:3306/fable_admin?user=root&password=root123");
		listTableUtil.setM_tmpDSP(m_tmpDSP);
		System.out.println(listTableUtil.acquireTablesOfDataSouce().get(2));
	}
	
	
}
