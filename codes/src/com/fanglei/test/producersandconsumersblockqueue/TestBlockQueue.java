package com.fanglei.test.producersandconsumersblockqueue;

/**
 * Created by Owen on 2017/4/2.
 */
public class TestBlockQueue {
}


class Resource
{
	private
	public
}


/**
 * 资源队列中的Object
 */
class ResourceObject
{

	private	String itemId = "";
	private String itemCode = "";
	private Boolean isMatched = false;

	ResourceObject(String itemId, String itemCode, Boolean isMatched)
	{
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.isMatched = isMatched;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	public String getItemId()
	{
		return this.itemId;
	}

	public void setItemCode(String itemCode)
	{
		this.itemCode = itemCode;
	}

	public String getItemCode()
	{
		return this.itemCode;
	}
}
class Producer implements Runnable
{

}

