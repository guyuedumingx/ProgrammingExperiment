package experiment2.wangwei;

class President implements Comparable<President>
{
    String no;
    String enName;
    String BirthPlace;
    String OnDutyStart;
    String BirthYear;
    String Party;

    President(String no, String enName, String BirthPlace, String OnDutyStart, String BirthYear, String Party)
    {
        this.no = no;
        this.enName = enName;
        this.BirthPlace = BirthPlace;
        this.OnDutyStart = OnDutyStart;
        this.BirthYear = BirthYear;
        this.Party = Party;
    }

    public int equals(President strobj) //根据历任总统的出生地进行排序。当两个美国总统的出生地相同时，请按照其任职先后排序
    {
        String str1 = this.getBirthPlace();
        String str2 = (String)strobj.getBirthPlace();
        return str1.compareTo(str2);
    }

    public int compareTo(President dstobj)//比较总统编号的大小
    {
        int src = Integer.parseInt(this.getNo());
        //President dstobj = (President)obj;
        int dst = Integer.parseInt(dstobj.getNo());

        if (src > dst)
            return 1;
        else
        {
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
