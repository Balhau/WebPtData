package org.pt.pub.data.sources;

/**
 * Enumerate that holds the several data sources provided by project
 * @author balhau
 *
 */
public enum DataSources {
	
	INE("org.pt.pub.data.sources.AbstractDataSource.INEDataSource")
	;
	
	private String className;
	
	private DataSources(String className){
		
	}
	
	/**
	 * Create, through reflection the respective instance of {@link IDataSource}
	 * @return {@link IDataSource}
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public IDataSource buildInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<IDataSource> cls=(Class<IDataSource>)Class.forName(this.className);
		IDataSource ds=cls.newInstance();
		return ds;
	}
}
