package org.pub.pt.data.sources.domain;

/**
 * Enumerate that holds the several data sources provided by project
 * @author balhau
 *
 */
public enum DataSources {
	
	INE("org.pt.pub.data.sources.domain.AbstractDataSource.INEDataSource")
	;
	
	private String className;
	
	private DataSources(String className){
		
	}
	
	/**
	 * Create, through reflection the respective instance of {@link DataSource}
	 * @return IDataSource
	 * @throws ClassNotFoundException Classloading problems
	 * @throws InstantiationException Instatiation problems
	 * @throws IllegalAccessException Invocation problems
	 */
	public DataSource buildInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		@SuppressWarnings("unchecked")
		Class<DataSource> cls=(Class<DataSource>)Class.forName(this.className);
		DataSource ds=cls.newInstance();
		return ds;
	}
}
