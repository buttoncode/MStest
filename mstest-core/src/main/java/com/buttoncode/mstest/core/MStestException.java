package com.buttoncode.mstest.core;

public class MStestException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public MStestException()
	{
		super();
	}

	public MStestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MStestException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public MStestException(String message)
	{
		super(message);
	}

	public MStestException(Throwable cause)
	{
		super(cause);
	}
	
}
