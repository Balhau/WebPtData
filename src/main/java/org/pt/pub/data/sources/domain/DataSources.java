package org.pt.pub.data.sources.domain;

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
	 * Create, through reflection the respective instance of {@link IDataSource}
	 * @return IDataSource
	 * @throws ClassNotFoundException Classloading problems
	 * @throws InstantiationException Instatiation problems
	 * @throws IllegalAccessException Invocation problems
	 */
	public IDataSource buildInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		@SuppressWarnings("unchecked")
		Class<IDataSource> cls=(Class<IDataSource>)Class.forName(this.className);
		IDataSource ds=cls.newInstance();
		return ds;
	}
}
