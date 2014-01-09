package railo.runtime.cache.tag.smart;

public interface SmartEntry {
	public String getId();

	public String getTypeId();

	public String getEntryHash();

	public String getResultHash();

	public long getCreateTime();
	public long getExecutionTime();

	public String getApplicationName();

	public String getCfid();
	

	public String getName();

	public int getPayLoad();

	public String getMeta();

	public String getTemplate();
	
	public int getLine();
}
