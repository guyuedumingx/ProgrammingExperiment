class President implements Comparable<President> {
	String no;
	String enName;
	String BirthPlace;
	String OnDutyStart;
	String BirthYear;
	String Party;
	
	President(String no, String enName, String BirthPlace, String OnDutyStart, String BirthYear, String Party) {
		this.no = no;
		this.enName = enName;
		this.BirthPlace = BirthPlace;
		this.OnDutyStart = OnDutyStart;
		this.BirthYear = BirthYear;
		this.Party = Party;
	}

	public int compareTo(President dstobj) {
		int src = Integer.parseInt(this.getNo());
		//President dstobj = (President)obj;
		int dst = Integer.parseInt(dstobj.getNo());
		if (src > dst)
			return 1;
		else {
			if (src < dst)
				return -1;
			else
				return 0;
		}
	}

	String getNo()
	{
		return this.no;
	}
	
	String getEnName()
	{
		return this.enName;
	}
	
	String getBirthPlace()
	{
		return this.BirthPlace;
	}
	
	String getOnDutyStart()
	{
		return this.OnDutyStart;
	}
	
	String getBirthYear()
	{
		return this.BirthYear;
	}
	
	String getParty()
	{
		return this.Party;
	}
	
}

