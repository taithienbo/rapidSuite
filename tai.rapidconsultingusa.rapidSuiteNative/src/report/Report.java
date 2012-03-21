package report;

import java.io.Serializable;

public class Report implements Serializable
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int year;

	private float feb_rev;

	private float jan_rev;

	private float apr_rev;

	private float mar_rev;

	private float jul_rev;

	private float may_rev;

	private float jun_rev;

	private float aug_rev;

	private float sep_rev;

	private float oct_rev;

	private float nov_rev;

	private float dec_rev;
	
	private static final String dollar = "$";
	
	public static final String Report_RETRIEVAL_KEY = "Report object";

	
	
	public Report ( int year, float jan_rev, float feb_rev, float mar_rev, float apr_rev,
			float may_rev, float jun_rev, float jul_rev, float aug_rev, float sep_rev,
			float oct_rev, float nov_rev, float dec_rev)
			{

		this.year				=		year;
		this.jan_rev 		=	 	jan_rev;
		this.feb_rev		= 		feb_rev;
		this.mar_rev		= 		mar_rev;
		this.apr_rev		= 		apr_rev;
		this.may_rev 		= 		may_rev; 
		this.jun_rev	 	= 		jun_rev;
		this.jul_rev		= 		jul_rev;
		this.aug_rev 		= 		aug_rev;
		this.sep_rev		= 		sep_rev;
		this.oct_rev 		= 		oct_rev;
		this.nov_rev		= 		nov_rev;
		this.dec_rev		= 		dec_rev;
		
	}


	public int getYear()
	{
		return year;
	}


	public float getJanRev()
	{
		return jan_rev;
	}


	public float getFebRev()
	{
		return feb_rev;
	}


	public float getMarRev()
	{
		return mar_rev;
	}

	
	
	public float getAprRev()
	{
		return apr_rev;
	}
	
	
	public float getMayRev()
	{
		return may_rev;
	}


	public float getJunRev()
	{
		return jun_rev;
	}
	
	
	public float getJulRev()
	{
		return jul_rev;
	}
	
	
	public float getAugRev()
	{
		return aug_rev;
	}
	
	
	public float getSepRev()
	{
		return sep_rev;
	}
	
	
	public float getOctRev()
	{
		return oct_rev;
	}
	
	
	public float getNovRev()
	{
		return nov_rev;
	}
	
	
	public float getDecRev()
	{
		return dec_rev;
	}
	
	
	public float getTotalrev()
	{
		return jan_rev + feb_rev + mar_rev + apr_rev + may_rev + jun_rev 
				+ jul_rev + aug_rev + sep_rev + oct_rev + nov_rev + dec_rev;
	}
}	
